$(document).ready(function() {
    $.ajax({
        url:'/data/venue/getVenue',
        success:function (data) {
            var str=data.seatMap;
            var seatTypes=data.seatType;
            var seats=str.split(","); //字符分割
            var sc = $('#seat-map').seatCharts({
                map: seats,
                naming : {
                    top : false,
                    getLabel : function (character, row, column) {
                        return character;
                    },
                },
            });
            for(var i=0;i<seatTypes.length;i++){
                var type=seatTypes[i];
                var addContent="<div class='addPrice'><span class='seatType'>"+type+"</span><input type='text' class='seatPrice'></div>";
                $("#seat-map").append(addContent);
            }
        },
    })
});

setInterval(function() {
    $.ajax({
        type     : 'get',
        url      : 'book.php',
        dataType : 'json',
        success  : function(response) {
            //遍历所有座位
            $.each(response.bookings, function(index, booking) {
                //将已售出的座位状态设置为已售出
                sc.status(booking.seat_id, 'unavailable');
            });
        }
    });
}, 10000); //每10秒

$("form").submit(function (e) {
    var datas={
        name:$('#name').val(),
        time:$('#time').val(),
        type:$('input:radio:checked').val(),
        description:$('#description').val()
    };
    $.ajax({
        type: "POST",
        url: "/venue/releaseActivity",
        data: datas,
        success: function(dat) {
            if(dat.result>=0) {
                var seatMaps = {};
                var seatTypes = $('.addPrice');
                for (var i = 0; i < seatTypes.length; i++) {
                    var seatType = $(seatTypes[i].getElementsByClassName("seatType")).html();
                    var price = $(seatTypes[i].getElementsByClassName("seatPrice")).val();
                    seatMaps[seatType] = price;
                }
                console.log(JSON.stringify(seatMaps));
                $.ajax({
                    type: "POST",
                    url: "/venue/makeTickets",
                    data: {activityId:dat.result,seatMap: JSON.stringify(seatMaps)},
                    success: function (data) {
                        swal('成功','成功发布活动','success');
                        location.replace('/venue/info');
                    },
                    error:function (message) {
                      alert(message);
                    }
                });
            }
        },

    });
    e.preventDefault(); // avoid to execute the actual submit of the form.
});

