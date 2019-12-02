package cn.faceland.springbootlimitratelimiter.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


//import net.sf.json.JSONArray;


public class ApiUtil {
    /**
     * 获取请求的IP地址
     * @param request 请求request
     * @return 请求的ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取浏览器客户端
     * @param request  请求request
     * @return	浏览器客户端名称
     */
    public static String getUserBrows(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        return BrowsUtil.checkBrowse(userAgent);
    }

    public static String getRequestContent(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = request.getReader();
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String postContent = sb.toString();
        request.setAttribute("postContent", postContent);
        return postContent;
    }
}
