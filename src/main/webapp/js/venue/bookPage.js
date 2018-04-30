

$(document).ready(function () {
   var args=requestParamFormatter();
   activityId=args['activityId'];
   originalPrice=args['seatPrice'];
   seats=args['seatNo'];
   seatMap=seats.split(',');
   init(activityId);
    $('input[type=radio][name=isVip]').change(function() {
        if (this.value == 2) {
            $('#ticketsInfo').hide();
        }
        else if (this.value == 1) {
            $('#ticketsInfo').show();
        }
    });
});



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
}

$('#loginButton').on('click',function () {
    $.ajax({
        data:{userName:$('#userName').val(),password:$('#password').val()},
        type:'post',
        url:'/venue/userLogin',
        success:function (id) {
            if(id<0) {
                alert('验证失败');
                $('#userName').val('');
                $('#password').val('');
            }
            else{
                $.ajax({
                    url:'/data/user/getUserInfo',
                    data:{id:id},
                    success:function (info) {
                        userId=info.id;
                        var level=info.level;
                        $.ajax({
                            url:'/data/user/getDiscountPrice',
                            data:{level:level,originalPrice:originalPrice},
                            success:function (data) {
                                $('#vipPrice').show();
                                $('#afterDiscountPrice').html(data);
                            }
                        });
                    }
                });
            }
        }
    });
})

$('#pay').on('click',function () {
    var orderId=null;
    var price=null;
    if(userId!=null){
        price=$('#afterDiscountPrice').html();
        var url = "/venue/vipPay"; // the script where you handle the form input.
        var data = {
            userId:userId,
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
                if(id>=0)
                    alert('购买成功')
                else
                    alert('购买失败');
            },

        })
    }
    else {
        price=$('#originalPrice').html();
        var url = "/venue/visitorPay"; // the script where you handle the form input.
        var data = {
            activityId: activityId,
            seatNo: seats,
            totalPrice: originalPrice,
        };
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: function (id) {
                if(id>=0)
                    alert('购买成功')
                else
                    alert('购买失败');
            },

        })
    }

})


$('#cancel').on('click',function () {
    location.href="/venue/selectSeat?activityId="+activityId;
})



var originalPrice=null;
var seatMap=null;
var seats=null;
var activityId=null;
var userId=null;



