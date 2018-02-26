<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/13
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<title>editCategory</title>
<script>
    $(function () {
        $("#editForm").submit(function () {
            if (!checkEmpty("name", "分类名称")) {
                return false
            }
            return true;
        });
    })
</script>
<title>编辑分类</title>
<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li class="active">编辑分类</li>
    </ol>
    <div class="panel panel-info editDiv">
        <div class="panel-heading" align="center">编辑分类</div>
        <div class="panel-body">
            <form action="admin_category_update" method="post" id="editForm" enctype="multipart/form-data">
                <table class="editTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" value="${c.name}" type="text" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="categoryPic" name="image" type="file" accept="image/*"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${c.id}"/>
                            <button type="submit" class="btn btn-primary">提交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>
