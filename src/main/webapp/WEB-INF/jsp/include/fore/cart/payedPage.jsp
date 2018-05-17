<%@ page import="org.apache.commons.lang.math.RandomUtils" %><%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/24
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<div class="progress" style="margin: 60px auto;width: 1000px;">
    <div class="progress-bar progress-bar-striped active" style="width: 50%">
        购买商品进度：50%
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 50%">
        剩余进度：50%
    </div>
</div>
<div class="payedDiv">
    <div class="payedTextDiv">
        <img src="img/site/paySuccess.png">
        <span class="text-primary">您已成功付款</span>
    </div>
    <div class="payedAddressInfo">
        <ul>
            <li>收货地址：${o.address} ${o.receiver} ${o.mobile }</li>
            <li>实付款：<span class="payedInfoPrice">
            ￥<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/>
            </li>
            <li>预计5月<%=RandomUtils.nextInt(7)+24%>日送达</li>
        </ul>
        <div class="paedCheckLinkDiv">
            您可以
            <a class="payedCheckLink" href="forebought">查看已买到的宝贝</a>
            <a class="payedCheckLink" href="forebought">查看交易详情 </a>
        </div>

    </div>

    <div class="payedSeperateLine">
    </div>

    <div class="warningDiv">
        <img src="img/site/warning.png">
        <b>安全提醒：</b>下单后，<span class="blueColor boldWord">小心骗子，给您发送链接办理退款的都是骗子！</span>不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！
    </div>

</div>