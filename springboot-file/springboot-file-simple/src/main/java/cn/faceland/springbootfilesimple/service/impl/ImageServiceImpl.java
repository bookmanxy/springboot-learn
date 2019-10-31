package cn.faceland.springbootfilesimple.service.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import cn.faceland.springbootfilesimple.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;


/**
 * @description 用户api
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {
    @Autowired
    private FileManager fileManager;

    @Override
    public ResultBean imageUpload(MultipartFile file) {
        return fileManager.saveFile(file,"image");
    }
}
