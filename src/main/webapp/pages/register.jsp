<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="./common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
    <link rel="stylesheet" href="/css/lib/sweetalert.css">

</head>
<body>
<%@include file="common/header.jsp" %>
<div class="title" ><a href="/">Hostel World</a></div>

<div  class="middle-register">
    <form id="registerForm" >
        <%--<span class="label label-default">选择注册身份</span>--%>

        <div class="btn-group choice-container" data-toggle="buttons">
            <label class="btn btn-primary active">
                <input type="radio" name="options" value="vip" autocomplete="off" checked> 会员
            </label>
            <label class="btn btn-primary">
                <input type="radio" name="options" value="hostel" autocomplete="off"> 客栈
            </label>
        </div>


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
            <a href="/login" class="btn btn-default">去登录</a>
        </div>
    </form>
</div>


<%@include file="./common/tail.jsp" %>
<script type="text/javascript" src="../js/login.js"></script>
<script type="text/javascript" src="../js/lib/sweetalert.min.js"></script>

</body>
</html>
