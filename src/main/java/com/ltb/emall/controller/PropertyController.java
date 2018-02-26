package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.Property;
import com.ltb.emall.service.CategoryService;
import com.ltb.emall.service.PropertyService;
import com.ltb.emall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * ClassName: PropertyController
 * Description: property控制类
 * User: litengbin
 * Date: 2018/2/15 17:43
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_add")
    public String add(Property p) {
        /**
         * @author litengbin
         * @method add
         * @param       [p]
         * @return java.lang.String
         * @date 2018/2/15 20:07
         * @version 1.0.0
         * @description
         */
        propertyService.add(p);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return java.lang.String
         * @date 2018/2/15 18:45
         * @version 1.0.0
         * @description 删除属性
         */
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id) {
        /**
         * @author litengbin
         * @method edit
         * @param       [model, id]
         * @return java.lang.String
         * @date 2018/2/15 18:46
         * @version 1.0.0
         * @description 编辑属性
         */
        Property p = propertyService.get(id);
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p", p);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p) {
        /**
         * @author litengbin
         * @method update
         * @param       [p]
         * @return java.lang.String
         * @date 2018/2/15 20:07
         * @version 1.0.0
         * @description
         */
        propertyService.update(p);
        return "redirect:admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page) {
        /**
         * @author litengbin
         * @method list
         * @param       [cid, model, page]
         * @return java.lang.String
         * @date 2018/2/15 18:46
         * @version 1.0.0
         * @description 属性列表
         */
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        Category c = categoryService.get(cid);
        page.setParam("&cid=" + cid);
        model.addAttribute("ps", ps);
        model.addAttribute("page", page);
        model.addAttribute("c", c);
        return "admin/listProperty";
    }
}
