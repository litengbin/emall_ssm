<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<title>官网-${c.name}</title>
<div id="category">
    <div class="categoryPageDiv">
        <img src="img/category/${c.id}.jpg">
        <%@include file="sortBar.jsp" %>
        <%@include file="productsByCategory.jsp" %>
    </div>
</div>
