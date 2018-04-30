
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/activityDetail.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="container ">
    <div class="row">
        <div class="col-lg-4 clo-md-4">
            <img src="../../img/poster0.jpeg">
        </div>
        <div class="col-lg-8 clo-md-8">
            <h1 id="name"></h1>
            <p>时间:<span id="time"></span></p>
            <p>地址:<span id="address"></span></p>
            <p>简介:<span id="description"></span></p>
            <div class="seatType">
                <span class="price">价格：</span>
            </div>
            <div class="seatNum">
                <h3 hidden id="num">数量：</h3>
            </div>
            <div id="button-group">
                <button type="button" class="btn btn-primary btn-block create-base btn-base" id="directOrder">直接预定</button>
                <button type="button" class="btn btn-default btn-block create-base btn-base" id="selectSeat">选座预定</button>
            </div>
        </div>
    </div>
    <div id="msg" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>

<script type="text/javascript" src="../../js/user/activityDetailPage.js"></script>

</body>
</html>
