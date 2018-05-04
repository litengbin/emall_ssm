<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<script>
    var deleteOrderItem = false;
    var deleteOrderItemid = 0;
    $(function () {

        $("a.deleteOrderItem").click(function () {
            deleteOrderItem = false;
            var oiid = $(this).attr("oiid")
            deleteOrderItemid = oiid;
            $("#deleteConfirmModal").modal('show');
        });
        $("button.deleteConfirmButton").click(function () {
            deleteOrderItem = true;
            $("#deleteConfirmModal").modal('hide');
        });

        $('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
            if (deleteOrderItem) {
                var page = "foredeleteOrderItem";
                $.post(
                    page,
                    {"oiid": deleteOrderItemid},
                    function (result) {
                        if ("success" == result) {
                            $("tr.cartProductItemTR[oiid=" + deleteOrderItemid + "]").hide();
                            location.href = "forecart";
                        }
                        else {
                            location.href = "loginPage";
                        }
                    }
                );

            }
        })

        $("img.cartProductItemIfSelected").click(function () {
            var selectit = $(this).attr("selectit")
            if ("selectit" == selectit) {
                $(this).attr("src", "img/site/cartNotSelected.png");
                $(this).attr("selectit", "false")
                $(this).parents("tr.cartProductItemTR").css("background-color", "#fff");
            }
            else {
                $(this).attr("src", "img/site/cartSelected.png");
                $(this).attr("selectit", "selectit")
                $(this).parents("tr.cartProductItemTR").css("background-color", "#e3f3ff");
            }
            syncSelect();
            syncCreateOrderButton();
            calcCartSumPriceAndNumber();
        });
        $("img.selectAllItem").click(function () {
            var selectit = $(this).attr("selectit")
            if ("selectit" == selectit) {
                $("img.selectAllItem").attr("src", "img/site/cartNotSelected.png");
                $("img.selectAllItem").attr("selectit", "false")
                $(".cartProductItemIfSelected").each(function () {
                    $(this).attr("src", "img/site/cartNotSelected.png");
                    $(this).attr("selectit", "false");
                    $(this).parents("tr.cartProductItemTR").css("background-color", "#fff");
                });
            }
            else {
                $("img.selectAllItem").attr("src", "img/site/cartSelected.png");
                $("img.selectAllItem").attr("selectit", "selectit")
                $(".cartProductItemIfSelected").each(function () {
                    $(this).attr("src", "img/site/cartSelected.png");
                    $(this).attr("selectit", "selectit");
                    $(this).parents("tr.cartProductItemTR").css("background-color", "#e3f3ff");
                });
            }
            syncCreateOrderButton();
            calcCartSumPriceAndNumber();

        });

        $(".orderItemNumberSetting").keyup(function () {
            var pid = $(this).attr("pid");
            var stock = $("span.orderItemStock[pid=" + pid + "]").text();
            var price = $("span.orderItemPromotePrice[pid=" + pid + "]").text();

            var num = $(".orderItemNumberSetting[pid=" + pid + "]").val();
            num = parseInt(num);
            if (isNaN(num))
                num = 1;
            if (num <= 0)
                num = 1;
            if (num > stock)
                num = stock;

            syncPrice(pid, num, price);
        });

        $(".numberPlus").click(function () {

            var pid = $(this).attr("pid");
            var stock = $("span.orderItemStock[pid=" + pid + "]").text();
            var price = $("span.orderItemPromotePrice[pid=" + pid + "]").text();
            var num = $(".orderItemNumberSetting[pid=" + pid + "]").val();

            num++;
            if (num > stock)
                num = stock;
            syncPrice(pid, num, price);
        });
        $(".numberMinus").click(function () {
            var pid = $(this).attr("pid");
            var stock = $("span.orderItemStock[pid=" + pid + "]").text();
            var price = $("span.orderItemPromotePrice[pid=" + pid + "]").text();

            var num = $(".orderItemNumberSetting[pid=" + pid + "]").val();
            --num;
            if (num <= 0)
                num = 1;
            syncPrice(pid, num, price);
        });

        $("button.createOrderButton").click(function () {
            var params = "";
            $(".cartProductItemIfSelected").each(function () {
                if ("selectit" == $(this).attr("selectit")) {
                    var oiid = $(this).attr("oiid");
                    params += "&oiid=" + oiid;
                }
            });
            params = params.substring(1);
            location.href = "forebuy?" + params;
        });

    })

    function syncCreateOrderButton() {
        var selectAny = false;
        $(".cartProductItemIfSelected").each(function () {
            if ("selectit" == $(this).attr("selectit")) {
                selectAny = true;
            }
        });

        if (selectAny) {
//            $("button.createOrderButton").css("background-color", "#337ab7");
            $("button.createOrderButton").addClass("btn btn-primary");
            $("button.createOrderButton").removeAttr("disabled").removeClass("disabled");

        }
        else {
//            $("button.createOrderButton").css("background-color", "#AAAAAA");
            $("button.createOrderButton").attr("disabled", "disabled").addClass("disabled");
        }

    }

    function syncSelect() {
        var selectAll = true;
        $(".cartProductItemIfSelected").each(function () {
            if ("false" == $(this).attr("selectit")) {
                selectAll = false;
            }
        });

        if (selectAll)
            $("img.selectAllItem").attr("src", "img/site/cartSelected.png");
        else
            $("img.selectAllItem").attr("src", "img/site/cartNotSelected.png");

    }

    function calcCartSumPriceAndNumber() {
        var sum = 0;
        var totalNumber = 0;
        $("img.cartProductItemIfSelected[selectit='selectit']").each(function () {
            var oiid = $(this).attr("oiid");
            var price = $(".cartProductItemSmallSumPrice[oiid=" + oiid + "]").text();
            price = price.replace(/,/g, "");
            price = price.replace(/￥/g, "");
            sum += new Number(price);

            var num = $(".orderItemNumberSetting[oiid=" + oiid + "]").val();
            totalNumber += new Number(num);

        });

        $("span.cartSumPrice").html("￥" + formatMoney(sum));
        $("span.cartTitlePrice").html("￥" + formatMoney(sum));
        $("span.cartSumNumber").html(totalNumber);
    }

    function syncPrice(pid, num, price) {
        $(".orderItemNumberSetting[pid=" + pid + "]").val(num);
        var cartProductItemSmallSumPrice = formatMoney(num * price);
        $(".cartProductItemSmallSumPrice[pid=" + pid + "]").html("￥" + cartProductItemSmallSumPrice);
        calcCartSumPriceAndNumber();

        var page = "forechangeOrderItem";
        $.post(
            page,
            {"pid": pid, "number": num},
            function (result) {
                if ("success" != result) {
                    location.href = "loginPage";
                }
            }
        );

    }
