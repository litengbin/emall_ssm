package com.ltb.emall.comparator;

import com.ltb.emall.pojo.Product;

import java.util.Comparator;

/**
 * ClassName: ProductReviewComparator
 * Description: 人气比较器
 * User: litengbin
 * Date: 2018/2/20 20:12
 * Version: 1.0.0
 */
public class ProductReviewComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() - p1.getReviewCount();
    }
}
