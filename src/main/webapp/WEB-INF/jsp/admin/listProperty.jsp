<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/15
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $("#addForm").submit(function () {
            if (checkEmpty("name", "属性名称")) {
                return true;
            }
            else {
                return false;
            }
        });
    });
</script>
<title>属性管理</title>
<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">属性管理</li>
    </ol>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover text-center">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">属性名称</th>
                <th class="text-center">编辑</th>
                <th class="text-center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td><a href="admin_property_edit?id=${p.id}">
                        <span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="admin_property_delete?id=${p.id}">
                        <span class="glyphicon glyphicon-trash"></span>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-info addDiv">
        <div class="panel-heading" align="center">新增属性</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_property_add">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td>
                            <input id="name" name="name" type="text" class="form-control">
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-primary">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>

