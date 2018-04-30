<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/6
  Time: 下午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/blockTitleAndText.css"/>
    <link type="text/css" rel="stylesheet" href="/css/search.css"/>
    <link rel="stylesheet" href="/css/userinfo.css">
</head>

<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="container" >
    <div class="row">
        <div class=" col-xs-10 col-sm-10  col-md-6  col-lg-6 col-md-offset-3  col-lg-offset-3 col-xs-offset-1  " >
            <div class="row">
                <div class=" col-xs-12 col-sm-10  col-md-10  col-lg-10 media-list-base " id="point">
                    <p>支付</p>
                    <div >
                        <p id="money" style="display: inline">支付金额: <span id="payMoney"></span></p>
                        <p id="account">支付账号: <span id="accountId"></span></p>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon" >密码</span>
                        <input type="password" class="form-control"  id="accountPassword">
                    </div>
                </div>
            </div>
            <div id="button-group">
                <button type="button" class="btn btn-primary btn-block create-base btn-base" id="create-btn">支付</button>
                <button type="button" class="btn btn-default btn-block create-base btn-base" id="create-cancle">取消</button>
            </div>
        </div>
    </div>
    <div id="msg-book" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/user/payOrder.js"></script>
</body>
</html>
