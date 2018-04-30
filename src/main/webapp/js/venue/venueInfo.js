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
            $('#name').html(data.venueName);
            $('#email').html(data.email);
            $('#address').html(data.address);
        },
    })
});


