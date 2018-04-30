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
        <h4>收支情况</h4>
        <ul class="nav nav-pills nav-stacked">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    vip收入/总收入 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="year"><a href="#">年</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    人均消费 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li id="avg_year"><a href="#">年</a></li>
                </ul>
            </li>

        </ul>
    </div>

    <div class=" col-lg-10 col-md-10 col-xs-10">

        <%--<div id="container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>--%>
        <div id="incomeChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>
        <div id="avgChart-container" style="height: 500px; min-width: 310px; max-width: 1200px; margin: 0 auto"></div>

    </div>
</div>

<%@include file="../common/tail.jsp" %>
<%@include file="component/hostelTail.jsp"%>

<script type="text/javascript" src="../../js/hostel/analyzePayBillPage.js"></script>


</body>
</html>
