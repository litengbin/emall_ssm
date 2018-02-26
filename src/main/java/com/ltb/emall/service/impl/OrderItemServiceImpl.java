package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.OrderItemMapper;
import com.ltb.emall.pojo.Order;
import com.ltb.emall.pojo.OrderItem;
import com.ltb.emall.pojo.OrderItemExample;
import com.ltb.emall.pojo.Product;
import com.ltb.emall.service.OrderItemService;
import com.ltb.emall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: OrderItemServiceImpl
 * Description: orderItem业务实现类
 * User: litengbin
 * Date: 2018/2/18 0:46
 * Version: 1.0.0
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        /**
         * @author litengbin
         * @method add
         * @param       [orderItem]
         * @return void
         * @date 2018/2/18 14:48
         * @version 1.0.0
         * @description 新增订单项
         */
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void update(OrderItem orderItem) {
        /**
         * @author litengbin
         * @method update
         * @param       [orderItem]
         * @return void
         * @date 2018/2/18 14:48
         * @version 1.0.0
         * @description 修改订单项
         */
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/18 14:48
         * @version 1.0.0
         * @description 删除订单项
         */
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<OrderItem> list() {
        /**
         * @author litengbin
         * @method list
         * @param       []
         * @return java.util.List<com.ltb.emall.pojo.OrderItem>
         * @date 2018/2/18 14:46
         * @version 1.0.0
         * @description 获取订单项列表
         */
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public OrderItem get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.OrderItem
         * @date 2018/2/18 14:46
         * @version 1.0.0
         * @description 获取订单项
         */
        OrderItem o = orderItemMapper.selectByPrimaryKey(id);
        this.setProduct(o);
        return o;
    }

    public void setProduct(OrderItem o) {
        /**
         * @author litengbin
         * @method setProduct
         * @param       [o]
         * @return void
         * @date 2018/2/18 14:46
         * @version 1.0.0
         * @description set产品
         */
        Product product = productService.get(o.getPid());
        o.setProduct(product);
    }

    public void setProduct(List<OrderItem> ois) {
        /**
         * @author litengbin
         * @method setProduct
         * @param       [ois]
         * @return void
         * @date 2018/2/18 14:47
         * @version 1.0.0
         * @description set产品
         */
        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

    @Override
    public void fill(List<Order> os) {
        /**
         * @author litengbin
         * @method fill
         * @param       [os]
         * @return void
         * @date 2018/2/18 14:47
         * @version 1.0.0
         * @description 填充order列表
         */
        for (Order o : os) {
            fill(o);
        }
    }

    @Override
    public void fill(Order o) {
        /**
         * @author litengbin
         * @method fill
         * @param       [o]
         * @return void
         * @date 2018/2/18 14:47
         * @version 1.0.0
         * @description 填充order
         */
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        setProduct(ois);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
            totalNumber += oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    @Override
    public int getSaleCount(int pid) {
        /**
         * @author litengbin
         * @method getSaleCount
         * @param       [pid]
         * @return int
         * @date 2018/2/21 14:49
         * @version 1.0.0
         * @description 获取销售总数
         */
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> ois = orderItemMapper.selectByExample(example);
        int result = 0;
        for (OrderItem oi : ois) {
            result += oi.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        /**
         * @author litengbin
         * @method listByUser
         * @param       [uid]
         * @return java.util.List<com.ltb.emall.pojo.OrderItem>
         * @date 2018/2/21 14:49
         * @version 1.0.0
         * @description 获取订单项列表--根据用户id
         */
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<OrderItem> result = orderItemMapper.selectByExample(example);
        setProduct(result);
        return result;
    }
}
