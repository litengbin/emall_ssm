<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="categoryProducts">
    <c:forEach items="${c.products}" var="p" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="productUnit" price="${p.promotePrice}">
                <div class="productUnitFrame">
                    <a href="foreproduct?pid=${p.id}">
                        <img class="productImage" src="img/productSingle_middle/${p.firstProductImage.id}.jpg">
                    </a>
                    <span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promotePrice}"
                                                                  minFractionDigits="2"/></span>
                    <strong>
                    <a class="productLink text-muted" href="foreproduct?pid=${p.id}">
                            ${fn:substring(p.name, 0, 50)}
                    </a>
                    <a class="emallLink" href="foreproduct?pid=${p.id}">查看产品</a>
                    <div class="show1 productInfo">
                        <span class="monthDeal pull-left">月成交 <span
                                class="productDealNumber">${p.saleCount}笔</span></span>
                        <span class="productReview pull-right">评价&nbsp;<span
                                class="productReviewNumber">${p.reviewCount}</span></span>
                    </div>
                    </strong>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear:both"></div>
</div>