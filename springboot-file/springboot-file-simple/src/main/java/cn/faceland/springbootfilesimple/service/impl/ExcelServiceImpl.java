package cn.faceland.springbootfilesimple.service.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.service.ExcelService;
import cn.faceland.springbootfilesimple.util.ReadExcelUtils;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public ResultBean example1(MultipartFile file) {
        ResultBean rb = new ResultBean(true, 1, "导入成功");
        log.info("start to analysis excel");
        String realPath;
        ResultBean saveRb = saveExcel(file);
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

    public static String getJarPath(String subdirectory) {
        //获取跟目录---与jar包同级目录的upload目录下指定的子目录subdirectory
        File upload = null;
        try {
            //本地测试时获取到的是"工程目录/target/upload/subdirectory
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!path.exists()) {
                path = new File("");
            }
            upload = new File(path.getAbsolutePath(), subdirectory);
            if (!upload.exists()) {
                upload.mkdirs();
            }//如果不存在则创建目录
            String realPath = upload + "/";
            return realPath;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("获取服务器路径发生错误！");
        }
    }

    /**
     * 保存excel文件
     * @param file
     * @return
     */
    public static ResultBean saveExcel(MultipartFile file) {
        ResultBean rb = new ResultBean(true, 1, "保存成功");
        String realPath = "";
        try {
            //指定上传文件的临时保存目录位置
            String jarPath = getJarPath("upload");
            File path = new File(jarPath);
            log.info("jar path :{}", jarPath);

            realPath = path.getAbsolutePath() + File.separator + file.getOriginalFilename();

            System.out.println("realPath==" + realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream in = file.getInputStream();
            OutputStream out = new FileOutputStream(realPath);
            int len = 0;
            byte[] by = new byte[1024];
            while ((len = in.read(by)) > 0) {
                out.write(by, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            rb = new ResultBean(false, 2, "文件不存在");
            return rb;
        } catch (IOException e) {
            e.printStackTrace();
            rb = new ResultBean(false, 3, "文件保存出错");
            return rb;
        }

        rb.setData(realPath);

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
