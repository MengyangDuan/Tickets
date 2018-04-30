<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/5
  Time: 下午7:39
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
            <div class="row">
                <div class=" col-xs-12 col-sm-10  col-md-10  col-lg-10 media-list-base " id="point">
                    <p>基本信息</p>
                    <div class="input-group">
                        <span class="input-group-addon" >场馆名</span>
                        <input type="text" class="form-control"  id="name">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon" >地址</span>
                        <input type="text" class="form-control"  id="address">
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon" >邮箱</span>
                        <input type="email" class="form-control"  id="email">
                    </div>

                    <p style="font-size: small;margin-bottom: 0">请用字母表示座位，用'_'表示走廊绘制座位图。不同类型座位用不同字母来表示</p>
                    <div class="input-group" style="margin-top: 0">
                        <span class="input-group-addon" >座位图</span>
                        <textarea type="email" class="form-control"  id="seat"></textarea>
                    </div>

                    <div id="button-group">
                        <button type="button" class="btn btn-primary btn-block create-base btn-base" id="create-btn">保存</button>
                        <button type="button" class="btn btn-default btn-block create-base btn-base" id="create-cancle">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="../common/tail.jsp" %>
<script type="text/javascript" src="../../js/venue/modifyInfo.js"></script>
</body>
</html>
