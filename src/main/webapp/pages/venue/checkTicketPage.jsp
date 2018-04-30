<%--
  Created by IntelliJ IDEA.
  User: duanmengyang
  Date: 2018/3/15
  Time: 上午10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../common/head.jsp" %>
    <%@include file="component/navigation.jsp" %>
    <link type="text/css" rel="stylesheet" href="/css/blockTitleAndText.css"/>
    <link type="text/css" rel="stylesheet" href="/css/search.css"/>
    <link rel="stylesheet" href="/css/userinfo.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class=" col-xs-12 col-sm-10  col-md-10  col-lg-10 media-list-base " id="point">

        <div class=" col-xs-10 col-sm-10  col-md-6  col-lg-6 col-md-offset-3  col-lg-offset-3 col-xs-offset-1  " >
            <span>票务id：</span><input type="text" id="ticketId" name="ticketId">
            <input type="submit" class="btn btn-primary btn-block create-base btn-base" value="确定" onclick="checkTicket()">
        </div>
        </div>
    </div>
</div>
<%@include file="../common/tail.jsp" %>
<script>
    function checkTicket(){
        $.ajax({
            url:'/venue/checkTicket',
            type:'post',
            data:{ticketId:$('#ticketId').val()},
            success:function (data) {
                if(data.result=='success'){
                    swal('成功！','检票成功','success');
                    $('#ticketId').val('');
                }
                else
                    swal('失败！','检票失败','error');
            }
        });
    }
</script>
</body>
</html>
