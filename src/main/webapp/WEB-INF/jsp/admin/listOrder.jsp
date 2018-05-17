<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/18
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="../include/admin/adminHeader.jsp" %>
<%@ include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $("button.orderPageCheckOrderItems").click(function () {
            var oid = $(this).attr("oid");
            console.log(oid);
            $("tr.orderPageOrderItemTR[oid=" + oid + "]").toggle();
        });
    });
</script>
<title>订单管理</title>
<div class="workingArea">
    <h1 class="label label-info">订单管理</h1>
    <br>
    <br>
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover text-center">
            <thead>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">状态</th>
                <th class="text-center">金额</th>
                <th class="text-center">商品数量</th>
                <th class="text-center">买家名称</th>
                <th class="text-center">创建时间</th>
                <th class="text-center">支付时间</th>
                <th class="text-center">发货时间</th>
                <th class="text-center">确认收货时间</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${os}" var="o">
                <tr>
                    <td width="4%">${o.id}</td>
                    <td width="7%">
                        <c:if test="${o.status=='finish'}">
                        <span class="label label-success">${o.statusDesc}</span>
                    </c:if>
                        <c:if test="${o.status=='waitPay'}">
                            <span class="label label-default">${o.statusDesc}</span>
                        </c:if>
                        <c:if test="${o.status=='waitDelivery'}">
                            <span class="label label-warning">${o.statusDesc}</span>
                        </c:if>
                        <c:if test="${o.status=='waitConfirm'}">
                            <span class="label label-primary">${o.statusDesc}</span>
                        </c:if>
                        <c:if test="${o.status=='waitReview'}">
                            <span class="label label-info">${o.statusDesc}</span>
                        </c:if>
                        <c:if test="${o.status=='delete'}">
                            <span class="label label-danger">${o.statusDesc}</span>
                        </c:if>


                    </td>
                    <td width="7%" class="text-danger" style="font-weight: bold">￥<fmt:formatNumber type="number" value="${o.total}"
                                           minFractionDigits="2"></fmt:formatNumber></td>
                    <td align="center" width="7%">${o.totalNumber}</td>
                    <td align="center" width="7%">${o.user.name}</td>
                    <td width="14%"><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td width="14%"><fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td width="14%"><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td width="14%"> <fmt:formatDate value="${o.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="text-align: left">
                        <button oid="${o.id}" class="orderPageCheckOrderItems btn btn-primary btn-sm" style="font-weight: bold;margin-left: 30px;">查看详情</button>
                        <c:if test="${o.status=='waitDelivery'}">
                            <a href="admin_order_delivery?id=${o.id}">
                                <button class="btn btn-danger btn-sm" style="font-weight: bold">快速发货</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR" oid="${o.id}">
                    <td colspan="10" align="center">
                        <div class="orderPageOrderItem">
                            <table width="800px" align="center" class="orderPageOrderItemTable">
                                <c:forEach items="${o.orderItems}" var="oi">
                                    <tr>
                                        <td align="left">
                                            <img width="40px" height="40px"
                                                 src="img/productSingle/${oi.product.firstProductImage.id}.jpg">
                                        </td>
                                        <td>
                                            <a href="foreproduct?pid=${oi.product.id}">
                                                <span>${oi.product.name}</span>
                                            </a>
                                        </td>
                                        <td align="right">
                                            <span class="text-muted">${oi.number}个</span>
                                        </td>
                                        <td align="right">
                                            <span class="text-danger" style="font-weight: bold">单价：￥${oi.product.promotePrice}</span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </td>
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

