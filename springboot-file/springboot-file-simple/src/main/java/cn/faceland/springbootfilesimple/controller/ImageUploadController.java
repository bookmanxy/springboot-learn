package cn.faceland.springbootfilesimple.controller;

import cn.faceland.springbootfilesimple.service.ExcelService;
import cn.faceland.springbootfilesimple.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value="/image")
@Slf4j
public class ImageUploadController {
    @Autowired
    private ImageService imageService;

    @RequestMapping("/upload")
    public ResultBean upload(@RequestParam("upload") MultipartFile file) {
        log.debug("上传图片并存储");
        if (file.isEmpty()) {
            return new ResultBean(false,-1,"上传失败，请选择图片");
        }
        return imageService.imageUpload(file);
    }

    @RequestMapping("/mergeImgs")
    public ResultBean mergeImgs(String imgUrls) {
        log.debug("多张图合并成一张图");
        if (StringUtils.isBlank(imgUrls)) {
            return new ResultBean(false,-1,"请传图片地址");
        }
        return imageService.mergeImgs(imgUrls);
    }
}
