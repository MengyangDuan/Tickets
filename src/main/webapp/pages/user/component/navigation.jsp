
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse navbar-fixed-top" >
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Tickets | 会员</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/user/index" >首页</a></li>
                <li><a href="/user/activityList">活动列表</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        个人信息
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/modify">基本信息</a></li>
                        <li><a href="/user/exchangeCoupon" >优惠卷兑换</a></li>
                    </ul>
                </li>
                <li><a href="/user/OrderList">订单信息</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        其他设置
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/user/logout">退出登录</a></li>
                        <li><a href="#" onclick="alert(this)">注销账户</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<script>
function alert(element) {
    swal({    title: "确定注销吗？",
        text: "你将无法再使用此账号登陆，无法恢复！",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定注销！",
        closeOnConfirm: false },
        function(){
            $.ajax({
                type: 'post',
                url:'/user/cancel',
                success:function (data) {
                    swal("注销！", "你的账号已注销。", "success");
                    location.replace("/user/login");
                }
            })
    });
}
</script>
