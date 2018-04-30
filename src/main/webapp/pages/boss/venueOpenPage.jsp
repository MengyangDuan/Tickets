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
    <div class="col-lg-2 col-md-2">
        <%@include file="component/sideBar.jsp" %>
    </div>
    <div class="col-lg-10 col-md-10">
        <h3>场馆信息审核</h3>
        <table id="requestTable" class="col-lg-9 table table-striped">
        </table>
    </div>

</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/manager/requestPage.js"></script>


</body>
</html>
