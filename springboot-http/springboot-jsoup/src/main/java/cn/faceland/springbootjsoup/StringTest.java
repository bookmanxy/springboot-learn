package cn.faceland.springbootjsoup;

import java.net.URLDecoder;

/**
 * @author watermelon
 * @Date 2019-12-16
 * @Description
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "https%3A%2F%2www.baidu.com";
        System.out.println(URLDecoder.decode(str));
    }
}
