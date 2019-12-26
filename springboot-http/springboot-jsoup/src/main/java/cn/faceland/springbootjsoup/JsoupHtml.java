package cn.faceland.springbootjsoup;

import cn.faceland.springbootjsoup.download.DateTimeUtil;
import cn.faceland.springbootjsoup.download.Download;
import cn.faceland.springbootjsoup.download.DownloadByM3U8;
import cn.faceland.springbootjsoup.download.DownloadFileWithProgress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.net.URLDecoder;

/**
 * @author watermelon
 * @Date 2019-12-16
 * @Description
 */
public class JsoupHtml {
    public static void main(String[] args) {
        String url = "http://www.100se.cc/play/368.shtml";
        String result = "";
        Document doc ;
        try {
//            doc = Jsoup.connect(url).get();
//            result = doc.html();
        }catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println(result);

//        String videoUrl = getFileUrl(result);
        String videoUrl = "88.mp4";
        System.out.println("videoUrl:==" + videoUrl);

        // TODO: 2019/12/17 可以考虑将视频地址存起来
        String dir = DateTimeUtil.getDateTimeNow().replace(" ","").replace(":","").replace("-","");
        String tempDir = "D:\\video\\"+dir;
        String name = "me.mp4";
        System.out.println("tempDir:==" +tempDir);

        if(videoUrl.endsWith("mp4")){
            Download.download(videoUrl,tempDir,name);
//            DownloadFileWithProgress.downVideo(videoUrl,tempDir ,"me","mp4");
        }else{
            DownloadByM3U8.download(videoUrl,tempDir, name);
        }

//
//        DownloadByM3U8.download(videoUrl,tempDir, "me.mp4");
    }

    public static String getFileUrl(String htmlContent){
        String content = htmlContent.substring(htmlContent.indexOf("file:\"https"));
        if(content.indexOf("m3u8")>0){
            content = content.substring(6,content.indexOf("m3u8") +4);
        }else{
            content = content.substring(6,content.indexOf("mp4") +3);
        }

//        System.out.println(URLDecoder.decode(content));
        return URLDecoder.decode(content);
    }
}
