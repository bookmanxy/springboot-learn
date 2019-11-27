package cn.faceland.springbootfilesimple.service.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import cn.faceland.springbootfilesimple.service.ImageService;
import cn.faceland.springbootfilesimple.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResultBean mergeImgs(String imgUrls) {
        ResultBean rb = new ResultBean();
        String[] urls = imgUrls.split(",");
        if(urls.length < 1){
            rb = new ResultBean(false,-1,"请传图片地址");
            return rb;
        }
        List<String> bufferLists = new ArrayList<String>();
        for(String url : urls){
            bufferLists.add(url);
        }

        String url = ImageUtil.generateMergeImage(bufferLists);
        System.out.println(url);
        rb.setData(url);

        return rb;
    }
}
