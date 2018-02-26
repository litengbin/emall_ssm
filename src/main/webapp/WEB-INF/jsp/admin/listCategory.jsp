<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/12
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $("#addForm").submit(function () {
            if (!checkEmpty("name", "分类名称"))
                return false;
            if (!checkEmpty("categoryPic", "分类图片"))
                return false;
            return true;
        });
    });
</script>
<title>分类管理</title>
<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed text-center">
            <thead >
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">图片</th>
                <th class="text-center">分类名称</th>
                <th class="text-center">属性管理</th>
                <th class="text-center">产品管理</th>
                <th class="text-center">编辑</th>
                <th class="text-center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cs}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td style="text-align: left"><img height="40px" src="img/category/${c.id}.jpg"></td>
                    <td>${c.name}</td>
                    <td><a href="admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a>
                    </td>
                    <td><a href="admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a>
                    </td>
                    <td><a href="admin_category_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="admin_category_delete?id=${c.id}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
    <div class="panel panel-info addDiv">
        <div class="panel-heading" align="center">新增分类</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_category_add" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td>
                            <input id="categoryPic" accept="image/*" type="file" name="image"/>
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-primary">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>
