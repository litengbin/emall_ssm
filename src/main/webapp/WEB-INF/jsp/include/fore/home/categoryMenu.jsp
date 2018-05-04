<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="categoryMenu">
    <c:forEach items="${cs}" var="c">
        <div cid="${c.id}" class="eachCategory">
            <span class="glyphicon glyphicon-cutlery blueColor"></span>
            <a href="forecategory?cid=${c.id}">
                    <strong>${c.name}</strong>
            </a>
        </div>
    </c:forEach>
</div>