package cn.faceland.stringformat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author watermelon
 * @Date 2020-01-07
 * @Description
 */
public class GetSpecialStr {
    /**
     * 1 提取中文：regEx=“[\\u4e00-\\u9fa5]";
     *
     * 2 提取数字：regEx=“[0-9]";
     *
     * 3 提取英文：regEx=“[a-zA-Z0-9]";
     *
     * 4 提取英文和数字：regEx=“[a-zA-Z0-9]";
     * @param str
     */
    public static void test (String str){
//        String str = "……^1dsf の adS  DFASFSADF阿德斯防守对方asdfsadf37《？：？@%#￥%#￥%@#$%#@$%^><?1234";
        String regEx="[a-zA-Z0-9\\u4e00-\\u9fa5]";
        Pattern p  =  Pattern.compile(regEx);
        Matcher m  =  p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            sb.append(m.group());
        }
        System.out.println(sb);
    }
}
