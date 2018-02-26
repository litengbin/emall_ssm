package com.ltb.emall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ltb.emall.pojo.Category;
import com.ltb.emall.service.CategoryService;
import com.ltb.emall.util.ImageUtil;
import com.ltb.emall.util.Page;
import com.ltb.emall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: CategoryController
 * Description: Category控制类
 * User: litengbin
 * Date: 2018/2/12 14:01
 * Version: 1.0.0
 */
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        /**
         * @author litengbin
         * @method list
         * @param       [model, page]
         * @return java.lang.String
         * @date 2018/2/13 21:22
         * @version 1.0.0
         * @description 分类列表
         */
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        //List<Category> cs = categoryService.list(page);
        //int total = categoryService.total();
        model.addAttribute("cs", cs);
        page.setTotal(total);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        /**
         * @author litengbin
         * @method add
         * @param       [c, session, uploadedImageFile]
         * @return java.lang.String
         * @date 2018/2/15 2:18
         * @version 1.0.0
         * @description 新增分类
         */
        categoryService.add(c);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        /**
         * @author litengbin
         * @method delete
         * @param       [id, session]
         * @return java.lang.String
         * @date 2018/2/15 2:18
         * @version 1.0.0
         * @description 删除分类
         */
        categoryService.delete(id);
        File fileFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(fileFolder, id + ".jpg");
        file.delete();
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) {
        /**
         * @author litengbin
         * @method edit
         * @param       [id, model]
         * @return java.lang.String
         * @date 2018/2/15 2:19
         * @version 1.0.0
         * @description 编辑分类
         */
        Category c = categoryService.get(id);
        model.addAttribute("c", c);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        /**
         * @author litengbin
         * @method update
         * @param       [category, session, uploadedImageFile]
         * @return java.lang.String
         * @date 2018/2/15 2:19
         * @version 1.0.0
         * @description 修改分类
         */
        categoryService.update(category);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder, category.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:admin_category_list";
    }
}
