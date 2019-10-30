package cn.faceland.springbootfilesimple.service;


import cn.faceland.springbootfilesimple.controller.ResultBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 */
public interface ExcelService {
    ResultBean example1(MultipartFile file);
}
