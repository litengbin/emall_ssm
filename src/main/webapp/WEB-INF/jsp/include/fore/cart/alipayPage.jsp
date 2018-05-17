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
    <%--oid:${param.oid}--%>
    <form method="post" action="ebaopayed?oid=${param.oid}&total=${param.total}">
    <div style="margin: 60px auto">
            <span class="confirmMoney">
        ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>
        <strong>选择银行：</strong>
        <p>
            <br /> <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C"
                          checked="checked" />工商银行 <img src="../../../img/bank_img/icbc.bmp"
                                                        align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />中国银行 <img
                src="../../../img/bank_img/bc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />农业银行 <img
                src="../../../img/bank_img/abc.bmp" align="middle" /> <br /> <br /> <input
                type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />交通银行 <img
                src="../../../img/bank_img/bcc.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />平安银行
            <img src="../../../img/bank_img/pingan.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />建设银行 <img
                src="../../../img/bank_img/ccb.bmp" align="middle" /> <br /> <br /> <input
                type="radio" name="pd_FrpId" value="CEB-NET-B2C" />光大银行 <img
                src="../../../img/bank_img/guangda.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C" />招商银行
            <img src="../../../img/bank_img/cmb.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="POST-NET-B2C" />中国邮政
            <img src="../../../img/bank_img/post.bmp" align="middle" /> <br /> <br />
            <input type="radio" name="pd_FrpId" value="CBHB-NET-B2C" />渤海银行
            <img src="../../../img/bank_img/bh.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="BCCB-NET-B2C" />北京银行
            <img src="../../../img/bank_img/bj.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="CIB-NET-B2C" />兴业银行
            <img src="../../../img/bank_img/cib.bmp" align="middle" /> <br /> <br />
            <input type="radio" name="pd_FrpId" value="HKBEA-NET-B2C" />东亚银行
            <img src="../../../img/bank_img/dy.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="NJCB-NET-B2C" />南京银行
            <img src="../../../img/bank_img/nanjing.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="NBCB-NET-B2C" />宁波银行
            <img src="../../../img/bank_img/ningbo.bmp" align="middle" /> <br /> <br />
            <input type="radio" name="pd_FrpId" value="SHB-NET-B2C" />上海银行
            <img src="../../../img/bank_img/sh.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="CZ-NET-B2C" />浙商银行
            <img src="../../../img/bank_img/zheshang.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="CMBC-NET-B2C" />民生银行
            <img src="../../../img/bank_img/cmbc.bmp" align="middle" /> <br /> <br />
            <input type="radio" name="pd_FrpId" value="GDB-NET-B2C" />广东发展银行
            <img src="../../../img/bank_img/gf.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <%--<input type="radio" name="pd_FrpId" value="HXB-NET-B2C" />华夏银行--%>
            <%--<img src="../../../img/bank_img/hx.bmp" align="middle" />--%>
            <input type="radio" name="pd_FrpId" value="SDB-NET-B2C" />深圳发展银行
            <img src="../../../img/bank_img/sfz.bmp" align="middle" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pd_FrpId" value="SPDB-NET-B2C" />上海浦东发展银行
            <img src="../../../img/bank_img/shpd.bmp" align="middle" /> <br /> <br />
            <input type="radio" name="pd_FrpId" value="BJRCB-NET-B2C" />北京农村商业银行
            <img src="../../../img/bank_img/beijingnongshang.bmp" align="middle" />
            <%--<input type="radio" name="pd_FrpId" value="ECITIC-NET-B2C" />中信银行--%>
            <%--<img src="../../../img/bank_img/zx.bmp" align="middle" />--%>
        </p>
        <br>
        <button type="submit" class="confirmPay btn btn-primary">确认支付</button>
    </div>
    </form>

    <%--<div style="margin: 60px auto">--%>
        <%--<span class="confirmMoneyText">扫一扫付款（元）</span>--%>
        <%--<span class="confirmMoney">--%>
        <%--￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<img class="aliPayImg" src="img/site/alipay2wei.png">--%>
    <%--</div>--%>
    <%--<div>--%>
        <%--<a href="forepayed?oid=${param.oid}&total=${param.total}"><button class="confirmPay btn btn-primary">确认支付</button></a>--%>
    <%--</div>--%>
</div>

