package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.ProductImageMapper;
import com.ltb.emall.mapper.ProductMapper;
import com.ltb.emall.pojo.*;
import com.ltb.emall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Description: product业务接口实现类
 * User: litengbin
 * Date: 2018/2/15 20:37
 * Version: 1.0.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @Override
    public void add(Product product) {
        /**
         * @author litengbin
         * @method add
         * @param       [product]
         * @return void
         * @date 2018/2/15 20:41
         * @version 1.0.0
         * @description 新增产品
         */
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/15 20:41
         * @version 1.0.0
         * @description 删除产品
         */
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        /**
         * @author litengbin
         * @method update
         * @param       [product]
         * @return void
         * @date 2018/2/15 20:41
         * @version 1.0.0
         * @description 修改产品
         */
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.Product
         * @date 2018/2/15 20:42
         * @version 1.0.0
         * @description 获取产品
         */
        Product p = productMapper.selectByPrimaryKey(id);
        setFirstProductImage(p);
        setCategory(p);
        return p;
    }

    public void setCategory(List<Product> ps) {
        /**
         * @author litengbin
         * @method setCategory
         * @param       [ps]
         * @return void
         * @date 2018/2/17 14:50
         * @version 1.0.0
         * @description 给多个产品设置分类
         */
        for (Product p : ps) {
            setCategory(p);
        }
    }

    public void setCategory(Product p) {
        /**
         * @author litengbin
         * @method setCategory
         * @param       [p]
         * @return void
         * @date 2018/2/17 14:50
         * @version 1.0.0
         * @description 给单个产品设置分类
         */
        int cid = p.getCid();
        Category category = categoryService.get(cid);
        p.setCategory(category);
    }

    @Override
    public List<Product> list(int cid) {
        /**
         * @author litengbin
         * @method list
         * @param       [cid]
         * @return java.util.List<com.ltb.emall.pojo.Product>
         * @date 2018/2/15 20:42
         * @version 1.0.0
         * @description 获取产品列表
         */
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List list = productMapper.selectByExample(example);
        setCategory(list);
        setFirstProductImage(list);
        return list;
    }

    @Override
    public void setFirstProductImage(Product p) {
        /**
         * @author litengbin
         * @method setFirstProductImage
         * @param       [p]
         * @return void
         * @date 2018/2/17 14:47
         * @version 1.0.0
         * @description 给单个产品设置图片
         */
        List<ProductImage> pis = productImageService.list(p.getId(), "type_single");
        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            p.setFirstProductImage(pi);
        }
    }

    @Override
    public void fill(List<Category> cs) {
        /**
         * @author litengbin
         * @method fill
         * @param       [cs]
         * @return void
         * @date 2018/2/19 0:50
         * @version 1.0.0
         * @description 填充分类
         */
        for (Category c : cs) {
            fill(c);
        }
    }

    @Override
    public void fill(Category c) {
        /**
         * @author litengbin
         * @method fill
         * @param       [c]
         * @return void
         * @date 2018/2/19 0:50
         * @version 1.0.0
         * @description 填充分类
         */
        List<Product> ps = list(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        /**
         * @author litengbin
         * @method fillByRow
         * @param       [cs]
         * @return void
         * @date 2018/2/19 0:56
         * @version 1.0.0
         * @description 一行8个-填充分类
         */
        int productNumberEachRow = 6;
        for (Category c : cs) {
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        /**
         * @author litengbin
         * @method setSaleAndReviewNumber
         * @param       [p]
         * @return void
         * @date 2018/2/19 20:53
         * @version 1.0.0
         * @description 为产品设置销售数量和评价数量
         */
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);
        int reviewCount = reviewService.count(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        /**
         * @author litengbin
         * @method setSaleAndReviewNumber
         * @param       [ps]
         * @return void
         * @date 2018/2/21 14:07
         * @version 1.0.0
         * @description 为产品设置销售数量和评价数量
         */
        for (Product p : ps) {
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        /**
         * @author litengbin
         * @method search
         * @param       [keyword]
         * @return java.util.List<com.ltb.emall.pojo.Product>
         * @date 2018/2/21 14:09
         * @version 1.0.0
         * @description 关键词搜索
         */
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setFirstProductImage(result);
        setCategory(result);
        return result;
    }

    public void setFirstProductImage(List<Product> ps) {
        /**
         * @author litengbin
         * @method setFirstProductImage
         * @param       [ps]
         * @return void
         * @date 2018/2/17 14:47
         * @version 1.0.0
         * @description 给多个产品设置图片
         */
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }
}
