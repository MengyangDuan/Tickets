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
    <div class="col-lg-9 col-md-9">
        <table id="moneyTable" class="col-lg-9 table table-striped">
        </table>
    </div>
    <div class="col-lg-3 col-md-3 main-container">
        <h2>总计：<b id="total"></b>元</h2>
        <form id="countForm">
            <br><br><br><br><br><br><br><br><br>
            <input type="submit" class="btn btn-primary" value="全部结算">
        </form>
    </div>
</div>




<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/manager/countPage.js"></script>


</body>
</html>
