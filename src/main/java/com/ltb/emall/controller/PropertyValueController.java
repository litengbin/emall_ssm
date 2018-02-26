package com.ltb.emall.controller;

import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.PropertyValue;
import com.ltb.emall.service.ProductService;
import com.ltb.emall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: PropertyValueController
 * Description: propertyvalue控制类
 * User: litengbin
 * Date: 2018/2/17 17:24
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, int pid) {
        /**
         * @author litengbin
         * @method edit
         * @param       [model, pid]
         * @return java.lang.String
         * @date 2018/2/18 14:42
         * @version 1.0.0
         * @description 编辑属性值
         */
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> pvs = propertyValueService.list(pid);
        model.addAttribute("p", product);
        model.addAttribute("pvs", pvs);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue) {
        /**
         * @author litengbin
         * @method update
         * @param       [propertyValue]
         * @return java.lang.String
         * @date 2018/2/18 14:43
         * @version 1.0.0
         * @description 修改属性值
         */
        propertyValueService.update(propertyValue);
        return "success";
    }
}
