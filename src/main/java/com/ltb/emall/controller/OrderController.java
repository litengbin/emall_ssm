package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ltb.emall.pojo.Order;
import com.ltb.emall.service.OrderItemService;
import com.ltb.emall.service.OrderService;
import com.ltb.emall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderController
 * Description: order控制类
 * User: litengbin
 * Date: 2018/2/18 13:38
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Order> os = orderService.list();
        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);
        orderItemService.fill(os);
        model.addAttribute("os", os);
        model.addAttribute("page", page);
        return "admin/listOrder";
    }

    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) {
        /**
         * @author litengbin
         * @method delivery
         * @param       [order]
         * @return java.lang.String
         * @date 2018/2/18 14:45
         * @version 1.0.0
         * @description 订单发货
         */
        order.setDeliveryDate(new Date());
        order.setStatus(orderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";
    }
}
