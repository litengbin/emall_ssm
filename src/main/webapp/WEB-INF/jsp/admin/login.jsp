<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/2/25
  Time: 22:32
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
        $("div.loginErrorMessageDiv").show();
        </c:if>

        $("form.loginForm").submit(function () {
            if (0 == $("#name").val().length || 0 == $("#password").val().length) {
                $("span.errorMessage").html("请输入账号密码");
                $("div.loginErrorMessageDiv").show();
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function () {
            $("div.loginErrorMessageDiv").hide();
        });

        var left = window.innerWidth / 2 - 200;
        $("div.loginSmallDiv").css("left", left);
    })
</script>
<div id="loginDiv" style="background-color: #204d74;height: 890px;margin-top: -12px;">
    <form class="loginForm" action="admin_home" method="post">
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>
            <div class="login_acount_text text-primary text-center" style="margin-top: -15px;">管理员账户登录</div>
            <div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
                <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text" class="form-control">
            </div>
            <div class="loginInput">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
                <input id="password" name="password" type="password" placeholder="密码" type="text" class="form-control">
            </div>
            <div>
                <a class="notImplementLink" href="#nowhere">忘记登录密码</a>
                <a href="adminregisterPage" class="pull-right">免费注册</a>
            </div>
            <div style="margin-top:30px">
                <button class="btn btn-primary btn-block" type="submit" style="font-weight: bold">登录</button>
            </div>
        </div>
    </form>
</div>
<%@include file="../include/admin/adminFooter.jsp" %>