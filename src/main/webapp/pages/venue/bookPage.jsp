<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="../../css/bookPage.css"/>

</head>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>


<div class="container ">
    <div class="col-lg-9 clo-md-9">
        <h1 id="activityName"></h1>
        <div class="row">
            <div class=" orderInfo ">
                <h2>时间：<span id="time" ></span></h2>
                <h2>地址：<span id="address" ></span></h2>
                <h2>座位：<span id="seat" ></span></h2>
                <h2>原价：<span id="originalPrice"></span></h2>
                <h2 id="vipPrice" hidden>会员价：<span id="afterDiscountPrice"></span></h2>
            </div>
            <div class="btn-group" style="display: block" >
                <input  type="radio" name="isVip" value="1">我是Tickets会员
                <input  type="radio" name="isVip" value="2">不是Tickets会员
            </div>
            <div id="ticketsInfo" hidden>
                <input id="userName" type="text" placeholder="请输入Tickets账号名">
                <input id="password" type="password" placeholder="请输入Tickets密码">
                <button type="button" id="loginButton">验证</button>
            </div>
            <div class="btn-group" style="margin-top: 30px">
                <button type="button" id="pay" class="btn btn-primary" style="margin-right: 15px;width: 100px">已付款</button>
                <button type="button" id="cancel" class="btn btn-primary" style="width: 100px">已取消</button>
            </div>
        </div>
    </div>
    <div id="msg-book" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/venue/bookPage.js"></script>
</body>
</html>
