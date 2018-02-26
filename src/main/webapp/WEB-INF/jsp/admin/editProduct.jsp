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
<title>编辑产品</title>

<script>
    $(function () {
        $("#editForm").submit(function () {
            if (!checkEmpty("name", "产品名称"))
                return false;
//          if (!checkEmpty("title", "小标题"))
//              return false;
            if (!checkNumber("initialPrice", "原价格"))
                return false;
            if (!checkNumber("promotePrice", "优惠价格"))
                return false;
            if (!checkInt("inventory", "库存"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">编辑产品</li>
    </ol>

    <div class="panel panel-info editDiv">
        <div class="panel-heading text-center">编辑产品</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_product_update">
                <table class="editTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" value="${p.name}" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="title" name="title" type="text" value="${p.title}" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="initialPrice" value="${p.initialPrice}" name="initialPrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="${p.promotePrice}" name="promotePrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="inventory" value="${p.inventory}" name="stock" type="text" class="form-control">
                        </td>
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