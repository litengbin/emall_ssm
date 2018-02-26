package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.OrderMapper;
import com.ltb.emall.mapper.UserMapper;
import com.ltb.emall.pojo.Order;
import com.ltb.emall.pojo.OrderExample;
import com.ltb.emall.pojo.OrderItem;
import com.ltb.emall.pojo.User;
import com.ltb.emall.service.OrderItemService;
import com.ltb.emall.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: OrderServiceImpl
 * Description: order业务接口实现类
 * User: litengbin
 * Date: 2018/2/18 1:14
 * Version: 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order order) {
        /**
         * @author litengbin
         * @method add
         * @param       [order]
         * @return void
         * @date 2018/2/18 14:48
         * @version 1.0.0
         * @description 新增订单
         */
        orderMapper.insert(order);
    }

    @Override
    public void update(Order order) {
        /**
         * @author litengbin
         * @method update
         * @param       [order]
         * @return void
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description 修改订单
         */
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description 删除订单
         */
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Order get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.Order
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description 获取订单
         */
        Order o = orderMapper.selectByPrimaryKey(id);
        setUser(o);
        return o;
    }

    @Override
    public List<Order> list() {
        /**
         * @author litengbin
         * @method list
         * @param       []
         * @return java.util.List<com.ltb.emall.pojo.Order>
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description 获取订单列表
         */
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> os = orderMapper.selectByExample(example);
        setUser(os);
        return os;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order o, List<OrderItem> ois) {
        /**
         * @author litengbin
         * @method add
         * @param       [o, ois]
         * @return float
         * @date 2018/2/24 15:23
         * @version 1.0.0
         * @description 新增订单
         */
        float total = 0;
        add(o);
        if (false) {
            throw new RuntimeException();
        }
        for (OrderItem oi : ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
        }
        return total;
    }

    @Override
    public List list(int uid, String excludedStatus) {
        /**
         * @author litengbin
         * @method list
         * @param       [uid, excludedStatus]
         * @return java.util.List
         * @date 2018/2/24 15:55
         * @version 1.0.0
         * @description 用户订单
         */
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    public void setUser(List<Order> os) {
        /**
         * @author litengbin
         * @method setUser
         * @param       [os]
         * @return void
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description set用户
         */
        for (Order o : os) {
            setUser(o);
        }
    }

    public void setUser(Order o) {
        /**
         * @author litengbin
         * @method setUser
         * @param       [o]
         * @return void
         * @date 2018/2/18 14:49
         * @version 1.0.0
         * @description set用户
         */
        User user = userMapper.selectByPrimaryKey(o.getUid());
        o.setUser(user);
    }
}
