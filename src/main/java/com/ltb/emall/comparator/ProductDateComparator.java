package com.ltb.emall.comparator;

import com.ltb.emall.pojo.Product;

import java.util.Comparator;

/**
 * ClassName: ProductDateComparator
 * Description: 新品比较器-时间最新最前
 * User: litengbin
 * Date: 2018/2/20 20:15
 * Version: 1.0.0
 */
public class ProductDateComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return p1.getCreateDate().compareTo(p2.getCreateDate());
    }
}
