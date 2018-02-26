<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/17
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java"
        pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<title>编辑产品属性值</title>
<script>
    $(function () {
        $("input.form-control").keyup(function () {
            var value = $(this).val();
            var page = "admin_propertyValue_update";
            var pvid = $(this).attr("pvid");
            var parentSpan = $(this).parent("span");
//            parentSpan.css("border", "1px solid yellow");
            $.post(
                page,
                {"value": value, "id": pvid},
                function (result) {
                    if ("success" == result) {
                        parentSpan.css("border", "1px solid green");
                        console.log("success");
                    }
                    else {
                        parentSpan.css("border", "1px solid red");
                        console.log("false");
                    }
                }
            );
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
        <li class="active">编辑产品属性</li>
    </ol>
    <div class="editPVDiv">
        <span style="display: block;margin-left: 300px;" class="text-danger">移开鼠标即可修改</span>
        <br/>
        <c:forEach items="${pvs}" var="pv">
            <div class="eachPV">
                <span class="pvName">${pv.property.name}</span>
                <span class="pvValue"><input class="form-control" pvid="${pv.id}" type="text"
                                             value="${pv.value}"></span>
            </div>
        </c:forEach>
        <div style="clear:both"></div>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>