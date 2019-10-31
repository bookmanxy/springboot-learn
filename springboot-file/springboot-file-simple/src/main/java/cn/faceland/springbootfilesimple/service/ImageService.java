package cn.faceland.springbootfilesimple.service;


import cn.faceland.springbootfilesimple.controller.ResultBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 */
public interface ImageService {
    ResultBean imageUpload(MultipartFile file);
}
