<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/21
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var num = $(this).val();
            if (num.length == 0) {
                $("div.productUnit").show();
                return;
            }

            num = parseInt(num);
            if (isNaN(num))
                num = 1;
            if (num <= 0)
                num = 1;
            $(this).val(num);

            var begin = $("input.beginPrice").val();
            var end = $("input.endPrice").val();
            if (!isNaN(begin) && !isNaN(end)) {
                console.log(begin);
                console.log(end);
                $("div.productUnit").hide();
                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");
                    price = new Number(price);
                    if (price <= end && price >= begin)
                        $(this).show();
                });
            }
        });
    });
</script>
<div class="categorySortBar">
    <table class="categorySortBarTable categorySortTable">
        <tr>
        <td
                    <c:if test="${'all'==param.sort||empty param.sort}">class="grayColumn"</c:if> ><a
                    href="?cid=${c.id}&sort=all"><h3><span class="label label-primary spana "><strong>综合 </strong><span class="glyphicon glyphicon-arrow-down " ></span></span></h3></a></td>
            <td
                    <c:if test="${'review'==param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=review"><h3><span class="label label-primary spana"><strong>人气</strong><span
                    class="glyphicon glyphicon-arrow-down"></span></span></h3></a></td>
            <td <c:if test="${'date'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=date"><h3><span class="label label-primary spana"><strong>新品</strong><span
                    class="glyphicon glyphicon-arrow-down"></span></span></h3></a></td>
            <td <c:if test="${'saleCount'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=saleCount"><h3><span class="label label-primary spana"><strong>销量</strong><span
                    class="glyphicon glyphicon-arrow-down"></span></span></h3></a></td>
            <td <c:if test="${'price'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=price"><h3><span class="label label-primary spana"><strong>价格</strong><span
                    class="glyphicon glyphicon-resize-vertical"></span></span></h3></a></td>
        </tr>
    </table>
    <table class="categorySortBarTable">
        <tr>
            <td><input class="sortBarPrice beginPrice form-control" type="text" placeholder="请输入最低价格"></td>
            <td class="grayColumn priceMiddleColumn">---</td>
            <td><input class="sortBarPrice endPrice form-control" type="text" placeholder="请输入最高价格"></td>
        </tr>
    </table>
</div>