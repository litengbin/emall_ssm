<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/12
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        var left = window.innerWidth / 2 - 300;
        $("div.top").css("margin-left", left);
    });
    $(function () {
        var width = window.innerWidth;
        $("div.workingArea").css("width", width - 100);
    });
</script>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="text-center top">
        <a class="navbar-brand" style="margin-top: -1px;" href="admin_order_list">
            <span class="glyphicon glyphicon-home blueColor"></span>
          首页
        </a>
        <c:if test="${!empty adminUser}">
            <a class="navbar-brand" href="adminloginPage">${adminUser.name}</a>
            <a class="navbar-brand" href="admin_logout">退出</a>
        </c:if>
        <c:if test="${empty adminUser}">
            <a class="navbar-brand" href="adminloginPage">请登录</a>
            <a class="navbar-brand" href="adminregisterPage">免费注册</a>
        </c:if>
        <a class="navbar-brand" href="admin_category_list">分类管理</a>
        <a class="navbar-brand" href="admin_user_list">用户管理</a>
        <a class="navbar-brand" href="admin_order_list">订单管理</a>
    </div>
</nav>
