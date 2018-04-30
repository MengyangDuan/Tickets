
$(document).ready(function () {
    initInfo();
    $('#create-btn').on('click', function () {
        updateEvent();
    });
    $('#create-cancle').on('click', function () {
        window.location.href="/venue/info";
    });
})

function updateEvent() {
    jQuery.ajax({
        url: '/venue/modify',
        type:"post",
        data:{
            id:id,
            venuename:$('#name').val(),
            email:$('#email').val(),
            address:$('#address').val(),
            seat:$('#seat').html()
        },
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

function initInfo() {
    $.ajax({
        url: '/data/venue/getVenue',
        success: function (data) {
            id=data.id;
            $('#name').val(data.venueName);
            $('#address').val( data.address);
            $('#email').val(data.email);
            $('#seat').html(data.seatMap);
        },
    })
}
var id=null;
