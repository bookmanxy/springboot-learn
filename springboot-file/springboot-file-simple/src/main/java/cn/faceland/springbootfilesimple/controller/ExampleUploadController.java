package cn.faceland.springbootfilesimple.controller;

import cn.faceland.springbootfilesimple.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author xy on 2018/8/30 13:48
 * @description excel上传
 */
@RestController
@RequestMapping(value="/excel")
@Slf4j
public class ExampleUploadController{
    @Autowired
    private ExcelService excelService;

    @RequestMapping("/example1")
    public ResultBean example1(@RequestParam("upload") MultipartFile file) {
        log.debug("Excel文件导入 example1");
        if (file.isEmpty()) {
            return new ResultBean(false,-1,"上传失败，请选择文件");
        }
        return excelService.example1(file);
    }
}
