package cn.faceland.springbootfilesimple.manager.impl;

import cn.faceland.springbootfilesimple.controller.ResultBean;
import cn.faceland.springbootfilesimple.manager.FileManager;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

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

    @Override
    public List<String> readLines(String filePath) {
        List<String> list = new LinkedList();
        try{
            FileInputStream fis=new FileInputStream(filePath);
            InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //简写如下
            //BufferedReader br = new BufferedReader(new InputStreamReader(
            //        new FileInputStream("E:/phsftp/evdokey/evdokey_201103221556.txt"), "UTF-8"));
            String line="";

            while ((line=br.readLine())!=null) {
//                arrs=line.split(",");
                list.add(line);
            }
            br.close();
            isr.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void readFileLinesTest() {
        HashMap<String,Integer> map = new LinkedHashMap<>();
        HashMap<String,RecordItem> map2 = new LinkedHashMap<>();
        RecordItem recordItem;
        List<String> uris;

        String ip = "";
        String time = "";
        String uriOrgi = "";
        Integer times = 0;
        List<String> list = readLines("F:/download/access.log");
        for(String line : list){

//            System.out.println(line);
            if(line.contains("Dec/2019:")&&line.contains("tang")){
                ip = line.substring(0,line.indexOf(" -"));
                time = line.substring(line.indexOf("Dec/2019") + 4,line.indexOf("Dec/2019") + 17);
                uriOrgi = line.substring(line.indexOf("0800]") + 5,line.indexOf("HTTP/1."));
//                    System.out.println("第" + i + "条;ip：" + ip
//                            + ";time:" + time
//                            + ";uri:" + uri
//                    );

                if(map.containsKey(time)){
                    times = map.get(time);
                    times++;
                    map.put(time,times);
                }else{
                    map.put(time,1);
                }

                if(map2.containsKey(time)){
                    recordItem = map2.get(time);
                    times++;
                    recordItem.setTimes(times);
                    uris = recordItem.getUris();
                    uris.add(uriOrgi);
                    recordItem.setUris(uris);
                    map2.put(time,recordItem);
                }else{
                    recordItem = new RecordItem();
                    uris = new ArrayList<>();
                    uris.add(uriOrgi);
                    recordItem.setTimes(1);
                    recordItem.setUris(uris);
                    map2.put(time, recordItem);
                }
            }
        }

        HashMap<String,Integer> map3 = new LinkedHashMap<>();
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        String uriKey = "";
        Integer uriTimes = 0;
        while (it.hasNext()){
            Map.Entry<String, Integer> item = it.next();
//            if(item.getValue() >=1 && (item.getKey().contains("2019:08:2") || item.getKey().contains("2019:08:3"))){
            if(item.getValue() >=1){

//                System.out.println(item.getKey() + "   :  " + item.getValue());

                recordItem = map2.get(item.getKey());
                for(String uri : recordItem.getUris()){
//                    System.out.println("       " + uri);

                    if(uri.contains("?")&&uri.contains("/tang")){
                        uriKey = uri.substring(uri.indexOf("/tang") + 5, uri.indexOf("?"));
//                        System.out.println(uri.substring(uri.indexOf("/tang") + 5, uri.indexOf("?")));
                        if(map3.containsKey(uriKey)){
                            uriTimes = map3.get(uriKey);
                            uriTimes++;
                            map3.put(uriKey,uriTimes);
                        }else{
                            map3.put(uriKey,1);
                        }
                    }
                }
            }
        }

        for(String key : map3.keySet()){
            System.out.println(key + "   :  " + map3.get(key));
        }
    }

    @Data
    @Accessors(chain = true)
    private class RecordItem{
        private String time;
        private Integer times;
        private List<String> uris;
    }
    public static void main(String[] args) {
        FileManagerImpl fileManager = new FileManagerImpl();
        fileManager.readFileLinesTest();

//        List<String> list = fileManager.readLines("F:/download/access.log");
//        for(String str : list){
//            System.out.println(str);
//        }
    }
}
