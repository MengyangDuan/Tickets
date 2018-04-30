
$('#registerForm').submit(function (e) {
    var data={
        venueName:$('#venueName').val(),
        address:$('#address').val(),
        seatMap: $('#seat').val(),
        email:$('#email').val(),
        password:$('#password').val()
        };
    if($('#password').val()!=$('#repassword').val()){
        swal('两次密码不一样','请重新输入',"error");
    }else {
        $.ajax({
            type:'POST',
            url:'/venue/register',
            data:data,
            success:function (data) {
                if(data.result=='success') {
                    swal("注册成功", "请关注邮箱，查看审核情况");
                    document.getElementById("registerForm").reset();
                }
                else
                    swal(data.result);
            },
            error:function(XMLResponse){alert(XMLResponse.responseText)}
        });
    }
    // alert(role=='on');
    // alert($('#hostel').val());
    e.preventDefault(); // avoid to execute the actual submit of the form.
})


