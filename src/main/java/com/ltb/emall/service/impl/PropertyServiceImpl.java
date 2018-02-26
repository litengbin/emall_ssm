package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.PropertyMapper;
import com.ltb.emall.pojo.Property;
import com.ltb.emall.pojo.PropertyExample;
import com.ltb.emall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PropertyServiceImpl
 * Description: property业务接口实现类
 * User: litengbin
 * Date: 2018/2/15 17:36
 * Version: 1.0.0
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyMapper propertyMapper;

    @Override
    public void add(Property property) {
        /**
         * @author litengbin
         * @method add
         * @param       [property]
         * @return void
         * @date 2018/2/15 17:49
         * @version 1.0.0
         * @description 新增属性
         */
        propertyMapper.insert(property);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/15 17:49
         * @version 1.0.0
         * @description 删除属性
         */
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Property property) {
        /**
         * @author litengbin
         * @method update
         * @param       [property]
         * @return void
         * @date 2018/2/15 17:49
         * @version 1.0.0
         * @description 修改属性
         */
        propertyMapper.updateByPrimaryKeySelective(property);
    }

    @Override
    public Property get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.Property
         * @date 2018/2/15 17:49
         * @version 1.0.0
         * @description 获取属性
         */
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Property> list(int cid) {
        /**
         * @author litengbin
         * @method list
         * @param       [cid]
         * @return java.util.List<com.ltb.emall.pojo.Property>
         * @date 2018/2/15 17:49
         * @version 1.0.0
         * @description 获取属性列表
         */
        PropertyExample example = new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        return propertyMapper.selectByExample(example);
    }
}
