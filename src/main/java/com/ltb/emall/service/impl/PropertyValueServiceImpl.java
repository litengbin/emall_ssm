package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.PropertyValueMapper;
import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.Property;
import com.ltb.emall.pojo.PropertyValue;
import com.ltb.emall.pojo.PropertyValueExample;
import com.ltb.emall.service.PropertyService;
import com.ltb.emall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PropertyValueServiceImpl
 * Description: propertyvalue业务接口实现类
 * User: litengbin
 * Date: 2018/2/17 16:11
 * Version: 1.0.0
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    @Override
    public void init(Product product) {
        /**
         * @author litengbin
         * @method init
         * @param       [product]
         * @return void
         * @date 2018/2/17 17:21
         * @version 1.0.0
         * @description 初始化属性值
         */
        List<Property> pts = propertyService.list(product.getCid());
        for (Property pt : pts) {
            PropertyValue propertyValue = get(pt.getId(), product.getId());
            if (propertyValue == null) {
                propertyValue = new PropertyValue();
                propertyValue.setPid(product.getId());
                propertyValue.setPpid(pt.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    @Override
    public void update(PropertyValue propertyValue) {
        /**
         * @author litengbin
         * @method update
         * @param       [propertyValue]
         * @return void
         * @date 2018/2/17 17:22
         * @version 1.0.0
         * @description 修改属性值
         */
        propertyValueMapper.updateByPrimaryKeySelective(propertyValue);
    }

    @Override
    public PropertyValue get(int ppid, int pid) {
        /**
         * @author litengbin
         * @method get
         * @param       [ppid, pid]
         * @return com.ltb.emall.pojo.PropertyValue
         * @date 2018/2/17 17:22
         * @version 1.0.0
         * @description 获取属性值
         */
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPpidEqualTo(ppid).andPidEqualTo(pid);
        //example.setOrderByClause("id desc");
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty()) {
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        /**
         * @author litengbin
         * @method list
         * @param       [pid]
         * @return java.util.List<com.ltb.emall.pojo.PropertyValue>
         * @date 2018/2/17 17:22
         * @version 1.0.0
         * @description 获取属性值列表
         */
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        //example.setOrderByClause("id desc");
        List<PropertyValue> list = propertyValueMapper.selectByExample(example);
        for (PropertyValue p : list) {
            p.setProperty(propertyService.get(p.getPpid()));
        }
        return list;
    }
}
