package com.ltb.emall.service;

import com.ltb.emall.pojo.Property;

import java.util.List;

/**
 * ClassName: PropertyService
 * Description: Property业务接口
 * User: litengbin
 * Date: 2018/2/15 15:47
 * Version: 1.0.0
 */
public interface PropertyService {
    public void add(Property property);

    public void delete(int id);

    public void update(Property property);

    public Property get(int id);

    public List<Property> list(int cid);
}
