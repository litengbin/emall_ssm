package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.Product;
import com.ltb.emall.service.CategoryService;
import com.ltb.emall.service.ProductService;
import com.ltb.emall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * ClassName: ProductController
 * Description: product控制类
 * User: litengbin
 * Date: 2018/2/15 20:44
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_add")
    public String add(Product product) {
        /**
         * @author litengbin
         * @method add
         * @param       [product]
         * @return java.lang.String
         * @date 2018/2/18 14:44
         * @version 1.0.0
         * @description 新增产品
         */
        product.setCreateDate(new Date());
        productService.add(product);
        return "redirect:admin_product_list?cid=" + product.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return java.lang.String
         * @date 2018/2/18 14:44
         * @version 1.0.0
         * @description 删除产品
         */
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?cid=" + product.getCid();
    }

    @RequestMapping("admin_product_edit")
    public String edit(Model model, int id) {
        /**
         * @author litengbin
         * @method edit
         * @param       [model, id]
         * @return java.lang.String
         * @date 2018/2/18 14:44
         * @version 1.0.0
         * @description 编辑产品
         */
        Product product = productService.get(id);
        //Category category = categoryService.get(product.getCid());
        //product.setCategory(category);
        model.addAttribute("p", product);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:admin_product_list?cid=" + product.getCid();
    }

    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page) {
        /**
         * @author litengbin
         * @method list
         * @param       [cid, model, page]
         * @return java.lang.String
         * @date 2018/2/18 14:45
         * @version 1.0.0
         * @description 获取产品列表
         */
        Category category = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + cid);
        model.addAttribute("ps", ps);
        model.addAttribute("c", category);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }
}
