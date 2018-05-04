<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/24
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<script>
    var deleteOrder = false;
    var deleteOrderid = 0;

    $(function () {
        $("a[orderStatus]").click(function () {
            var orderStatus = $(this).attr("orderStatus");
            if ('all' == orderStatus) {
                $("table[orderStatus]").show();
            }
            else {
                $("table[orderStatus]").hide();
                $("table[orderStatus=" + orderStatus + "]").show();
            }

            $("div.orderType div").removeClass("selectedOrderType");
            $(this).parent("div").addClass("selectedOrderType");
        });

        $("a.deleteOrderLink").click(function () {
            deleteOrderid = $(this).attr("oid");
            deleteOrder = false;
            $("#deleteConfirmModal").modal("show");
        });

        $("button.deleteConfirmButton").click(function () {
            deleteOrder = true;
            $("#deleteConfirmModal").modal('hide');
        });

        $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
            if (deleteOrder) {
                var page = "foredeleteOrder";
                $.post(
                    page,
                    {"oid": deleteOrderid},
                    function (result) {
                        if ("success" == result) {
                            $("table.orderListItemTable[oid=" + deleteOrderid + "]").hide();
                        }
                        else {
                            location.href = "loginPage";
                        }
                    }
                );

            }
        })

        $(".ask2delivery").click(function () {
            var link = $(this).attr("link");
            $(this).hide();
            page = link;
            $.ajax({
                url: page,
                success: function (result) {
                    alert("卖家已秒发");
                    location.href = "forebought";
                }
            });

        });
    });

</script>

<div class="boughtDiv">
    <div class="orderType">
        <div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
        <div><a orderStatus="waitPay" href="#nowhere">待付款</a></div>
        <div><a orderStatus="waitDelivery" href="#nowhere">待发货</a></div>
        <div><a orderStatus="waitConfirm" href="#nowhere">待收货</a></div>
        <div><a orderStatus="waitReview" href="#nowhere" class="noRightborder">待评价</a></div>
        <div><a orderStatus="finish" href="#nowhere">已完成</a></div>
        <div class="orderTypeLastOne"><a class="noRightborder"> </a></div>
    </div>
    <div style="clear:both"></div>
    <div class="orderListTitle">
        <table class="orderListTitleTable">
            <tr>
                <td class="text-muted" style="font-weight: bold"><h4 style="font-size:25px;"><span class="label label-primary">你的宝贝</span> </h4></td>
                <td width="100px" class="text-muted" style="font-weight: bold"><h4 style="font-size:25px;"><span class="label label-primary">商品单价</span> </h4></td>
                <td width="100px" class="text-muted" style="font-weight: bold"><h4 style="font-size:25px;"><span class="label label-primary">购买数量</span> </h4></td>
                <td width="100px" class="text-muted" style="font-weight: bold"><h4 style="font-size:25px;"><span class="label label-primary">实际付款</span> </h4></td>
                <td width="100px" class="text-muted" style="font-weight: bold"><h4 style="font-size:25px;"><span class="label label-primary">交易操作</span> </h4></td>
            </tr>
        </table>
    </div>
    <div class="orderListItem">
        <c:forEach items="${os}" var="o">
            <table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
                <tr class="orderListItemFirstTR">
                    <td colspan="2" class="text-muted">
                        <b><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b>
                        <span>订单号: ${o.orderCode}
                    </span>
                    </td>
                    <td colspan="2" class="text-muted text-center">
                        商场
                    </td>
                    <td colspan="1" class="text-muted text-center">
                        价格
                    </td>
                    <td class="orderItemDeleteTD">
                        <a class="deleteOrderLink" oid="${o.id}" href="#nowhere">
                            <span class="orderListItemDelete glyphicon glyphicon-trash blueColor"></span>
                        </a>

                    </td>
                </tr>
                <c:forEach items="${o.orderItems}" var="oi" varStatus="st">
                    <tr class="orderItemProductInfoPartTR">
                        <td class="orderItemProductInfoPartTD"><img width="80" height="80"
                                                                    src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
                        </td>
                        <td class="orderItemProductInfoPartTD">
                            <div class="orderListItemProductLinkOutDiv">
                                <a href="foreproduct?pid=${oi.product.id}">${oi.product.name}</a>
                                <div class="orderListItemProductLinkInnerDiv">
                                </div>
                            </div>
                        </td>
                        <td class="orderItemProductInfoPartTD" width="100px">

                            <div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number"
                                                                                              value="${oi.product.initialPrice}"
                                                                                              minFractionDigits="2"/></div>
                            <div class="orderListItemProductPrice">￥<fmt:formatNumber type="number"
                                                                                      value="${oi.product.promotePrice}"
                                                                                      minFractionDigits="2"/></div>

                        </td>
                        <c:if test="${st.count==1}">

                            <td valign="top" rowspan="${fn:length(o.orderItems)}"
                                class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
                                <span class="orderListItemNumber">${o.totalNumber}</span>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItems)}" width="120px"
                                class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
                                <div class="orderListItemProductRealPrice">￥<fmt:formatNumber minFractionDigits="2"
                                                                                              maxFractionDigits="2"
                                                                                              type="number"
                                                                                              value="${o.total}"/></div>
                                <div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
                            </td>
                            <td valign="top" rowspan="${fn:length(o.orderItems)}"
                                class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
                                <c:if test="${o.status=='waitConfirm' }">
                                    <a href="foreconfirmPay?oid=${o.id}">
                                        <button class="orderListItemConfirm btn btn-primary">确认收货</button>
                                    </a>
                                </c:if>
                                <c:if test="${o.status=='waitPay' }">
                                    <a href="forealipay?oid=${o.id}&total=${o.total}">
                                        <button class="orderListItemConfirm btn btn-primary">付款</button>
                                    </a>
                                </c:if>

                                <c:if test="${o.status=='waitDelivery' }">
                                    <br/>
                                    <span class="text-primary" style="font-weight: bolder">待发货</span>
                                    <br/>
                                    <br/>
                                    <button class="btn btn-info btn-sm ask2delivery orderListItemConfirm"
                                            link="admin_order_delivery?id=${o.id}">催卖家发货
                                    </button>
                                </c:if>
                                <c:if test="${o.status=='waitReview' }">
                                    <a href="forereview?oid=${o.id}">
                                        <button class="orderListItemReview btn btn-primary">评价</button>
                                    </a>
                                </c:if>
                                <c:if test="${o.status=='finish' }">
                                    <%--<a href="forereview?oid=${o.id}">--%>
                                    <a href="javascript:void(0);">
                                        <button class="orderListItemReview btn btn-primary disabled">已完成</button>
                                    </a>
                                </c:if>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>

            </table>
        </c:forEach>
    </div>

</div>
