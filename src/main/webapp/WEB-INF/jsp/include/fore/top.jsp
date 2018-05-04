<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script>
$(function(){

    $(".top_middle").css("width", window.innerWidth/2 + 65);
    }
);
</script>

<nav class="top navbar navbar-default navbar-fixed-top">
    <div class="top_middle ">
        <a href="forehome">
            <span style="margin:0px" class="glyphicon glyphicon-home blueColor"></span>
            首页
        </a>
        <span style="color: #999">Welcome Shopping</span>
        <c:if test="${!empty user}">
            <a href="loginPage">${user.name}</a>
            <a href="forelogout">退出</a>
        </c:if>
        <c:if test="${empty user}">
            <a href="loginPage">请登录</a>
            <a href="registerPage">免费注册</a>
        </c:if>
        <span class="pull-right">
            <a href="forebought">我的订单</a>
            <a href="forecart">
            <span style="margin:0px" class=" glyphicon glyphicon-shopping-cart blueColor"></span>
            购物车<strong>${cartTotalItemNumber}</strong>件</a>
        </span>
    </div>
</nav>
