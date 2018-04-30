<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/6
  Time: 下午12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>
<div class="big-container ">
    <div class="col-lg-9 col-md-9">
        <div class="flex-container">
            <button id="btn_nopay" class="btn btn-primary">未付款</button>
            <button id="btn_pre" class="btn btn-primary">预定</button>
            <button id="btn_cancel" class="btn btn-primary">退订</button>
            <button id="btn_cousumed" class="btn btn-primary">消费</button>
        </div>
        <table id="table" class="table table-striped">
        </table>
    </div>
    <div id="msg" class="msg alert alert-success " role="alert"></div>
</div>


<%@include file="../common/tail.jsp" %>

<script type="text/javascript" src="../../js/user/bookListPage.js"></script>
</body>
</html>
