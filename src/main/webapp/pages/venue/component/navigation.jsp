<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="sideBar.jsp" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">HostelWorld | 场馆</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/venue/info">基本信息</a></li>
                <li><a href="/venue/releaseActivity">发布计划</a></li>
                <li><a href="/venue/activityList">现场购票</a></li>
                <li><a href="/venue/checkTickets">检票登记</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        统计分析 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li> <a href="/venue/analyzeBook">预订情况</a></li>
                        <li> <a href="/venue/analyzePay">收入情况</a></li>
                    </ul>
                </li>

                <li><a class="btn btn-primary" href="/venue/logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>
