

$(document).ready(function () {
   var args=requestParamFormatter();
   var activityId=args['activityId'];
   originalPrice=args['seatPrice'];
   seats=args['seatNo'];
   seatMap=seats.split(',');
   init(activityId);
});

function confirm() {
    var args = requestParamFormatter();
    var activityId = args['activityId'];
    var url = "/user/bookSeat"; // the script where you handle the form input.
    var data = {
        activityId: activityId,
        seatNo: seats,
        totalPrice: originalPrice,
        afterDiscountPirce: $('#afterDiscountPrice').html(),
    };
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        success: function (id) {
            location.replace('/user/payOrder?orderId=' + id+'&money='+parseFloat($('#payPrice').html()));
        },

    })
}



function init(activityId) {
    $("#originalPrice").html(originalPrice);
    $("#seat").html(seatMap.join(','));
    $.ajax({
        url:'/data/user/getActivityInfo',
        data:{activityId:activityId},
        success:function (data) {
            $('#address').html(data.place);
            $('#activityName').html(data.name);
            $('#time').html(new Date(parseFloat(data.time)).format("yyyy-MM-dd hh:mm:ss"));
        }
    });
    $.ajax({
        url:'/data/user/getInfo',
        success:function (data) {
            var level=data.level;
            $.ajax({
                url:'/data/user/getDiscountPrice',
                data:{level:level,originalPrice:originalPrice},
                success:function (data) {
                    $('#afterDiscountPrice').html(data);
                    $('#payPrice').html(data);
                }
            });
            var coupons=data.coupons;
            if(coupons.length==0)
                $("#couponLeft").append('<div>无</div>');
            for(var i=0;i<coupons.length;i++){
                var coupon=coupons[i];
                var addContent="<div class='exchangeCoupon'><div class='addCoupon' style='display: block'>金额：" + coupon.money + "元</div><button class='btn btn-primary' id=\'" + coupon.id + "\'  onclick='clickAction("+coupon.id+")'>使用</button></div>";
                $("#couponLeft").append(addContent);
            }
        }
    });
}



function clickAction(id) {
    submitEvent(id);
    $(document.getElementById(id).parentNode).remove();
}

var originalPrice=null;
var seatMap=null;
var seats=null;
var activityId=null;

function submitEvent(id) {
    $.ajax({
        type: 'POST',
        url: '/user/useCoupon',
        data: {couponId:id},
        success: function (money) {
            var pay=parseFloat($('#payPrice').html())-parseFloat(money);
            if(pay<0)
                pay=0;
            $('#payPrice').html(pay);
        },
        error:function (message) {
            alert(message);
        }
    })
}

