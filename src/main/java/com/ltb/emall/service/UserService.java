package com.ltb.emall.service;

import com.ltb.emall.pojo.User;

import java.util.List;

/**
 * ClassName: UserService
 * Description: 用户业务接口
 * User: litengbin
 * Date: 2018/2/17 22:30
 * Version: 1.0.0
 */
public interface UserService {
    public void add(User user);

    public void delete(int id);

    public void update(User user);

    public User get(int id);

    public List list();

    public boolean isExist(String name);

    User get(String name, String password);
}
