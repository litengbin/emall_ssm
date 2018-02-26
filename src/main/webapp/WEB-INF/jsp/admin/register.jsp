<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/26
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility", "visible");
        </c:if>

        $(".registerForm").submit(function () {
            if (0 == $("#name").val().length) {
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility", "visible");
                return false;
            }
            if (0 == $("#password").val().length) {
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility", "visible");
                return false;
            }
            if (0 == $("#repeatpassword").val().length) {
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("visibility", "visible");
                return false;
            }
            if ($("#password").val() != $("#repeatpassword").val()) {
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("visibility", "visible");
                return false;
            }

            return true;
        });
        $(".registerTable input").keyup(function () {
            $(".registerErrorMessageDiv").css("visibility", "hidden");
            ;
        });
    })
</script>

<form method="post" action="admin_register" class="registerForm">
    <div class="registerDiv">
        <div style="background-color: white;width: 400px;height:550px;margin:0 auto;top: 150px;position: relative">
            <div class="registerErrorMessageDiv">
                <div class="alert alert-info" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>
            <table class="registerTable" align="center">
                <tr>
                    <td colspan="2" class="registerTip text-primary text-center">管理员账户注册</td>
                </tr>
                <tr>
                    <td class="registerTableLeftTD text-muted">登陆名</td>
                    <td class="registerTableRightTD"><input id="name" name="name" placeholder="会员名一旦设置成功，无法修改"
                                                            class="form-control"></td>
                </tr>
                <tr>
                    <td class="registerTip registerTableLeftTD text-muted">设置登陆密码</td>
                    <td class="registerTableRightTD text-muted">登陆时验证，保护账号信息</td>
                </tr>
                <tr>
                    <td class="registerTableLeftTD text-muted">登陆密码</td>
                    <td class="registerTableRightTD"><input id="password" name="password" type="password"
                                                            placeholder="设置你的登陆密码" class="form-control"></td>
                </tr>
                <tr>
                    <td class="registerTableLeftTD text-muted">密码确认</td>
                    <td class="registerTableRightTD"><input id="repeatpassword" type="password" placeholder="请再次输入你的密码"
                                                            class="form-control">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="registerButtonTD">

                            <button class="btn btn-primary btn-block" style="font-weight: bold">提 交</button>

                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
<%@include file="../include/admin/adminFooter.jsp" %>
