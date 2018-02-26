<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/24
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="confirmPayPageDiv">
    <div class="confirmPayImageDiv">
        <div class="progress" style="margin: 60px auto;">
            <div class="progress-bar progress-bar-striped active" style="width: 80%">
                购买商品进度：80%
            </div>
            <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 20%">
                剩余进度：20%
            </div>
        </div>
    </div>
    <div class="confirmPayOrderInfoDiv">
        <div class="confirmPayOrderInfoText">我已收到货，同意支付宝付款</div>
    </div>
    <div class="confirmPayOrderItemDiv">
        <div class="confirmPayOrderItemText">订单信息</div>
        <table class="confirmPayOrderItemTable table table-hover table-condensed">
            <thead>
            <th colspan="2">宝贝</th>
            <th width="120px">单价</th>
            <th width="120px">数量</th>
            <th width="120px">商品总价</th>
            <th width="120px">运费</th>
            </thead>
            <c:forEach items="${o.orderItems}" var="oi">
                <tr>
                    <td style="text-align: left"><img width="50px"
                                                      src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
                    </td>
                    <td class="confirmPayOrderItemProductLink">
                        <a href="#nowhere">${oi.product.name}</a>
                    </td>
                    <td style="vertical-align: middle" class="text-muted text-center">￥<fmt:formatNumber type="number"
                                                                                                         value="${oi.product.initialPrice}"
                                                                                                         minFractionDigits="2"/></td>
                    <td style="vertical-align: middle" class="text-muted text-center">1</td>
                    <td style="vertical-align: middle" class="text-muted text-center"><span
                            class="conformPayProductPrice">￥<fmt:formatNumber type="number"
                                                                              value="${oi.product.promotePrice}"
                                                                              minFractionDigits="2"/></span></td>
                    <td style="vertical-align: middle" class="text-muted text-center"><span>快递 ： 0.00 </span></td>
                </tr>
            </c:forEach>
        </table>
        <div class="confirmPayOrderItemText pull-right">
            实付款： <span class="confirmPayOrderItemSumPrice">￥<fmt:formatNumber type="number" value="${o.total}"
                                                                              minFractionDigits="2"/></span>
        </div>
    </div>
    <div class="confirmPayOrderDetailDiv">
        <table class="confirmPayOrderDetailTable table table-striped table-bordered table-hover table-condensed text-muted">
            <tr>
                <td>订单编号：</td>
                <td>${o.orderCode}</td>
            </tr>
            <tr>
                <td row>卖家昵称：</td>
                <td>${o.user.name}</td>
            </tr>
            <tr>
                <td>收货信息：</td>
                <td>${o.address}，${o.receiver}， ${o.mobile}，${o.post} </td>
            </tr>
            <tr>
                <td>成交时间：</td>
                <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </table>
    </div>
    <div class="confirmPayButtonDiv">
        <div class="confirmPayWarning text-danger">请收到货后，再确认收货！否则您可能钱货两空！</div>
        <a href="foreorderConfirmed?oid=${o.id}">
            <button class="confirmPayButton btn-primary btn">确认支付</button>
        </a>
    </div>
</div>