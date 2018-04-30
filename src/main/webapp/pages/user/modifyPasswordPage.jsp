<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/5
  Time: 下午7:40
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

            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" ><a href="/user/modify"> 基本信息</a></li>
                <li role="presentation" class="active"><a href="/user/modifyPassword">账号安全</a></li>
                <li role="presentation" ><a href="/user/userAccount">账户信息</a></li>
            </ul>
            <div class="row">
                <div class=" col-xs-12 col-sm-10  col-md-10  col-lg-10 media-list-base " id="point">
                    <p>账号安全</p>
                    <div class="input-group">
                        <span class="input-group-addon" >当前密码</span>
                        <input type="password" class="form-control" id="oldpass">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon" >新密码</span>
                        <input type="password" class="form-control"  id="newpass">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon">再输一次</span>
                        <input type="password" class="form-control" id="newpass1" >
                    </div>
                    <div id="button-group">
                        <button type="button" class="btn btn-primary btn-block create-base btn-base" id="create-btn">保存</button>
                        <button type="button" class="btn btn-default btn-block create-base btn-base" id="create-cancle">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../common/tail.jsp" %>
    <script type="text/javascript" src="../../js/user/modifyPassword.js"></script>
</div>
</body>
</html>
