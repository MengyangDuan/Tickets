<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/6
  Time: 下午12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/addCoupon.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="container" >
    <div class="row">
        <div class=" col-xs-12 col-sm-10  col-md-10  col-lg-10 media-list-base " id="point">
            <h2>您当前的积分:<span id="scoreOriginal"></span></h2>
            <h2>您当前的优惠卷:</h2>
            <div id="couponLeft"></div>
            <h2>可兑换的优惠卷</h2>
            <div id="availableCoupon"></div>
        </div>
    </div>
</div>

<div id="message" class="msg alert alert-success " role="alert"></div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/user/exchangeCoupon.js"></script>

</body>
</html>
