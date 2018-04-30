<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/12
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/lib/jquery.seat-charts.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<div class="wrapper">
    <div class="container">
        <div id="seat-map">
            <div class="front-indicator">前部</div>
        </div>
        <div class="booking-details" style="margin-right: 50px">
            <h3>已选中的座位:</h3>
            <ul id="cart"></ul>
            <p>票数: <span id="counter">0</span></p>
            <p>总计（原价）: ￥<span id="total">0</span></p>
            <div id="legend"></div>
            <button class="btn btn-primary" id="submit">确定</button>
        </div>
        <%--<a href="#" id="submit" role="button">确定</a>--%>
    </div>
</div>
<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/lib/jquery.seat-charts.min.js"></script>
<script type="text/javascript" src="../../js/user/selectSeat.js"></script>
<script type="text/javascript" src="../js/lib/sweetalert.min.js"></script>
</body>
</html>