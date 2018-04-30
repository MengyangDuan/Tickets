<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="./common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
</head>
<body>
<%@include file="common/header.jsp" %>
<div class="title" ><a href="/">Hostel World</a></div>
<div  class="middle" >
    <form action="/login" method="POST">
        <div class="input userName">
            <span class="input-group col-lg-12">
                <span class="input-group-addon glyphicon glyphicon-user"></span>
                <input required='true' id='userName' name='userName' type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
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
            <a class="btn btn-default" href="/register" >去注册</a>
        </div>

    </form>

</div>



<%--<!-- 新 Bootstrap 核心 CSS 文件 -->--%>
<%--<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
<%--<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->--%>
<%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

<%--<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->--%>
<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
<%@include file="./common/tail.jsp" %>
</body>
</html>