package cn.faceland.springbootfilesimple.manager;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import org.springframework.web.multipart.MultipartFile;

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
     * 获取jar的相对路径
     * @param dir 文件夹的名称
     * @return
     */
    String getJarPath(String dir);
}
