package cn.faceland.springbootpdftopic.util;

import org.apache.commons.codec.binary.Base64;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xy on 2018/10/17 18:03
 * @description
 */
public class PDFUtil {
    /**
     * base64字符串转成PDF文件： filePath = 目录 + 文件名
     *
     * @param
     * @return
     * @author xy 2018/10/17 17:53
     */
    public static Boolean base64StringToPDF(String base64sString, String filePath) {
        Boolean result = true;
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            // 将base64编码的字符串解码成字节数组
            byte[] bytes = Base64.decodeBase64(base64sString);
            // apache公司的API
            // byte[] bytes = Base64.decodeBase64(base64sString);
            // 创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            // 创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);
            // 指定输出的文件
            File file = new File(filePath);
            if (!file.exists()) {
//                file.mkdirs();
                file.createNewFile();
            }
            // 创建到指定文件的输出流
            fout = new FileOutputStream(file);
            // 为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);

            byte[] buffers = new byte[1024];
            int len = bin.read(buffers);
            while (len != -1) {
                bout.write(buffers, 0, len);
                len = bin.read(buffers);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();

        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                bin.close();
                fout.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 本地pdf转图片
     * @param pdfPath  本地存放的文件地址
     * @param picDir 生成图片的文件夹名字
     * @return
     */
    public static List<File> pdfToPic(String pdfPath, String picDir) {
        List<File> picFileList = new ArrayList<>();
        Document document = new Document();
        try {
            document.setFile(pdfPath);
            float scale = 2.5f;//缩放比例
            float rotation = 0f;//旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                try {
                    File file = new File(getJarPath(picDir) + i + ".jpg");
                    picFileList.add(file);
                    ImageIO.write(rendImage, "jpg", file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            picFileList = new ArrayList<>();
        }
        return picFileList;
    }

    /**
     * 网路pdf转图片
     * @param downloadUrl
     * @param picDir
     * @return
     */
    public static List<File> pdfToPicFromUrl(String downloadUrl, String picDir) {
        List<File> picFileList = new ArrayList<>();
        Document document = new Document();
        try {
//            document.setFile(downloadUrl);
            URL url = new URL(downloadUrl);
            document.setUrl(url);
            float scale = 2.5f;//缩放比例
            float rotation = 0f;//旋转角度
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                try {
                    File file = new File(getJarPath(picDir) + i + ".jpg");
                    picFileList.add(file);
                    ImageIO.write(rendImage, "jpg", file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            picFileList = new ArrayList<>();
        }
        return picFileList;
    }

    public static void rmByFile(List<File> fileList){
        for(File file : fileList){
            rmByFile(file);
        }
    }

    public static Boolean rmByFile(File file){
        if(file.exists()){
            return file.delete();
        }
        return true;
    }

    public static void rmByPath(List<String> pathList){
        for(String path : pathList){
            rmByPath(path);
        }
    }

    public static Boolean rmByPath(String path){
        File file = new File(path);
        if(file.exists()){
            return rmByFile(file);
        }
        return true;
    }

    public static void main(String[] args) {
        String url = "https://picdev.bianla.cn/presentation/pdf/20181025/58666223c84e4bf78a1fdd5f0b4b435c.pdf";
        List<File> list = pdfToPicFromUrl(url,"pic");
//        List<File> list2 = pdfToPic("","pic2");
        for(File file : list){
            System.out.println(file.getAbsolutePath());
        }
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
}
