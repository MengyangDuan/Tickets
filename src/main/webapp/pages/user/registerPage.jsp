<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
    <link rel="stylesheet" href="/css/lib/sweetalert.css">

</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="title" ><a href="/">Tickets</a></div>
<div class="role">
    <a href="/venue/login">场馆版</a>
    <a href="/boss/login">管理员版</a>
</div>
<div  class="middle-register">
    <form id="registerForm" >
        <div class="input userName">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-user"></span>
                <input required='true' id='userName' name='userName' type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
            </span>
            <%--<span id="nameMsg" class="help-inline">asd</span>--%>
        </div>

        <div class="input repassword">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-user"></span>
                <input required='true' id='email' name='email' type="email" class="form-control" placeholder="请输入邮箱" aria-describedby="basic-addon1">
            </span>
        </div>

        <div class="input password">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-lock"></span>
                <input required='true' id='password' name='password' type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
            </span>
        </div>

        <div class="input repassword">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-lock"></span>
                <input required='true' id='repassword' name='repassword' type="password" class="form-control" placeholder="再次输入密码" aria-describedby="basic-addon1">
            </span>
        </div>

        <div class="btn-container">
            <input type="submit" class="btn-large btn btn-primary" value="注册"/>
            <a href="/user/login" class="btn btn-default">去登录</a>
        </div>
    </form>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/login.js"></script>
<script type="text/javascript" src="../js/lib/sweetalert.min.js"></script>

</body>
</html>
