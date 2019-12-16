package cn.faceland.springbootdownload.test;

import cn.faceland.springbootdownload.domain.M3U8;
import cn.faceland.springbootdownload.util.DateTimeUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

import static cn.faceland.springbootdownload.constants.StringConstants.VIDEO_URL;

/**
 * @author watermelon
 * @Date 2019-12-16
 * @Description
 *  原文链接：https://blog.csdn.net/jjzhoulong/article/details/78622003
 */
public class DownloadByM3U8 {
//    public static String TEMP_DIR = "D:\\video\\temp";
    public static int connTimeout = 30 * 60 * 1000;
    public static int readTimeout = 30 * 60 * 1000;
//    public static String s1 = "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";
//    public static String s1 = VIDEO_URL;

    public static void main(String[] args) {
        String dir = DateTimeUtil.getDateTimeNow().replace(" ","").replace(":","").replace("-","");
        dir = "20191217000555";
        String tempDir = "D:\\video\\"+dir;

//        download(VIDEO_URL, tempDir,"me.ts");


        File tfile = new File(tempDir);
        mergeFiles(tfile.listFiles(), tempDir + File.separator + "me.ts");
//        System.out.println(dir);
    }

    public static void download(String netUrl,String tempDir,String fileName){
        File tfile = new File(tempDir);
        if (!tfile.exists()) {
            tfile.mkdirs();
        }

        M3U8 m3u8ByURL = getM3U8ByURL(netUrl);
        String basePath = m3u8ByURL.getBasepath();
        m3u8ByURL.getTsList().stream().parallel().forEach(m3U8Ts -> {
            String fullPathName = tempDir + File.separator + m3U8Ts.getFile();
            File file = new File(fullPathName);
            if (!file.exists()) {// 下载过的就不管了

                //用这个有进度条的
//                DownloadFileWithProgress.downVideo(basePath + m3U8Ts.getFile(),
//                        fullPathName);

                FileOutputStream fos = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(basePath + m3U8Ts.getFile());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(connTimeout);
                    conn.setReadTimeout(readTimeout);
                    if (conn.getResponseCode() == 200) {
                        inputStream = conn.getInputStream();
                        fos = new FileOutputStream(file);// 会自动创建文件
                        int len = 0;
                        byte[] buf = new byte[1024];
                        while ((len = inputStream.read(buf)) != -1) {
                            fos.write(buf, 0, len);// 写入流中
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {// 关流
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {e.printStackTrace();}
                }
            }
        });
        System.out.println("文件下载完毕!");
        mergeFiles(tfile.listFiles(), tempDir + File.separator + fileName);
    }


    public static M3U8 getM3U8ByURL(String m3u8URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
            if (conn.getResponseCode() == 200) {
                String realUrl = conn.getURL().toString();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
                M3U8 ret = new M3U8();
                ret.setBasepath(basepath);

                String line;
                float seconds = 0;
                int mIndex;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("#")) {
                        if (line.startsWith("#EXTINF:")) {
                            line = line.substring(8);
                            if ((mIndex = line.indexOf(",")) != -1) {
                                line = line.substring(0, mIndex + 1);
                            }
                            try {
                                seconds = Float.parseFloat(line);
                            } catch (Exception e) {
                                seconds = 0;
                            }
                        }
                        continue;
                    }
                    if (line.endsWith("m3u8")) {
                        return getM3U8ByURL(basepath + line);
                    }
                    ret.addTs(new M3U8.Ts(line, seconds));
                    seconds = 0;
                }
                reader.close();

                return ret;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static boolean mergeFiles(File[] fpaths, String resultPath) {
        System.out.println("resultPath:====" + resultPath);
        if (fpaths == null || fpaths.length < 1) {
            return false;
        }

        if (fpaths.length == 1) {
            return fpaths[0].renameTo(new File(resultPath));
        }
        for (int i = 0; i < fpaths.length; i++) {
            if (!fpaths[i].exists() || !fpaths[i].isFile()) {
                return false;
            }
        }
        File resultFile = new File(resultPath);

        try {
            FileOutputStream fs = new FileOutputStream(resultFile, true);
            FileChannel resultFileChannel = fs.getChannel();
            FileInputStream tfs;
            for (int i = 0; i < fpaths.length; i++) {
                tfs = new FileInputStream(fpaths[i]);
                FileChannel blk = tfs.getChannel();
                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
                tfs.close();
                blk.close();
            }
            fs.close();
            resultFileChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

         for (int i = 0; i < fpaths.length; i ++) {
            fpaths[i].delete();
         }

        return true;
    }


}
