package cn.faceland.springbootfilesimple.manager;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author watermelon on 2019/10/31 16:31
 * @description
 */
public interface FileManager {
    /**
     * \保存文件
     * @param file MultipartFile
     * @param dir 文件夹名称
     * @return
     */
    ResultBean saveFile(MultipartFile file, String dir);

    /**
     * 将流数据保存文件
     * @param is  InputStream
     * @param dir 文件夹名称
     * @param fileName 文件名称
     * @return
     */
    ResultBean saveFile(InputStream is, String dir,String fileName);


    /**
     * 获取jar的相对路径
     * @param dir 文件夹的名称
     * @return
     */
    String getJarPath(String dir);

    /**
     * 阅读文件的每一行
     * 参考：https://blog.csdn.net/super_master_/article/details/80285815
     * @param filePath
     * @return
     */
    List<String> readLines(String filePath);

    /**
     * 读取文件并一行一行
     * map的循环打开方式  https://blog.csdn.net/jianxia801/article/details/81540889
     */
    void readFileLinesTest();
}