</script>

<title>购物车</title>

<div class="progress" style="margin: 60px auto;width: 1020px;">
    <div class="progress-bar progress-bar-striped active" style="width: 0%;min-width: 10%">
        购买商品进度：0%
    </div>
    <div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 90%;">
        剩余进度：100%
    </div>
</div>
<div class="cartDiv">
    <div class="cartTitle pull-right">
        <span>已选商品  (不含运费)</span>
        <span class="cartTitlePrice">￥0.00</span>
        <button class="createOrderButton btn btn-primary disabled" disabled="disabled">结 算</button>
    </div>

    <div class="cartProductList">
        <table class="cartProductTable">
            <thead>
            <tr>
                <th class="selectAndImage"> <span class="label label-primary">
                    <img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">
                   全选</span>
                </th>
                <th><span class="label label-primary">商品信息</span> </th>
                <th><span class="label label-primary">单价</span></th>
                <th><span class="label label-primary">数量</span></th>
                <th width="120px"><span class="label label-primary">金额</span></th>
                <th class="operation"><span class="label label-primary">操作</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ois }" var="oi">
                <tr oiid="${oi.id}" class="cartProductItemTR">
                    <td>
                        <img selectit="false" oiid="${oi.id}" class="cartProductItemIfSelected"
                             src="img/site/cartNotSelected.png">
                        <a style="display:none" href="#nowhere"><img src="img/site/cartSelected.png"></a>
                        <img class="cartProductImg"
                             src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg">
                    </td>
                    <td>
                        <div class="cartProductLinkOutDiv">
                            <a href="foreproduct?pid=${oi.product.id}" class="cartProductLink">${oi.product.name}</a>
                        </div>

                    </td>
                    <td>
                        <span class="cartProductItemOringalPrice">￥${oi.product.initialPrice}</span>
                        <span class="cartProductItemPromotionPrice">￥${oi.product.promotePrice}</span>
                    </td>
                    <td>

                        <div class="cartProductChangeNumberDiv">
                            <span class="hidden orderItemStock " pid="${oi.product.id}">${oi.product.inventory}</span>
                            <span class="hidden orderItemPromotePrice "
                                  pid="${oi.product.id}">${oi.product.promotePrice}</span>
                            <a pid="${oi.product.id}" class="numberMinus form-control" href="#nowhere" style="padding: 5px 2px;">-</a>
                            <input pid="${oi.product.id}" oiid="${oi.id}" class="orderItemNumberSetting form-control" style="padding: 5px 2px;"
                                   autocomplete="off" value="${oi.number}">
                            <a stock="${oi.product.inventory}" pid="${oi.product.id}" class="numberPlus form-control"
                               href="#nowhere" style="padding: 5px 2px;">+</a>
                        </div>

                    </td>
                    <td>
                            <span class="cartProductItemSmallSumPrice" oiid="${oi.id}" pid="${oi.product.id}">
                            ￥<fmt:formatNumber type="number" value="${oi.product.promotePrice*oi.number}"
                                               minFractionDigits="2"/>
                            </span>
                    </td>
                    <td>
                        <a class="deleteOrderItem btn btn-danger" oiid="${oi.id}" href="#nowhere">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="cartFoot">
        <img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">
        <span>全选</span>
        <!--         <a href="#">删除</a> -->

        <div class="pull-right">
            <span>已选商品 <span class="cartSumNumber">0</span> 件</span>
            <span>合计 (不含运费): </span>
            <span class="cartSumPrice">￥0.00</span>
            <button class="createOrderButton btn btn-primary disabled" disabled="disabled">结 算</button>
        </div>
    </div>
</div>
