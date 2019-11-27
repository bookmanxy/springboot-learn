package cn.faceland.springbootfilesimple.manager.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author watermelon on 2019/10/31 16:31
 * @description
 */
@Slf4j
@Service
public class FileManagerImpl implements FileManager {

    @Override
    public ResultBean saveFile(MultipartFile file, String dir) {
        ResultBean rb = new ResultBean(true, 1, "保存成功");
        String realPath = "";
        try {
            //指定上传文件的临时保存目录位置
            String jarPath = getJarPath(dir);
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

    @Override
    public ResultBean saveFile(InputStream is, String dir,String fileName) {
        ResultBean rb = new ResultBean(true, 1, "保存成功");
        String realPath = "";
        try {
            //指定上传文件的临时保存目录位置
            String jarPath = getJarPath(dir);
            File path = new File(jarPath);
            log.info("jar path :{}", jarPath);

            realPath = path.getAbsolutePath() + File.separator + fileName;

            System.out.println("realPath==" + realPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream in = is;
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

    @Override
    public  String getJarPath(String subdirectory) {
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
}
