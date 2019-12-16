package cn.faceland.springbootjsoup;

import cn.faceland.springbootjsoup.download.DateTimeUtil;
import cn.faceland.springbootjsoup.download.DownloadByM3U8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URLDecoder;

/**
 * @author watermelon
 * @Date 2019-12-16
 * @Description
 */
public class JsoupHtml {
    public static void main(String[] args) {
        String url = "";
        String result = "";
        Document doc ;
        try {
            doc = Jsoup.connect(url).get();
            result = doc.html();
        }catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println(result);

        String videoUrl = getFileUrl(result);
        System.out.println("videoUrl:==" + videoUrl);

        String dir = DateTimeUtil.getDateTimeNow().replace(" ","").replace(":","").replace("-","");
        String tempDir = "D:\\video\\"+dir;
        System.out.println("tempDir:==" +tempDir);

        DownloadByM3U8.download(videoUrl,tempDir, "me.mp4");
    }

    public static String getFileUrl(String htmlContent){
        String content = htmlContent.substring(htmlContent.indexOf("file:\"https"));
        content = content.substring(6,content.indexOf("m3u8") +4);
//        System.out.println(URLDecoder.decode(content));
        return URLDecoder.decode(content);
    }
}
