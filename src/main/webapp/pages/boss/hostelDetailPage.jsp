<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 01:25
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
    <div class="col-lg-9 col-md-9">
        <table id="table" class="col-lg-9 table table-striped">
        </table>
    </div>
    <div class="col-lg-3 col-md-3 main-container">
        <div>
            <h3><div class="label label-warning">      住店累计人数</div></h3>
            <div id="total" class="middle-circle"></div>
        </div>

        <div>
            <h3><div class="label label-warning">      当前住店人数</div></h3>
            <div id="now" class="middle-circle"></div>
        </div>

    </div>

    <div class="col-lg-3 col-md-3 main-container">

    </div>
</div>

<%@include file="/pages/common/tail.jsp" %>

<script type="text/javascript" src="/js/manager/hostelDetailPage.js"></script>

</body>
</html>
