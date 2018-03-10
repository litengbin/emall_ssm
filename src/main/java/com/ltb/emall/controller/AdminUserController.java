package com.ltb.emall.controller;

import com.ltb.emall.pojo.AdminUser;
import com.ltb.emall.pojo.User;
import com.ltb.emall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * ClassName: AdminUserController
 * Description: 管理员用户控制类
 * User: litengbin
 * Date: 2018/2/25 23:21
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @RequestMapping("adminloginPage")
    public String admin_login() {
        /**
         * @author litengbin
         * @method admin_login
         * @param       []
         * @return java.lang.String
         * @date 2018/2/26 15:50
         * @version 1.0.0
         * @description 管理员页面
         */
        return "admin/login";
    }

    @RequestMapping("adminregisterPage")
    public String admin_register() {
        /**
         * @author litengbin
         * @method admin_register
         * @param       []
         * @return java.lang.String
         * @date 2018/3/7 21:46
         * @version 1.0.0
         * @description 管理员注册页面
         */

        return "admin/register";
    }

    @RequestMapping("admin_home")
    public String admin_login_home(@RequestParam("name") String name, @RequestParam("password") String password, Model
            model, HttpSession session) {
        /**
         * @author litengbin
         * @method admin_login_home
         * @param       [name, password, model, session]
         * @return java.lang.String
         * @date 2018/2/26 15:50
         * @version 1.0.0
         * @description 管理员登录
         */
        name = HtmlUtils.htmlEscape(name);
        AdminUser adminUser = adminUserService.get(name, password);
        if (null == adminUser) {
            model.addAttribute("msg", "账号密码错误");
            return "admin/login";
        }
        session.setAttribute("adminUser", adminUser);
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_logout")
    public String logout(HttpSession session) {
        /**
         * @author litengbin
         * @method logout
         * @param       [session]
         * @return java.lang.String
         * @date 2018/2/26 14:45
         * @version 1.0.0
         * @description 管理员退出
         */
        session.removeAttribute("adminUser");
        return "redirect:adminloginPage";
    }

    @RequestMapping("adminregistersuccessPage")
    public String registerSuccess() {
        /**
         * @author litengbin
         * @method registerSuccess
         * @param       []
         * @return java.lang.String
         * @date 2018/2/26 15:50
         * @version 1.0.0
         * @description 注册成功页面
         */
        return "admin/registerPageSuccess";
    }

    @RequestMapping("admin_register")
    public String register(Model model, AdminUser adminUser) {
        /**
         * @author litengbin
         * @method register
         * @param       [model, adminUser]
         * @return java.lang.String
         * @date 2018/3/7 21:46
         * @version 1.0.0
         * @description 管理员注册
         */
        String name = adminUser.getName();
        name = HtmlUtils.htmlEscape(name);
        adminUser.setName(name);
        boolean exist = adminUserService.isExist(name);
        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("adminUser", null);
            return "admin/register";
        }
        adminUserService.add(adminUser);
        return "redirect:adminregistersuccessPage";
    }

}
