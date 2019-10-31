package cn.faceland.springbootfilesimple.service.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import cn.faceland.springbootfilesimple.service.ExcelService;
import cn.faceland.springbootfilesimple.util.ReadExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;


/**
 * @description 用户api
 */
@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private FileManager fileManager;
    @Override
    public ResultBean example1(MultipartFile file) {
        ResultBean rb = new ResultBean(true, 1, "导入成功");
        log.info("start to analysis excel");
        String realPath;
        ResultBean saveRb = fileManager.saveFile(file,"excel");
        if (saveRb.getSuccess()) {
            realPath = (String) saveRb.getData();
        } else {
            return saveRb;
        }

        Map<Integer, Map<Integer,Object>> map;
        ResultBean dataMapRb = getDataMap(realPath);
        if (dataMapRb.getSuccess()) {
            map = (Map<Integer, Map<Integer,Object>>)getDataMap(realPath).getData();
        } else {
            return dataMapRb;
        }

        /** 顶部无效行数*/
        Integer unUserLines = 1;
        /** 每行的数据集合*/
        Map rowData;
        for (int i = unUserLines; i <= map.size(); i++) {
            log.info("read line index now:{}",i);
            rowData = map.get(i);

            System.out.println(rowData.get(0) + "" + rowData.get(1));

        }

        log.info("done to analysis excel");
        return rb;
    }

    /**
     * 解析excel文件，获取数据到map中
     * @param realPath
     * @return
     */
    public static ResultBean getDataMap(String realPath) {
        ResultBean rb = new ResultBean(true, 1, "解析数据成功");
        ReadExcelUtils excelReader = new ReadExcelUtils(realPath);
        Map<Integer, Map<Integer, Object>> map = null;
        try {
            map = excelReader.readExcelContent();
        } catch (Exception e) {
            e.printStackTrace();
            rb = new ResultBean(false, 4, "不是Excel文件");
            return rb;
        }
        if (null == map) {
            rb = new ResultBean(false, 5, "Excel读取失败");
            return rb;
        }

        rb.setData(map);

        return rb;
    }

}
