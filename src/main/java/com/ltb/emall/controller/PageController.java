package com.ltb.emall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: PageController
 * Description: 页面跳转控制器
 * User: litengbin
 * Date: 2018/2/19 18:16
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping("registerPage")
    public String registerPage() {
        /**
         * @author litengbin
         * @method registerPage
         * @param       []
         * @return java.lang.String
         * @date 2018/2/19 19:36
         * @version 1.0.0
         * @description 跳转到注册页
         */
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        /**
         * @author litengbin
         * @method registerSuccessPage
         * @param       []
         * @return java.lang.String
         * @date 2018/2/19 19:36
         * @version 1.0.0
         * @description 跳转到注册成功页面
         */
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        /**
         * @author litengbin
         * @method loginPage
         * @param       []
         * @return java.lang.String
         * @date 2018/2/19 19:37
         * @version 1.0.0
         * @description 跳转到登录页面
         */
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String alipay() {
        /**
         * @author litengbin
         * @method alipay
         * @param       []
         * @return java.lang.String
         * @date 2018/2/19 19:37
         * @version 1.0.0
         * @description 跳转到支付页面
         */
        return "fore/alipay";
    }
}
