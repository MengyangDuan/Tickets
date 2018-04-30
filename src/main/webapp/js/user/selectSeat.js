$(document).ready(function() {
    var firstSeatLabel = 1;
    var args=requestParamFormatter();
    activityId=args['activityId'];
    $.ajax({
        data:{activityId:activityId},
        url:'/data/venue/getVenueById',
        success:function (data) {
            var str=data.seatMap;
            var seatMap=str.split(","); //字符分割
            var sc = $('#seat-map').seatCharts({
                map: seatMap,
                naming : {
                    top : false,
                    getLabel : function (character, row, column) {
                        return firstSeatLabel++;
                    },
                },
                legend : { //定义图例
                    node : $('#legend'),
                    items : [
                        [ 'f', 'available',   '可选' ],
                        [ 'f', 'unavailable', '已售']
                    ]
                },
                click: function () {
                        var id = this.settings.label;
                        var status = this.status();
                        var price = null;
                        $.ajax({
                            url: '/data/user/getTicketPriceBySeat',
                            data: {activityId: activityId, seatNo: id},
                            async: false,
                            success: function (data) {
                                price = data;
                            }
                        });
                        if (status == 'available') {//可选座
                            if (parseFloat($('#counter').text()) < 6) {
                                $('<li>' + id + '号<br/>￥' + price + '</li>')
                                    .attr('id', 'cart-item-' + id)
                                    .data('seatId', id)
                                    .appendTo($('#cart'));
                                $('#counter').text(sc.find('selected').length + 1);
                                //计算总计金额
                                $('#total').text(recalculateTotal() + price);
                                seatList.push(id);
                                setHref();
                                return 'selected';
                            }
                            else {
                                swal('错误', '最多选6张', 'error');
                                return 'available';
                            }
                        } else if (status == 'selected') {//已选中
                            $('#counter').text(sc.find('selected').length - 1);
                            $('#total').text(recalculateTotal() - price);
                            //删除已预订座位
                            $('#cart-item-' + id).remove();
                            seatList.remove(id);
                            setHref();
                            return 'available';
                        } else if (status == 'unavailable') {//已售出
                            //已售出
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                },
            });
            $.ajax({
                url      : '/data/user/getUnavailableSeats',
                data     :{activityId:activityId},
                success  : function(response) {
                    sc.get(response).status('unavailable');
                }
            });
        },
    });

});

function setHref(){
    href="/user/book?activityId="+activityId+"&seatNo="+seatList.toString()+"&seatPrice="+parseFloat($("#total").text());
}

$('#submit').on('click',function () {
    location.replace(href);
})

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

function recalculateTotal(sc,money) {
    var total = $('#total').text();
    var result=parseFloat(total);
    return result;
}
var activityId=null;
var seatList=[];
var href=null;

// $('#submit').click(function () {
//     $.ajax({
//         url      : '/user/selectSeat',
//         type     : 'post',
//         data     :{activityId:activityId,seatNo:seatList,price:parseFloat($("#total").text())},
//         traditional: true,
//         success  : function(response) {
//             location.replace("/user/book")
//         }
//     });
// });