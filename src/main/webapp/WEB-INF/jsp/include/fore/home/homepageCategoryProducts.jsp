<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/19
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<style>
    div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td {margin: 0;padding: 0;}
    a{color: #666;text-decoration:none;}
    a:hover{color: #1e90ff;}

    /* 浮动面板 */
    #floatPanel{}
    #floatPanel .ctrolPanel{width:36px;height:166px;background:#fff url("${pageContext.request.contextPath}/img/site/float-panel-bg.gif") no-repeat left top;border:solid 1px #ddd;position:fixed;right:25px;top:300px;overflow:hidden;z-index:10000; _position:absolute; }
    #floatPanel .ctrolPanel a{width:34px;font-size:12px;color:#ff6600;letter-spacing:1px;text-align:center;overflow:hidden;}
    #floatPanel .ctrolPanel .arrow{height:29px;line-height:28px;display:block;margin:1px auto;}
    #floatPanel .ctrolPanel .arrow span{display:none;}
    #floatPanel .ctrolPanel .arrow:hover{background:#f4f4f4;}
    #floatPanel .ctrolPanel .arrow:hover span{display:block;}
    #floatPanel .ctrolPanel .contact{height:60px;display:block;margin:2px auto;}
    #floatPanel .ctrolPanel .contact span{line-height:90px;}
    #floatPanel .ctrolPanel .qrcode{height:40px;display:block;margin:2px auto;}
    #floatPanel .ctrolPanel .qrcode span{display:none;}

    #floatPanel .popPanel{width:230px;height:242px;position:fixed;right:70px;top:300px;z-index:10000;overflow:hidden;display:none;_position:absolute; }
    #floatPanel .popPanel .popPanel-inner{width:230px;height:242px;position:relative;overflow:hidden;}
    #floatPanel .popPanel .popPanel-inner .arrowPanel{width:10px;height:240px;position:absolute;right:1px;top:102px;}
    #floatPanel .popPanel .popPanel-inner .arrowPanel .arrow01{width:0;height:0;font-size:0;line-height:0;border-top:10px solid transparent;_border-top:10px solid black;_filter:chroma(color=black);border-right:10px solid transparent;_border-right:10px solid black;_filter:chroma(color=black);border-bottom:10px solid transparent;_border-bottom:10px solid black;_filter:chroma(color=black);border-left:10px solid #ddd;position:absolute;bottom:0;position:absolute;left:2px;top:0;}
    #floatPanel .popPanel .popPanel-inner .arrowPanel .arrow02{width:0;height:0;font-size:0;line-height:0;border-top:10px solid transparent;_border-top:10px solid black;_filter:chroma(color=black);border-right:10px solid transparent;_border-right:10px solid black;_filter:chroma(color=black);border-bottom:10px solid transparent;_border-bottom:10px solid black;_filter:chroma(color=black);border-left:10px solid #fff;position:absolute;bottom:0;position:absolute;left:0;top:0;}
    #floatPanel .popPanel .popPanel-inner .qrcodePanel{width:220px;height:240px;text-align:center;background:#fff;border:solid 1px #ddd;position:absolute;left:0;top:0;overflow:hidden;}
    #floatPanel .popPanel .popPanel-inner .qrcodePanel img{width:200px;height:200px;border:none;padding:10px 10px 5px 10px;}
    #floatPanel .popPanel .popPanel-inner .qrcodePanel span{font-size:12px;color:#666;line-height:24px;letter-spacing:1px;}
</style>
<script type="text/javascript">

    $(function(){

        // 页面浮动面板
        $("#floatPanel > .ctrolPanel > a.arrow").eq(0).click(function(){$("html,body").animate({scrollTop :0}, 800);return false;});
        $("#floatPanel > .ctrolPanel > a.arrow").eq(1).click(function(){$("html,body").animate({scrollTop : $(document).height()}, 800);return false;});

        var objPopPanel = $("#floatPanel > .popPanel");
        var w = objPopPanel.outerWidth();
        $("#floatPanel > .ctrolPanel > a.qrcode").bind({
            mouseover : function(){
                objPopPanel.css("width","0px").show();
                objPopPanel.animate({"width" : w + "px"},300);return false;
            },
            mouseout : function(){
                objPopPanel.animate({"width" : "0px"},300);return false;
                objPopPanel.css("width",w + "px");
            }
        });
    });
</script>
<!--浮动面板-->
<div id="floatPanel">
    <div class="ctrolPanel">
        <a class="arrow" href="#"><span>顶部</span></a><a class="contact" href="javascript:void(0);"><span>反馈</span></a><a class="qrcode" href="#"><span>微信二维码</span></a><a class="arrow" href="#"><span>底部</span></a></div>
    <div class="popPanel">
        <div class="popPanel-inner">
            <div class="qrcodePanel">
                <img src="${pageContext.request.contextPath}/img/site/foreqrcode.png" /><span style="display: block">扫描二维码登录手机</span></div>
            <div class="arrowPanel">
                <div class="arrow01">
                </div>
                <div class="arrow02">
                </div>
            </div>
        </div>
    </div>
</div>
<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>
<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
<div class="homepageCategoryProducts">
    <c:forEach items="${cs}" var="c" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="eachHomepageCategoryProducts">
                <div class="left-mark"></div>
                <span class="categoryTitle">${c.name}</span>
                <br>
                <c:forEach items="${c.products}" var="p" varStatus="st">
                    <c:if test="${st.count<=5}">
                        <div class="productItem">
                            <a href="foreproduct?pid=${p.id}"><img width="100px"
                                                                   src="img/productSingle_middle/${p.firstProductImage.id}.jpg"></a>
                            <a class="productItemDescLink" href="foreproduct?pid=${p.id}">
                                <span class="productItemDesc"><strong style="color: red">[热销]</strong>
                                    <strong class="text-muted">${fn:substring(p.name, 0, 20)}</strong>
                                </span>
                            </a>
                            <span class="productPrice">
                                <fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
                            </span>
                        </div>
                    </c:if>
                </c:forEach>
                <div style="clear:both"></div>
            </div>
        </c:if>
    </c:forEach>
</div>