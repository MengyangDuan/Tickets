<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/lib/jquery.seat-charts.css"/>
    <link type="text/css" rel="stylesheet" href="../../css/vip_selfPanel.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<div class="container">
    <div id="seat-map">
        <div class="front">座位</div>
    </div>
    <div id="venueInfo">
        <h2> 基本信息：</h2>
        <a href="/venue/modify"><i class="glyphicon glyphicon-pencil"></i></a>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-heart-empty"></i>
            场馆名：<span id="name"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-map-marker"></i>
            地址：<span id="address"></span>
        </div>
        <div  class="vip-self-li">
            <i class="glyphicon glyphicon-envelope"></i>
            企业邮箱：<span id="email"></span>
        </div>
    </div>
</div>
<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/lib/jquery.seat-charts.min.js"></script>
<script type="text/javascript" src="../../js/venue/venueInfo.js"></script>
</body>
</html>
