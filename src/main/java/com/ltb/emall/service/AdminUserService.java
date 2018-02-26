package com.ltb.emall.service;

import com.ltb.emall.pojo.AdminUser;
import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.User;

import java.util.List;

/**
 * ClassName: AdminUserService
 * Description: 管理员用户业务接口
 * User: litengbin
 * Date: 2018/2/25 23:13
 * Version: 1.0.0
 */
public interface AdminUserService {

    public void add(AdminUser adminUser);

    public void delete(int id);

    public void update(AdminUser adminUser);

    public AdminUser get(int id);

    public List list();

    public boolean isExist(String name);

    AdminUser get(String name, String password);
}
