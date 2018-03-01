<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<form action="foresearch" method="post">
    <div class="searchDiv">
        <input name="keyword" type="text" placeholder="测试产品1  测试产品2" class="form-control pull-left">
        <button type="submit" class="btn btn-primary ">搜索</button>
        <div class="searchBelow" style="clear: both">
            <c:forEach items="${cs}" var="c" varStatus="st">
                <c:if test="${st.count>=3 and st.count<=6}">
                        <span>
                            <a href="forecategory?cid=${c.id}">${c.name}
                            </a>
                            <c:if test="${st.count!=6}">
                                <span>|</span>
                            </c:if>
                        </span>
                </c:if>
            </c:forEach>
        </div>
    </div>
</form>
