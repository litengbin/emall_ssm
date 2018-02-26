<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/24
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="aliPayPageDiv">
    <div class="progress" style="margin: 60px auto;">
        <div class="progress-bar progress-bar-striped active" style="width: 40%">
            购买商品进度：40%
        </div>
        <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 60%">
            剩余进度：60%
        </div>
    </div>
    <div style="margin: 60px auto">
        <span class="confirmMoneyText">扫一扫付款（元）</span>
        <span class="confirmMoney">
        ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>
    </div>
    <div>
        <img class="aliPayImg" src="img/site/alipay2wei.png">
    </div>
    <div>
        <a href="forepayed?oid=${param.oid}&total=${param.total}"><button class="confirmPay btn btn-primary">确认支付</button></a>
    </div>
</div>

