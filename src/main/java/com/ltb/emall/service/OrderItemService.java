package com.ltb.emall.service;

import com.ltb.emall.pojo.Order;
import com.ltb.emall.pojo.OrderItem;

import java.util.List;

/**
 * ClassName: OrderItemService
 * Description: orderitem业务接口
 * User: litengbin
 * Date: 2018/2/18 0:31
 * Version: 1.0.0
 */
public interface OrderItemService {
    public void add(OrderItem orderItem);

    public void update(OrderItem orderItem);

    public void delete(int id);

    public List<OrderItem> list();

    public OrderItem get(int id);

    public void fill(List<Order> os);

    public void fill(Order o);

    public int getSaleCount(int pid);

    public List<OrderItem> listByUser(int uid);
}
