$(function () {
    $('#create-btn').on('click', function () {
        modifyPassword();
    });
});


function modifyPassword() {
    var oldpass = $('#oldpass').val();
    var newpass = $('#newpass').val();
    var newpass1 = $('#newpass1').val();
    if(newpass != newpass1){
        swal("信息", "两次密码输入不一致!", "error");
        return
    }
    jQuery.ajax({
        url: '/user/modifyPassword',
        type:"post",
        cache: false,
        data:{
            oldpass:oldpass,
            newpass:newpass
        },
        success: function(data) {
            if(data.result == 'success'){
                swal("成功", "密码修改成功!");
                $('#oldpass').val("");
                $('#newpass').val("");
                $('#newpass1').val("");
            }
            else {
                swal("错误", "密码修改失败,请稍后再试!", "error");
                $('#oldpass').val("");
                $('#newpass').val("");
                $('#newpass1').val("");
            }
        }
    })

}