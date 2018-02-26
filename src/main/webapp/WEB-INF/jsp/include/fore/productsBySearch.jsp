<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="searchProducts">

    <c:forEach items="${ps}" var="p">
    <div class="productUnit" price="${p.promotePrice}">
        <a href="foreproduct?pid=${p.id}">
            <img class="productImage" src="img/productSingle/${p.firstProductImage.id}.jpg">
        </a>
        <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}"
                                                      minFractionDigits="2"/></span>
        <a class="productLink" href="foreproduct?pid=${p.id}">
                ${fn:substring(p.name, 0, 50)}
        </a>
        <a class="tmallLink" href="foreproduct?pid=${p.id}">查看产品</a>

        <div class="productInfo">
            <span class="monthDeal pull-left">月成交 <span class="productDealNumber">${p.saleCount}笔</span></span>
            <span class="productReview pull-right">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
        </div>
    </div>
    </c:forEach>
    <c:if test="${empty ps}">
    <div class="noMatch">没有满足条件的产品
        <div>
            </c:if>
            <div style="clear:both"></div>
        </div>
    </div>