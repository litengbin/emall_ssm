<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $.validator.addMethod("post",function (value,element) {
            return this.optional(element) || /^\w{6}$/.test(value);
        },"该邮编不正确，正确邮编为6位");
        $.validator.addMethod("mobileCount",function(value,element){
            return this.optional(element) || /^\w{11}$/.test(value);
        },"该手机号码不正确，正确手机位数为11位");
        $.validator.addMethod("mobile",function(value,element){
            return this.optional(element) || /^1[34578]{1}\d{9}$/.test(value);
        },"该手机号码不正确，请输入正确的手机号码");
        $("#validateForm").validate({
            rules:{
                address:{
                    required:true,
                },
                post:{
                    required:true,
                    digits:true,
                     post:true,
                },
                receiver:{
                    required:true,
                    maxlength:25,
                    minlength:2,
                },
                mobile:{
                    required:true,
                    digits:true,
                    mobileCount:true,
                    mobile:true,
                },
                userManager:{
//                    required:false;
                    maxlength:200,
                }
            },
                messages:{
                    address:{
                        required:"地址不能为空",
                    },
                    post:{
                        required:"邮编不能为空",
                        digits:"必须为数字",
                    },
                    receiver:{
                        required:"收货人不能为空",
                        maxlength:"最大输入长度为25",
                        minlength:"最小输入长度为2",
                    },
                    mobile:{
                        required:"手机号码不能为空",
                        digits:"必须为数字",
                    },
                    userManager:{
                        maxlength:"最大输入长度为200",
                    }
                },
                    elementError:"label",
                    success:function (label) {
                        label.html("<span class = 'green1 glyphicon glyphicon-ok' style='color:green'>正确</span>");
                    },
//                    errorClass:"error glyphicon glyphicon-remove",
        });
    });
</script>
<div class="buyPageDiv">
    <form id = "validateForm" action="forecreateOrder" method="post">
        <div class="buyFlow">
            <div class="progress" style="margin: 60px auto;">
                <div class="progress-bar progress-bar-striped active" style="width: 20%">
                    购买商品进度：20%
                </div>
                <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 80%">
                    剩余进度：80%
                </div>
            </div>
            <div style="clear:both"></div>
        </div>
        <div class="address">
            <div class="addressTip text-primary">输入收货地址</div>
            <div>
                <table class="addressTable table table-striped table-bordered table-hover  table-condensed">
                    <tr>
                        <td class="firstColumn">详细地址<span class="redStar">*</span></td>

                        <td><textarea name="address" placeholder="建议您如实填写详细收货地址，例如接到名称，门牌好吗，楼层和房间号等信息"
                                      class="form-control"></textarea></td>
                    </tr>
                    <tr>
                        <td class="firstColumn">邮政编码<span class="redStar">*</span></td>
                        <td><input name="post" placeholder="如果您不清楚邮递区号，请填写000000" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="firstColumn">收货人姓名<span class="redStar">*</span></td>
                        <td><input name="receiver" placeholder="长度不超过25个字符" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td class="firstColumn">手机号码 <span class="redStar">*</span></td>
                        <td><input name="mobile" placeholder="请输入11位手机号码" type="text" class="form-control"></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="productList">
            <div class="productListTip text-primary" style="margin-left: 5px;">确认订单信息</div>

            <table class="productListTable">
                <thead>
                <tr>
                    <th colspan="2" class="productListTableFirstColumn">
                    </th>
                    <th><span class="label label-primary">商品单价</span></th>
                    <th><span class="label label-primary">商品数量</span></th>
                    <th><span class="label label-primary">商品总价</span></th>
                    <th><span class="label label-primary">配送方式</span></th>
                </tr>
                <tr class="rowborder">
                    <td colspan="2"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <tbody class="productListTableTbody">
                <c:forEach items="${ois}" var="oi" varStatus="st">
                    <tr class="orderItemTR">
                        <td class="orderItemFirstTD"><img class="orderItemImg"
                                                          src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
                        </td>
                        <td class="orderItemProductInfo">
                            <a href="foreproduct?pid=${oi.product.id}" class="orderItemProductLink">
                                <strong>${oi.product.name}</strong>
                            </a>
                        </td>
                        <td>
                            <span class="orderItemProductPrice">￥<fmt:formatNumber type="number"
                                                                                   value="${oi.product.promotePrice}"
                                                                                   minFractionDigits="2"/></span>
                        </td>
                        <td>
                            <span class="orderItemProductNumber">${oi.number}</span>
                        </td>
                        <td><span class="orderItemUnitSum"><strong style="font-size:larger">
                        ￥<fmt:formatNumber type="number" value="${oi.number*oi.product.promotePrice}"
                                           minFractionDigits="2"/></strong>
                        </span></td>
                        <c:if test="${st.count==1}">
                            <td rowspan="5" class="orderItemLastTD">
                                <label class="orderItemDeliveryLabel">
                                    <input type="radio" value="" checked="checked"/>
                                    普通配送
                                </label>
                                <select class="orderItemDeliverySelect" class="form-control">
                                    <option>快递 免邮费</option>
                                </select>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="orderItemSumDiv">
                <div class="pull-left">
                    <span class="leaveMessageText">给卖家留言:</span>
                    <span>
                    <img class="leaveMessageImg" src="img/site/leaveMessage.png">
                </span>
                    <span class="leaveMessageTextareaSpan ">
                    <textarea name="userManager" class="leaveMessageTextarea form-control"></textarea>
                    <div>
                        <span>还可以输入200个字符</span>
                    </div>
                </span>
                </div>
                <span class="pull-right">店铺合计(含运费): ￥<fmt:formatNumber type="number" value="${total}"
                                                                       minFractionDigits="2"/></span>
            </div>
        </div>
        <div class="orderItemTotalSumDiv">
            <div class="pull-right">
                <span>实付款：</span>
                <span class="orderItemTotalSumSpan">￥<fmt:formatNumber type="number" value="${total}"
                                                                       minFractionDigits="2"/></span>
            </div>
        </div>
        <div class="submitOrderDiv">
            <button type="submit" class="submitOrderButton">提交订单</button>
        </div>
    </form>
</div>
