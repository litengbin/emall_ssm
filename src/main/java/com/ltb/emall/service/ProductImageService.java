package com.ltb.emall.service;

import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.ProductImage;

import java.util.List;

/**
 * ClassName: ProductImageService
 * Description: ProductImage业务接口
 * User: litengbin
 * Date: 2018/2/16 2:14
 * Version: 1.0.0
 */
public interface ProductImageService {
    String imageType_single = "type_single";
    String imageType_detail = "type_detail";

    public void add(ProductImage productImage);

    public void delete(int id);

    public void update(ProductImage productImage);

    public ProductImage get(int id);

    public List<ProductImage> list(int pid, String imageType);

}
