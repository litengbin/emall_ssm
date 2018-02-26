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
//            if (!checkEmpty("title", "小标题"))
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
<title>产品管理</title>
<div class="workingArea">
    <h1 class="label label-info">分类管理</h1>
    <br>
    <br>
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">产品管理</li>
    </ol>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed text-center">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">图片</th>
                <th class="text-center">产品名称</th>
                <th class="text-center">产品标题</th>
                <th class="text-center">原价格</th>
                <th class="text-center">优惠价格</th>
                <th class="text-center">库存数量</th>
                <th class="text-center">图片管理</th>
                <th class="text-center">设置属性</th>
                <th class="text-center">编辑</th>
                <th class="text-center">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${!empty p.firstProductImage}">
                            <img width="40" src="img/productSingle/${p.firstProductImage.id}.jpg">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.title}</td>
                    <td>${p.initialPrice}</td>
                    <td>${p.promotePrice}</td>
                    <td>${p.inventory}</td>
                    <td><a href="admin_productImage_list?pid=${p.id}"><span class="glyphicon glyphicon-picture"></span></a>
                    </td>
                    <td><a href="admin_propertyValue_edit?pid=${p.id}"><span class="glyphicon glyphicon-th-list"></span></a>
                    </td>
                    <td><a href="admin_product_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="admin_product_delete?id=${p.id}"><span
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
        <div class="panel-heading" align="center">新增产品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_product_add">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="title" name="title" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="initialPrice" value="99.98" name="initialPrice" type="text"
                                   class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="19.98" name="promotePrice" type="text" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="inventory" value="99" name="inventory" type="text" class="form-control"></td>
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
