package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.CategoryMapper;
import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.CategoryExample;
import com.ltb.emall.service.CategoryService;
import com.ltb.emall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CategoryServiceImpl
 * Description: Category业务接口实现类
 * User: litengbin
 * Date: 2018/2/12 1:56
 * Version: 1.0.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        /**
         * @author litengbin
         * @method list
         * @param       []
         * @return java.util.List<com.ltb.emall.pojo.Category>
         * @date 2018/2/15 2:09
         * @version 1.0.0
         * @description 获取分类列表
         */
        //return categoryMapper.list();
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    //    @Override
//    public List<Category> list(Page page) {
//        /**
//         * @author litengbin
//         * @method list
//         * @param       [page]
//         * @return java.util.List<com.ltb.emall.pojo.Category>
//         * @date 2018/2/13 21:23
//         * @version 1.0.0
//         * @description 获取分类列表
//         */
//        return categoryMapper.list(page);
//    }
//
//
//    @Override
//    public int total() {
//        /**
//         * @author litengbin
//         * @method total
//         * @param       []
//         * @return int
//         * @date 2018/2/13 21:24
//         * @version 1.0.0
//         * @description 获取分类总数
//         */
//        return categoryMapper.total();
//    }


    @Override
    public void add(Category category) {
        /**
         * @author litengbin
         * @method add
         * @param       [category]
         * @return void
         * @date 2018/2/13 21:24
         * @version 1.0.0
         * @description 新增分类
         */
        //categoryMapper.add(category);
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/13 22:11
         * @version 1.0.0
         * @description 删除分类
         */
        //categoryMapper.delete(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.Category
         * @date 2018/2/13 22:22
         * @version 1.0.0
         * @description 获取分类
         */
        //return categoryMapper.get(id);
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        /**
         * @author litengbin
         * @method update
         * @param       [category]
         * @return void
         * @date 2018/2/15 1:38
         * @version 1.0.0
         * @description 更新分类
         */
        //categoryMapper.update(category);
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
