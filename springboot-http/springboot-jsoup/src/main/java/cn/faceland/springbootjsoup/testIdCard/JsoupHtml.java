package cn.faceland.springbootjsoup.testIdCard;

import cn.faceland.springbootjsoup.download.DateTimeUtil;
import cn.faceland.springbootjsoup.download.Download;
import cn.faceland.springbootjsoup.download.DownloadByM3U8;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        String str;
        try {
            doc = Jsoup.connect(url).get();
            result = doc.html();

            Elements div = doc.getElementsByClass("tupian");
//            System.out.println(div.html());
            Elements ps = div.get(0).getElementsByTag("p");
            for(Element element : ps){
                str = element.html();
                str = str.substring(str.indexOf("→") + 2 );
                System.out.println(str);
//                System.out.println(element.html());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        System.out.println(result);


//        String videoUrl = getFileUrl(result);
//        String videoUrl = "88.mp4";
//        System.out.println("videoUrl:==" + videoUrl);
//
//        // TODO: 2019/12/17 可以考虑将视频地址存起来
//        String dir = DateTimeUtil.getDateTimeNow().replace(" ","").replace(":","").replace("-","");
//        String tempDir = "D:\\video\\"+dir;
//        String name = "me.mp4";
//        System.out.println("tempDir:==" +tempDir);
//
//        if(videoUrl.endsWith("mp4")){
//            Download.download(videoUrl,tempDir,name);
////            DownloadFileWithProgress.downVideo(videoUrl,tempDir ,"me","mp4");
//        }else{
//            DownloadByM3U8.download(videoUrl,tempDir, name);
//        }

    }


}
