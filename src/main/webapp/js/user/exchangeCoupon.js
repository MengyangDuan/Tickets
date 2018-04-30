$(document).ready(function () {
    init();
});


function init() {
    $.ajax({
        url:'/data/user/getInfo',
        success:function (data) {
            var points=data.rewardPoints;
            $('#scoreOriginal').html(points);
            var coupons=data.coupons;
            if(coupons.length==0)
                $("#couponLeft").append('<div>无</div>');
            for(var i=0;i<coupons.length;i++){
                var coupon=coupons[i];
                var addContent='<div class="addCoupon"><div class="title">金额：' + coupon.money + '元</div><div class="sub-title ">积分：' + coupon.rewardPoints + '分</div></div>';
                $("#couponLeft").append(addContent);
            }
        }
    })
    $.ajax({
        url:'/data/user/getAvailableCoupons',
        success:function (data) {
            if(data.length==0)
                $("#couponLeft").append('<div>无</div>');
            for(var i=0;i<data.length;i++) {
                var coupon=data[i];
                var addContent="<div class='exchangeCoupon'><div class='addCoupon'><div class='title'>金额：" + coupon.money + "元</div><div class='sub-title'>积分：" + coupon.rewardPoints + "分</div></div><form class='exchange' id=\'" + coupon.id + "\'><input type='submit' class='btn btn-primary' value='兑换'/></form></div>";
                $("#availableCoupon").append(addContent);
            }
        }
    })
    $("#availableCoupon").on("submit", ".exchange", function() {
        submitEvent($(this).attr('id'));
    });
}




function submitEvent(id) {
    $.ajax({
        type: 'POST',
        url: '/user/exchangeCoupon',
        data: {couponId:id},
        success: function (msg) {
            swal("操作成功!", "已兑换！");
            init();
        }
    })
}




