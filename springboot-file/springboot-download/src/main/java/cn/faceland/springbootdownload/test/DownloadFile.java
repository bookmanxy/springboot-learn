package cn.faceland.springbootdownload.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static cn.faceland.springbootdownload.constants.StringConstants.VIDEO_URL;

/**
 * @author watermelon
 * @Date 2019-12-12
 * @Description
 */
public class DownloadFile {
    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }

        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);

            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String url = VIDEO_URL;
//        httpDownload("https://cloud.video.taobao.com/play/u/2204066007433/p/2/e/6/t/1/231529647869.mp4?appKey=38829","D:\\me.mp4");
        httpDownload(url,"D:\\video\\me.mp4");
    }
}
