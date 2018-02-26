package com.ltb.emall.comparator;

import com.ltb.emall.pojo.Product;

import java.util.Comparator;

/**
 * ClassName: ProductPriceComparator
 * Description: 价格比较器-低价最前
 * User: litengbin
 * Date: 2018/2/20 20:22
 * Version: 1.0.0
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice() - p2.getPromotePrice());
    }
}
