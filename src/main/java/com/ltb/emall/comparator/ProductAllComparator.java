package com.ltb.emall.comparator;

import com.ltb.emall.pojo.Product;

import java.util.Comparator;

/**
 * ClassName: ProductAllComparator
 * Description: 综合比较器
 * User: litengbin
 * Date: 2018/2/20 20:07
 * Version: 1.0.0
 */
public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() * p2.getSaleCount() - p1.getReviewCount() * p1.getSaleCount();
    }

}
