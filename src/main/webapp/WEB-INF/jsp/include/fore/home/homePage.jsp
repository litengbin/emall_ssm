<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<title>官网</title>
<div class="homepageDiv">
    <div id="img" style="position: absolute; left: 311px; top: 815px;visibility :hidden;"
         onmouseover="clearInterval(interval)"
         onmouseout="interval = setInterval('changePos()', delay)"
         align="right">
        <a href="#" target="_blank">
            <img width="100px" height="100px;" src="img/lunbo/6.jpg"
                 onclick="javascript:window.open(this.src);"
                 style="CURSOR: pointer" onload="return imgzoom(this,550)">
        </a>
        <span style="CURSOR:hand;color:red;font-weight:bold;margin-top: 0px;"
              onclick="clearInterval(interval);img.style.visibility = 'hidden'">X</span>
    </div>
    <script language=javascript>
        var xPos = 20;//from alixixi.com
        var yPos = document.body.clientHeight;
        var step = 1;
        var delay = 30;
        var height = 0;
        var Hoffset = 0;
        var Woffset = 0;
        var yon = 0;
        var xon = 0;
        var pause = true;
        var interval;
        img.style.top = yPos;
        function changePos() {
            width = document.body.clientWidth;
            height = document.body.clientHeight;
            Hoffset = img.offsetHeight;
            Woffset = img.offsetWidth;
            img.style.left = xPos + document.body.scrollLeft;
            img.style.top = yPos + document.body.scrollTop;
            if (yon) {
                yPos = yPos + step;
            }
            else {
                yPos = yPos - step;
            }
            if (yPos < 0) {
                yon = 1;
                yPos = 0;
            }
            if (yPos >= (height - Hoffset)) {
                yon = 0;
                yPos = (height - Hoffset);
            }
            if (xon) {
                xPos = xPos + step;
            }
            else {
                xPos = xPos - step;
            }
            if (xPos < 0) {
                xon = 1;
                xPos = 0;
            }
            if (xPos >= (width - Woffset)) {
                xon = 0;
                xPos = (width - Woffset);
            }
        }
        function start() {
            img.style.visibility = "visible";
            interval = setInterval('changePos()', delay);
        }
        start();
    </script>

    <%@include file="categoryAndcarousel.jsp" %>
    <%@include file="homepageCategoryProducts.jsp" %>
</div>
