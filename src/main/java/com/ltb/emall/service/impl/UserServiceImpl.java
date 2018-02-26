package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.UserMapper;
import com.ltb.emall.pojo.User;
import com.ltb.emall.pojo.UserExample;
import com.ltb.emall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description: user业务接口实现类
 * User: litengbin
 * Date: 2018/2/17 22:31
 * Version: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        /**
         * @author litengbin
         * @method add
         * @param       [user]
         * @return void
         * @date 2018/2/18 14:50
         * @version 1.0.0
         * @description 新增用户
         */
        userMapper.insert(user);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/18 14:50
         * @version 1.0.0
         * @description 删除用户
         */
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        /**
         * @author litengbin
         * @method update
         * @param       [user]
         * @return void
         * @date 2018/2/18 14:50
         * @version 1.0.0
         * @description 修改用户
         */
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.User
         * @date 2018/2/18 14:50
         * @version 1.0.0
         * @description 获取用户
         */
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        /**
         * @author litengbin
         * @method list
         * @param       []
         * @return java.util.List
         * @date 2018/2/18 14:51
         * @version 1.0.0
         * @description 获取用户列表
         */
        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    @Override
    public boolean isExist(String name) {
        /**
         * @author litengbin
         * @method isExist
         * @param       [name]
         * @return boolean
         * @date 2018/2/19 19:21
         * @version 1.0.0
         * @description 检验用户名是否存在
         */
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> result = userMapper.selectByExample(example);
        if (!result.isEmpty())
            return true;
        return false;
    }

    @Override
    public User get(String name, String password) {
        /**
         * @author litengbin
         * @method get
         * @param       [name, password]
         * @return com.ltb.emall.pojo.User
         * @date 2018/2/19 19:21
         * @version 1.0.0
         * @description 获取user
         */
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> list = userMapper.selectByExample(example);
        if (!list.isEmpty()) {
            User user = list.get(0);
            return user;
        } else {
            return null;
        }
    }
}
