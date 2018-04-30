<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/14
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/pages/common/head.jsp" %>
    <link rel="stylesheet" href="/css/bossAnalyze.css"/>
</head>
<body>
<%@include file="/pages/common/header.jsp" %>
<%@include file="component/navigation.jsp" %>

<div class="big-container ">
    <div class="col-lg-6 col-md-6">
        <h2>消费列表</h2>
        <table id="payTable" class="col-lg-9 table table-striped">
        </table>
    </div>
    <div class="col-lg-6 col-md-6 ">
        <h2>预订列表</h2>
        <table id="bookTable" class="col-lg-9 table table-striped">
        </table>
    </div>
</div>

<%@include file="/pages/common/tail.jsp" %>

<script type="text/javascript" src="/js/manager/vipDetailPage.js"></script>

</body>
</html>
