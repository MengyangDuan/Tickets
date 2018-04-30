<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
    <link type="text/css" rel="stylesheet" href="/css/blockTitleAndText.css"/>
    <link type="text/css" rel="stylesheet" href="/css/search.css"/>
    <link rel="stylesheet" href="/css/userinfo.css">

</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="title" ><a href="/">Tickets</a></div>
<div class="role">
    <a href="/user/login">用户版</a>
    <a href="/boss/login">管理员版</a>
</div>
<div class="container" >
    <div class="row">
        <form id="registerForm">
            <div class=" col-xs-10 col-sm-10  col-md-6  col-lg-6 col-md-offset-3  col-lg-offset-3 col-xs-offset-1  " >
                <div class="input-group">
                    <span class="input-group-addon" >场馆名</span>
                    <input type="text" class="form-control"  id="venueName">
                </div>

                <div class="input-group">
                    <span class="input-group-addon" >地址</span>
                    <input type="text" class="form-control"  id="address">
                </div>

                <div class="input-group">
                    <span class="input-group-addon" >邮箱</span>
                    <input type="email" class="form-control"  id="email">
                </div>

                <div class="input-group">
                    <span class="input-group-addon" >密码</span>
                    <input type="password" class="form-control"  id="password">
                </div>

                <div class="input-group">
                    <span class="input-group-addon" >确认密码</span>
                    <input type="password" class="form-control"  id="repassword">
                </div>

                <div class="input-group" style="margin-top: 0">
                    <span class="input-group-addon" >座位图</span>
                    <textarea type="email" class="form-control"  id="seat" placeholder="请用字母表示座位，用'_'表示走廊绘制座位图。不同类型座位用不同字母来表示"></textarea>
                </div>

                <div class="btn-container">
                    <input type="submit" class="btn-large btn btn-primary" value="注册"/>
                    <a href="/venue/login" class="btn btn-default">去登录</a>
                </div>
            </div>
        </form>
    </div>
</div>
<%--<div  class="middle-register">--%>
    <%--<form id="registerForm" >--%>
        <%--<div class="input venueName">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-user"></span>--%>
                <%--<input required='true' id='venueName' name='venueName' type="text" class="form-control" placeholder="场馆名" aria-describedby="basic-addon1">--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="input address">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-user"></span>--%>
                <%--<input required='true' id='address' name='address' type=address" class="form-control" placeholder="地址" aria-describedby="basic-addon1">--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="input seat">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-user"></span>--%>
                <%--<textarea id='seat' name='seat'  class="form-control" placeholder="座位情况" aria-describedby="basic-addon1" required></textarea>--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="input email">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-user"></span>--%>
                <%--<input required='true' id='email' name='email' type="email" class="form-control" placeholder="邮箱，用于接收审核信息" aria-describedby="basic-addon1">--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="input password">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-lock"></span>--%>
                <%--<input required='true' id='password' name='password' type="password" class="form-control" placeholder="密码，用于登陆" aria-describedby="basic-addon1">--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="input repassword">--%>
            <%--<span class="input-group col-lg-12">--%>
                <%--<span class="input-group-addon glyphicon glyphicon-lock"></span>--%>
                <%--<input required='true' id='repassword' name='repassword' type="password" class="form-control" placeholder="再次输入密码" aria-describedby="basic-addon1">--%>
            <%--</span>--%>
        <%--</div>--%>

        <%--<div class="btn-container">--%>
            <%--<input type="submit" class="btn-large btn btn-primary" value="注册"/>--%>
            <%--<a href="/venue/login" class="btn btn-default">去登录</a>--%>
        <%--</div>--%>
    <%--</form>--%>
<%--</div>--%>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/venue/register.js"></script>

</body>
</html>
