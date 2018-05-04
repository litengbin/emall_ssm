<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/5/4
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function setCookie(name, value, expire) {
        window.document.cookie = name + "=" + escape(value) + ((expire == null) ? "" : ("; expires=" + expire.toGMTString()));
    }
    function getCookie(Name) {
        var search = Name + "=";
        if (window.document.cookie.length > 0) {
            // 如果没有则下一个
            offset = window.document.cookie.indexOf(search);
            if (offset != -1) {
                // 如果找到
                offset += search.length;
                // 设置开始
                end = window.document.cookie.indexOf(";", offset)
                // 结束
                if (end == -1)
                    end = window.document.cookie.length;
                return unescape(window.document.cookie.substring(offset, end));
            }
        }
        return null;
    }
    function register(name) {
        var today = new Date();
        var expires = new Date();
        expires.setTime(today.getTime() + 1000 * 60 * 60 * 12);
        setCookie("ItDoor", name, expires);
    }
    $(function(){
            //1.书写显示广告图片的定时操作
        var c = getCookie("ItDoor");
        if (c == null) {
//            alert();
            time = setInterval("showAd()", 0);
            register();
        }
    });

    //2.书写显示广告图片的函数
    function showAd(){
        //3.获取广告图片，并让其显示
        //$("#img2").show(1000);
        $("#img2").slideDown(3000);
        //$("#img2").fadeIn(4000);
        //4.清除显示图片定时操作
        clearInterval(time);
        //5.设置隐藏图片的定时操作
        time = setInterval("hiddenAd()",0);
    }

    function hiddenAd(){
        //6.获取广告图片，并让其隐藏
        //$("#img2").hide();
        $("#img2").slideUp(3000);
        //$("#img2").fadeOut(6000);
        //7.清除隐藏图片的定时操作
        clearInterval(time);
    }
</script>
<img src="img/lunbo/5.jpg" width="100%"  style="display: none" id="img2"/>