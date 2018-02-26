<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/20
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="productDetailDiv">
    <div class="productDetailTopPart">
        <a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
        <a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span
                class="productDetailTopReviewLinkNumber">${p.reviewCount}</span> </a>
    </div>
    <div class="productParamterPart">
        <div class="productParamter">产品参数：</div>

        <div class="productParamterList">
            <c:forEach items="${pvs}" var="pv">
                <span>${pv.property.name}:  ${fn:substring(pv.value, 0, 10)} </span>
            </c:forEach>
        </div>
        <div style="clear:both"></div>
    </div>
    <div class="productDetailImagesPart">
        <c:forEach items="${p.productDetailImages}" var="pi" varStatus="vs">
            <img src="img/productDetail/${pi.id}.jpg">
        </c:forEach>
    </div>
</div>
