package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.AdminUserMapper;
import com.ltb.emall.pojo.AdminUser;
import com.ltb.emall.pojo.AdminUserExample;
import com.ltb.emall.pojo.User;
import com.ltb.emall.pojo.UserExample;
import com.ltb.emall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: AdminUserServiceImpl
 * Description: 管理员用户业务接口实现类
 * User: litengbin
 * Date: 2018/2/25 23:14
 * Version: 1.0.0
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;

    @Override
    public void add(AdminUser adminUser) {
        adminUserMapper.insert(adminUser);
    }

    @Override
    public void delete(int id) {
        adminUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(AdminUser adminUser) {
        adminUserMapper.updateByPrimaryKeySelective(adminUser);
    }

    @Override
    public AdminUser get(int id) {
        return adminUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        AdminUserExample example = new AdminUserExample();
        example.setOrderByClause("id desc");
        return adminUserMapper.selectByExample(example);
    }

    @Override
    public boolean isExist(String name) {
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andNameEqualTo(name);
        List<AdminUser> result = adminUserMapper.selectByExample(example);
        if (!result.isEmpty())
            return true;
        return false;
    }

    @Override
    public AdminUser get(String name, String password) {
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<AdminUser> list = adminUserMapper.selectByExample(example);
        if (!list.isEmpty()) {
            AdminUser adminUser = list.get(0);
            return adminUser;
        } else {
            return null;
        }
    }
}
