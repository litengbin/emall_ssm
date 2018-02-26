package com.ltb.emall.service;

import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.PropertyValue;

import java.util.List;

/**
 * ClassName: PropertyValueService
 * Description: propertyvalue业务接口
 * User: litengbin
 * Date: 2018/2/17 15:54
 * Version: 1.0.0
 */
public interface PropertyValueService {
    public void init(Product product);

    public void update(PropertyValue propertyValue);

    public PropertyValue get(int ppid, int pid);

    public List<PropertyValue> list(int pid);
}
