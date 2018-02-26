package com.ltb.emall.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: UploadedImageFile
 * Description: 上传文件类
 * User: litengbin
 * Date: 2018/2/13 19:36
 * Version: 1.0.0
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
