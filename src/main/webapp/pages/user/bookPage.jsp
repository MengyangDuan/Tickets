<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/addCoupon.css"/>

</head>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="container ">
    <div class="col-lg-9 clo-md-9">
        <h1 id="activityName"></h1>
        <div class="row">
            <div class=" orderInfo col-lg-5 col-md-5">
                <h2>时间：<span id="time" ></span></h2>
                <h2>地址：<span id="address" ></span></h2>
                <h2>座位：<span id="seat" ></span></h2>
                <h2>原价：<span id="originalPrice" ></span></h2>
                <h2>会员价：<span id="afterDiscountPrice" ></span></h2>
                <h2>可用优惠卷：<div id="couponLeft" style="width: 1000px"></div></h2>
                <h2>实付款：<span id="payPrice" ></span></h2>
                <a class="btn btn-primary" style="margin-top: 15px" id="confirm" onclick="confirm()">去付款</a>
            </div>
        </div>
    </div>
    <div id="msg-book" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/user/bookPage.js"></script>
</body>
</html>
