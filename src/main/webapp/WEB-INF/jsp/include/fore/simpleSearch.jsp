<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="simpleSearchOutDiv">
    <form action="foresearch" method="post">
        <div class="simpleSearchDiv pull-right">
            <input type="text" placeholder="测试产品1  测试产品2" name="keyword" class="form-control pull-left">
            <button class="searchButton btn btn-primary" type="submit">搜索</button>
            <div class="searchBelow" style="clear: both">
                <c:forEach items="${cs}" var="c" varStatus="st">
                    <c:if test="${st.count>=8 and st.count<=10}">
                    <span>
                        <a href="forecategory?cid=${c.id}">
                                ${c.name}
                        </a>
                        <c:if test="${st.count!=10}">
                            <span>|</span>
                        </c:if>
                    </span>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </form>
    <div style="clear:both"></div>
</div>