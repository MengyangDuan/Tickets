var orderId=null;
var money=null;
$(document).ready(function () {
    var args=requestParamFormatter();
    orderId=args['orderId'];
    money=args['money'];
    $('#payMoney').html(money);
    init();
});
function init() {
    $.ajax({
        url:'/data/user/getUserAccount',
        success:function (data) {
            $('#accountId').html(data.accountId);
        }
    });
}
$('#create-btn').on('click',function(){
    console.log(orderId);
    console.log($('#accountId').html());
    console.log($('#accountPassword').val());
    console.log( $('#payMoney').html());
    $.ajax({
        type:'post',
        data:{orderId:orderId,accountId: $('#accountId').html(),password:$('#accountPassword').val(),money: $('#payMoney').html()},
        url:'/user/payOrder',
        success:function (data) {
            if(data.result=='success')
                swal('成功','支付成功','success');
            else {
                swal('支付失败', '请检查账户信息及余额', 'error');
            }
            $('#accountPassword').val('');
        }
    });
});