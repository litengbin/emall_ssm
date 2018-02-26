package com.ltb.emall.controller;

import com.ltb.emall.pojo.Product;
import com.ltb.emall.pojo.ProductImage;
import com.ltb.emall.service.ProductImageService;
import com.ltb.emall.service.ProductService;
import com.ltb.emall.util.ImageUtil;
import com.ltb.emall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * ClassName: ProductImageController
 * Description: productimage控制类
 * User: litengbin
 * Date: 2018/2/16 3:28
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession session, UploadedImageFile uploadedImageFile) {
        /**
         * @author litengbin
         * @method add
         * @param       [pi, session, uploadedImageFile]
         * @return java.lang.String
         * @date 2018/2/18 14:43
         * @version 1.0.0
         * @description 新增产品图片
         */
        productImageService.add(pi);
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.imageType_single.equals(pi.getImageType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }
        File f = new File(imageFolder, fileName);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            uploadedImageFile.getImage().transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img, "jpg", f);
            if (ProductImageService.imageType_single.equals(pi.getImageType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);
                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid=" + pi.getPid();
    }

//    @RequestMapping("admin_productImage_addtest")
//    public String addtest(HttpSession session) {
//        String picPath = "C:/Users/Administrator/Desktop/1.jpg";
//        String imageFolder;
//        String imageFolder_small = null;
//        String imageFolder_middle = null;
//        String fileName;
//        for (int i = 1; i <= 75; i++) {
//            for (int j = 1; j <= 10; j++) {
//                ProductImage productImage = new ProductImage();
//                productImage.setPid(i);
//                if (j <= 5) {
//                    productImage.setImageType(productImageService.imageType_single);
//                    productImageService.add(productImage);
//                    fileName = productImage.getId() + ".jpg";
//                    imageFolder = session.getServletContext().getRealPath("img/productSingle");
//                    imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
//                    imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
//                } else {
//                    productImage.setImageType(productImageService.imageType_detail);
//                    productImageService.add(productImage);
//                    fileName = productImage.getId() + ".jpg";
//                    imageFolder = session.getServletContext().getRealPath("img/productDetail");
//                }
//                File f = new File(imageFolder, fileName);
//                if (!f.getParentFile().exists()) {
//                    f.getParentFile().mkdirs();
//                }
//                try {
//                    BufferedImage image = ImageIO.read(new File(picPath));
//                    ImageIO.write(image, "jpg", f);
//                    BufferedImage img = ImageUtil.change2jpg(f);
//                    ImageIO.write(img, "jpg", f);
//                    if (ProductImageService.imageType_single.equals(productImage.getImageType())) {
//                        File f_small = new File(imageFolder_small, fileName);
//                        File f_middle = new File(imageFolder_middle, fileName);
//                        ImageUtil.resizeImage(f, 56, 56, f_small);
//                        ImageUtil.resizeImage(f, 217, 190, f_middle);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return "redirect:forehome";
//    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id, HttpSession session) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id, session]
         * @return java.lang.String
         * @date 2018/2/18 14:43
         * @version 1.0.0
         * @description 删除产品图片
         */
        ProductImage pi = productImageService.get(id);
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.imageType_single.equals(pi.getImageType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder, fileName);
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder, fileName);
            imageFile.delete();
        }
        productImageService.delete(id);
        return "redirect:admin_productImage_list?pid=" + pi.getPid();
    }

    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model) {
        /**
         * @author litengbin
         * @method list
         * @param       [pid, model]
         * @return java.lang.String
         * @date 2018/2/18 14:43
         * @version 1.0.0
         * @description 获取产品图片列表
         */
        Product p = productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, ProductImageService.imageType_single);
        List<ProductImage> pisDetail = productImageService.list(pid, ProductImageService.imageType_detail);
        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "admin/listProductImage";
    }
}
