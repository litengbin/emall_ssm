<%--
  Created by IntelliJ IDEA.
  User: litengbin
  Date: 2018/5/4
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(function(){
        //1.书写显示广告图片的定时操作
        time = setInterval("showAd()",0);
    });

    //2.书写显示广告图片的函数
    function showAd(){
        //3.获取广告图片，并让其显示
        //$("#img2").show(1000);
        $("#img2").slideDown(2000);
        //$("#img2").fadeIn(4000);
        //4.清除显示图片定时操作
        clearInterval(time);
        //5.设置隐藏图片的定时操作
        time = setInterval("hiddenAd()",0);
    }

    function hiddenAd(){
        //6.获取广告图片，并让其隐藏
        //$("#img2").hide();
        $("#img2").slideUp(2000);
        //$("#img2").fadeOut(6000);
        //7.清除隐藏图片的定时操作
        clearInterval(time);
    }
</script>
<img src="img/lunbo/5.jpg" width="100%" height = "180px" style="display: none" id="img2"/>