<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/15
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $("#editForm").submit(function () {
            if (!checkEmpty("name", "属性名称")) {
                return false;
            }
            return true;
        });
    })
</script>
<title>编辑属性</title>
<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">编辑属性</li>
    </ol>
    <div class="panel panel-info editDiv">
        <div class="panel-heading text-center">编辑属性</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_property_update">
                <table class="editTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input id="name" name="name" value="${p.name}" type="text" class="form-control"/></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="hidden" name="cid" value="${p.category.id}">
                            <button type="submit" class="btn btn-primary">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>