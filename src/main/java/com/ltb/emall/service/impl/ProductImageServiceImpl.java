package com.ltb.emall.service.impl;

import com.ltb.emall.mapper.ProductImageMapper;
import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.ProductImage;
import com.ltb.emall.pojo.ProductImageExample;
import com.ltb.emall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProductImageServiceImpl
 * Description: productimage业务接口实现类
 * User: litengbin
 * Date: 2018/2/16 2:31
 * Version: 1.0.0
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage productImage) {
        /**
         * @author litengbin
         * @method add
         * @param       [productImage]
         * @return void
         * @date 2018/2/16 2:38
         * @version 1.0.0
         * @description 新增产品图片
         */
        productImageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id]
         * @return void
         * @date 2018/2/16 2:38
         * @version 1.0.0
         * @description 删除产品图片
         */
        productImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        /**
         * @author litengbin
         * @method update
         * @param       [productImage]
         * @return void
         * @date 2018/2/16 2:39
         * @version 1.0.0
         * @description 修改产品图片
         */
        productImageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        /**
         * @author litengbin
         * @method get
         * @param       [id]
         * @return com.ltb.emall.pojo.ProductImage
         * @date 2018/2/16 2:39
         * @version 1.0.0
         * @description 获取产品图片
         */
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductImage> list(int pid, String imageType) {
        /**
         * @author litengbin
         * @method list
         * @param       [pid, type]
         * @return java.util.List<com.ltb.emall.pojo.ProductImage>
         * @date 2018/2/16 2:39
         * @version 1.0.0
         * @description 获取产品列表
         */
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid).andImageTypeEqualTo(imageType);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }
}
