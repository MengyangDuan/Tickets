
$(document).ready(function () {
    initInfo();
    $('#create-btn').on('click', function () {
        updateEvent();
    });
})

function updateEvent() {
    jQuery.ajax({
        url: '/user/modify',
        type:"post",
        data:{
            username:$('#username').val(),
            email:	$('#email').val(),
        },
        cache: false,
        success: function(data) {
            if(data.result == 'success'){
                swal("成功", "您的个人信息修改成功!");
                init();
            }else {
                swal("错误","信息修改失败,请稍后再试!","error");
            }
        }
    })
}

$('#selfPanel_stopCardBtn').click(function () {

    var ans=confirm('确定停卡吗？停了就不能恢复喽');
    if(ans){
        $.ajax({
            url:'/vip/stopCard',
            type:'POST',
            success:function (msg) {
                $('#selfPanel_stopCard_msg').html(msg);
                $('#selfPanel_stopCard_msg').show();
                setTimeout(function () {
                    location.reload();
                },600)
            }
        })
    }

})

function initInfo() {
    $.ajax({
        url:'/data/user/getInfo',
        success:function (data) {
            $('#userLevel').html(data.level);
            $('#userPoints').html(data.rewardPoints);
            $('#username').val(data.userName);
            $('#email').val(data.email);
        },
    })
}
// $('#topupBtn').click(function () {
//     location.replace('/vip/topUp');
// });
// $('#toConvertScoreBtn').click(function () {
//     location.replace('/vip/convert');
// })
/*
 private int id;
 private String realName;
 private String idCard;
 private String avatar;
 private double moneyLeft;
 private double moneyPaid;
 private int level;
 private double score;
 private VIPState state;
 */