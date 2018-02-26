package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ltb.emall.pojo.User;
import com.ltb.emall.service.UserService;
import com.ltb.emall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClassName: UserController
 * Description: 用户控制类
 * User: litengbin
 * Date: 2018/2/17 22:40
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page) {
        /**
         * @author litengbin
         * @method list
         * @param       [model, page]
         * @return java.lang.String
         * @date 2018/2/18 14:42
         * @version 1.0.0
         * @description 获取用户列表
         */
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> us = userService.list();
        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);
        model.addAttribute("us", us);
        model.addAttribute("page", page);
        return "admin/listUser";
    }
}
