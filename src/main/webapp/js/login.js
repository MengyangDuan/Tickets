
//TODO login的错误信息要弹出，然后消失，要用到js！

// $("#userName").blur(function(event) {
//     $.ajax({
//         type:"POST",
//         url:"/checkUser",
//         data:{name:$('#userName').val()},
//         success:function(msg){
//             $('#nameMsg').html(msg);
//             console.log(msg);
//             // $("#accountStatus").html(msg);
//         },
//         error:function(jqXHR) {
//             alert("账号发生错误！")
//         },
//     });
// });
//
// $("#password").blur(function(event) {
//     var name=$('#userName').val();
//     var password=$("#password").val();
//     console.log("#password.blur  "+name+" "+password);
//     $.ajax({
//         type:"POST",
//         url:"/checkPassword",
//         data:{userName:name,
//             password:password},
//         success:function(msg){
//            alert(msg);
//            $('#passwordMsg').html(msg);
//         },
//         error:function(jqXHR) {
//             alert("密码查询发生错误！")
//         },
//     });
// });

// $('loginForm').submit(function (e) {
//     e.preventDefault(); // avoid to execute the actual submit of the form.
//     var data={
//         userName:$('#userName').val(),
//         password:$('#password').val()
//     };
//     $.ajax({
//         type:'POST',
//         url:'/checkUser',
//         data:$('#userName').val(),
//         success:function (data) {
//             if(data=='SUCCESS'){
//                 $.ajax({})
//             }else {
//                 alert(data);
//             }
//         }
//
//     })
// })
$('#registerForm').submit(function (e) {
    var data={
        userName:$('#userName').val(),
        password:$('#password').val(),
        email:$('#email').val(),
        };
    if($('#password').val()!=$('#repassword').val()){
        swal('两次密码不一样','请重新输入',"error");
    }else {
        $.ajax({
            type:'POST',
            url:'/user/register',
            data:data,
            success:function (data) {
                if(data.result=='success') {
                    swal("注册成功", "请前往邮箱认证激活");
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
