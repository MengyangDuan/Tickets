<%--
  Created by IntelliJ IDEA.
  User: disinuo
  Date: 17/3/4
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">HostelWorld | 总经理</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        审核场馆 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li> <a href="boss/checkVenue">新开</a></li>
                        <li> <a href="/boss/checkModify">更新</a></li>
                    </ul>
                </li>
                <li><a href="/boss/settleAccount">结算</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        统计分析 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li> <a href="/boss/analyzeVip">会员统计</a></li>
                        <li> <a href="/boss/analyzeVenue">场馆统计</a></li>
                        <li> <a href="/boss/analyzeCompany">财务统计</a></li>
                    </ul>
                </li>
                <li><a class="btn btn-primary" href="/boss/logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>
