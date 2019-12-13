package cn.faceland.springbootexcelcsv.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author watermelon
 * @Date 2019-12-12
 * @Description
 */
@Slf4j
public class CSVUtils {
    //上传文件的存储位置
//    private final static URL PATH = Thread.currentThread().getContextClassLoader().getResource("");
    /**
     * @return File
     * @Description 上传文件
     * @Param multipartFile
     **/
    public static File uploadFile(MultipartFile multipartFile) {
        String path = getJarPath("csv") + multipartFile.getOriginalFilename();
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
            log.info("上传文件成功，文件名===>" + multipartFile.getOriginalFilename() + ", 路径===>" + file.getPath());
            return file;
        } catch (IOException e) {
            log.error("上传文件失败" + e.getMessage(), e);
            return null;
        }
    }

    /**
     * @return List<List<String>>
     * @Description 读取CSV文件的内容（不含表头）
     * @Param filePath 文件存储路径，colNum 列数
     **/
    public static List<List<String>> readCSV(String filePath, int colNum) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
//          表内容集合，外层List为行的集合，内层List为字段集合
            List<List<String>> values = new ArrayList<>();
            int rowIndex = 0;

            for (CSVRecord record : parser.getRecords()) {
//              跳过表头
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
//              每行的内容
                List<String> value = new ArrayList<>(colNum + 1);
                for (int i = 0; i < colNum; i++) {
                    value.add(record.get(i));
                }
                values.add(value);
                rowIndex++;
            }
            return values;
        } catch (IOException e) {
            log.error("解析CSV内容失败" + e.getMessage(), e);
        }finally {
            //关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取jar包的相对路径
     * @param subdirectory
     * @return
     */
    private static String getJarPath(String subdirectory) {
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
     * 写数据到csv文件并保存
     */
    public void  writeToCsv(){
        try{
            Appendable out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getJarPath("csv") + "\\file.csv"), "gbk"));
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            //写一行数据
            printer.printRecord("","","");
            //空格行
            printer.println();

            printer.flush();
            printer.close();
        }catch (Exception e){

        }
    }
}
