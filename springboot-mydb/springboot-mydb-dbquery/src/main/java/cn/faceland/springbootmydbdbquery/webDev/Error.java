package cn.faceland.springbootmydbdbquery.webDev;


import org.apache.commons.lang.StringUtils;

/**
 * 报告出错的类
 * @author Administrator
 *
 */
public class Error {
	public static void print(Exception e){
		if (null == e) {
			return;
		}
		System.out.println("\n\n ###### 出错"+ DateTimeUtil.getDateTimeNow()+" ###### \n");
		e.printStackTrace();
		System.out.println("\n\n");
	}
	public static void print(String s){
		if (StringUtils.isBlank(s)) {
			return;
		}
		System.out.println("\n\n ###### 出错"+ DateTimeUtil.getDateTimeNow()+" ###### \n");
		System.out.println(s);
		System.out.println("\n\n");
	}
	public static void print(String s, Exception e){
		if (StringUtils.isNotBlank(s)) {
			print(s);
		}
		if (null != e) {
			print(e);
		}
	}

}
