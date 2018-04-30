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
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>
<%@include file="component/navigation.jsp" %>


<div class="big-container ">
    <div class="col-lg-2 col-md-2 col-xs-2">
        <ul class="nav nav-pills nav-stacked">
            <li id="vipLevel"><a href="#">会员概况</a></li>
            <li id="guest"><a href="#">会员比例</a></li>

        </ul>
    </div>
    <div class="col-lg-10 col-md-10 col-xs-10">
        <%--<div id="container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>--%>
        <div id="levelChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="monthChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
    </div>
</div>

<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="/js/manager/analyseVipsPage.js"></script>


</body>
</html>
