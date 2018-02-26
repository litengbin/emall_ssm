<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/17
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>用户管理</title>
<div class="workingArea">
    <h1 class="label label-info">用户管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover text-center">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">用户名称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${us}" var="u">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>