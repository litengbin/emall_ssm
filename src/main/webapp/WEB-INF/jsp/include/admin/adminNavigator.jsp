<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/12
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="text-center top">
        <div class="navbar-brand" style="margin-top: -1px;">
            <span class="glyphicon glyphicon-home blueColor"></span>
            首页
            <%--<span style="color: #999">Welcome</span>--%>
        </div>
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
