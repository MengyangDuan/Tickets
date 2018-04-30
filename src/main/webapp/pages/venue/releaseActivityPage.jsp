<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/8
  Time: 下午4:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <%@include file="component/navigation.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/lib/jquery.seat-charts.css"/>
    <link type="text/css" rel="stylesheet" href="../../css/vip_selfPanel.css"/>
</head>
<body>
<div class="container">
    <div id="seat-map">
        <div class="front">座位图</div>
    </div>
    <form id="modifyHostelInfoForm">
    <div class="price-details">
        <h2> 票价信息：</h2>
    </div>
        <div class="vip-self-li input-group input-group-sm">
            <span class="input-group-addon">活动名</span>
            <input id="name" name="name" type="text" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
        </div>
        <div class="vip-self-li input-group input-group-sm">
            <span class="input-group-addon">时间</span>
            <input id="time" name="time" type="text" class="form-control" placeholder="" aria-describedby="sizing-addon3" required>
        </div>
        <div class="vip-self-li input-group input-group-sm">
            <span class="input-group-addon">类型</span>
            <input type="radio" name="type" value="1" >音乐会
            <input type="radio" name="type" value="2">舞蹈
            <input type="radio" name="type" value="3">话剧
            <input type="radio" name="type" value="4">体育比赛
        </div>
        <div class="vip-self-li input-group input-group-sm">
            <label class="input-group-addon">描述</label>
            <textarea id="description" name="description"  class="form-control" placeholder="" aria-describedby="sizing-addon3" required></textarea>
        </div>
        <div class="vip-self-li input-group input-group-sm">
            <span class="photo">活动图片:</span>
            <input type="hidden" name="img"  id="thumbUrl"/>
            <input type="file" name="logoFile" id="logoFile" onchange="setImg(this);">
            <span><img id="thumburlShow" src="" width="120" height="120"/></span>
        </div>
        <div class="vip-self-li" style="float: right">
            <input type="submit" class="btn btn-primary" value="提交"/>
        </div>
    </form>
</div>
<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/lib/jquery.seat-charts.min.js"></script>
<script type="text/javascript" src="../../js/venue/releaseActivity.js"></script>
</body>
</html>
