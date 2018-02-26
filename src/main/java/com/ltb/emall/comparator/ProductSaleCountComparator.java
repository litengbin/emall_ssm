package com.ltb.emall.comparator;

import com.ltb.emall.pojo.Product;

import java.util.Comparator;

/**
 * ClassName: ProductSaleCountComparator
 * Description: 销售比较器
 * User: litengbin
 * Date: 2018/2/20 20:20
 * Version: 1.0.0
 */
public class ProductSaleCountComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount() - p1.getSaleCount();
    }
}
