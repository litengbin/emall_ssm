package com.ltb.emall.service;

import com.ltb.emall.pojo.Category;
import com.ltb.emall.util.Page;

import java.util.List;

/**
 * ClassName: CategoryService
 * Description: Category业务接口
 * User: litengbin
 * Date: 2018/2/12 1:50
 * Version: 1.0.0
 */
public interface CategoryService {
    //public List<Category> list(Page page);

    public List<Category> list();

    //public int total();

    public void add(Category category);

    public void delete(int id);

    public Category get(int id);

    public void update(Category category);
    
}
