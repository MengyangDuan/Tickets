<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>

</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="title" ><a href="/">Tickets</a></div>
<div class="role">
    <a href="/user/login">用户版</a>
    <a href="/boss/login">管理员版</a>
</div>
<div  class="middle" >
    <form action="/venue/login" method="POST">
        <div class="input venueName">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-user"></span>
                <input required='true' id='id' name='id' type="text" class="form-control" placeholder="企业id" aria-describedby="basic-addon1">
            </span>
            <%--<span id="nameMsg" class="help-inline">asd</span>--%>

        </div>

        <div class="input password">
        <span class="input-group col-lg-12">
            <span class="input-group-addon glyphicon glyphicon-lock"></span>
            <input required='true' id='password' name='password' type="password" class="form-control" placeholder="密码" aria-describedby="basic-addon1">
        </span>
            <span id="passwordMsg" class="help-inline"></span>
        </div>
        <div class="btn-container ">
            <input id="btnLogin"  class="btn-large btn btn-primary" type="submit" value="登录"/>
            <a class="btn btn-default" href="/venue/register" >去注册</a>
        </div>

    </form>

</div>




<%@include file="../common/tail.jsp" %>
</body>
</html>