package com.ltb.emall.service;

import com.ltb.emall.pojo.Category;
import com.ltb.emall.pojo.Product;

import java.util.List;

/**
 * ClassName: ProductService
 * Description: product业务接口
 * User: litengbin
 * Date: 2018/2/15 20:35
 * Version: 1.0.0
 */
public interface ProductService {
    public void add(Product product);

    public void delete(int id);

    public void update(Product product);

    public Product get(int id);

    public List<Product> list(int cid);

    public void setFirstProductImage(Product p);

    public void fill(List<Category> cs);

    public void fill(Category c);

    public void fillByRow(List<Category> cs);

    public void setSaleAndReviewNumber(Product p);

    public void setSaleAndReviewNumber(List<Product> ps);

    public List<Product> search(String keyword);
}
