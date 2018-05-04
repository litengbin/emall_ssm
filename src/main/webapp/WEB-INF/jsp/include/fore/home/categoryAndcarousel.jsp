<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<script>
    function showProductsAsideCategorys(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "white");
        $("div.eachCategory[cid=" + cid + "] a").css("color", "#87CEFA");
        $("div.productsAsideCategorys[cid=" + cid + "]").show();
    }

    function hideProductsAsideCategorys(cid) {
        $("div.eachCategory[cid=" + cid + "]").css("background-color", "#e2e2e3");
        $("div.eachCategory[cid=" + cid + "] a").css("color", "#337ab7");
        $("div.productsAsideCategorys[cid=" + cid + "]").hide();
    }

    $(function () {
        $("div.categoryMenu").hide();
        $("div.head").mouseenter(function () {
            $(".categoryMenu").show();
        });
        $("div.categoryMenu").mouseleave(function () {
            $(".categoryMenu").hide();
        });
        $("div.eachCategory").mouseenter(function () {
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.eachCategory").mouseleave(function () {
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseenter(function () {
            $(".categoryMenu").show();
            var cid = $(this).attr("cid");
            showProductsAsideCategorys(cid);
        });
        $("div.productsAsideCategorys").mouseleave(function () {
            $(".categoryMenu").hide();
            $("div.categoryMenu").mouseenter(function () {
                $(".categoryMenu").show();
            });
            var cid = $(this).attr("cid");
            hideProductsAsideCategorys(cid);
        });

        $(".dropdown-menu li").mouseenter(function () {
            $(this).css("background-color","#dedede");
        }).mouseleave(function(){
            $(this).css("background-color","white");
        });

        $("div.rightMenu span").mouseenter(function () {
            var left = $(this).position().left;
            var top = $(this).position().top;
            var width = $(this).css("width");
            var destLeft = parseInt(left) + parseInt(width) / 2;
            $("img#catear").css("left", destLeft);
            $("img#catear").css("top", top - 20);
            $("img#catear").fadeIn(500);
        });
        $("div.rightMenu span").mouseleave(function () {
            $("img#catear").hide();
        });
        $('.carousel').carousel({
            interval: 3000
        })
        var left = $("div#carousel-of-product").offset().left;
        $("div.categoryMenu").css("left", left - 20);
        $("div.categoryWithCarousel div.head").css("margin-left", left);
        $("div.productsAsideCategorys").css("left", left - 20);
    });
</script>
<%--<img src="img/site/catear.png" id="catear" class="catear"/>--%>

<div class="categoryWithCarousel">
    <div class="headbar show1">
        <div class="head ">
            <span style="margin:5px 0px 0px 10px;" class="glyphicon glyphicon-star"></span>
            <span style="margin-left:-2px;"><strong>商品分类</strong></span>
        </div>
        <div class="rightMenu" style="margin-left:5px;">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count<=6}">
                <span>
                <a href="forecategory?cid=${c.id}">
                 <strong>${c.name}</strong>
                </a></span>
                </c:if>
                <c:if test="${st.count >6}">
                    <c:if test="${st.count == 7}">
                        <ul class="nav navbar-nav navbar-right" style="margin-top: -15px;">
                        <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><strong>更多分类</strong><b class="caret"></b></a>
                        <ul class="dropdown-menu">
                    </c:if>
                    <li style="padding: 5px;"><span>
                    <a href="forecategory?cid=${c.id}"><strong style="color: #337ab7;">${c.name}</strong></a></span></li>
                    <c:if test="${st.end}"></ul></li>
                        </ul></c:if>
                    </c:if>
            </c:forEach>
        </div>
    </div>
        <div style="position: relative">
        <%@include file="categoryMenu.jsp" %>
    </div>
    <div style="position: relative;left: 0;top: 0;">
        <%@include file="productsAsideCategorys.jsp" %>
    </div>
    <%@include file="carousel.jsp" %>
    <div class="carouselBackgroundDiv">
    </div>
</div>
