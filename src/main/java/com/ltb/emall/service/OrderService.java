package com.ltb.emall.service;

import com.ltb.emall.pojo.Order;
import com.ltb.emall.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderService
 * Description: order业务接口
 * User: litengbin
 * Date: 2018/2/18 1:11
 * Version: 1.0.0
 */
public interface OrderService {
    public String waitPay = "waitPay";
    public String waitDelivery = "waitDelivery";
    public String waitConfirm = "waitConfirm";
    public String waitReview = "waitReview";
    public String finish = "finish";
    public String delete = "delete";

    public void add(Order order);

    public void update(Order order);

    public void delete(int id);

    public Order get(int id);

    public List<Order> list();

    public float add(Order o, List<OrderItem> ois);

    public List list(int uid, String excludedStatus);

    Order get(String code);
}
