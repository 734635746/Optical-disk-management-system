package util;


import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

/**
  *  封装字符串常用操作
 * @author liuyoubin
 *
 */
public class StringUtil {
	
	/**
	 *  将Date对象转换成String
	 * @param date Date对象
	 * @return 日期字符串
	 */
	public static String DataToString(Date date) {
		
		SimpleDateFormat dafo = null;
		
		if(date!=null) {
			dafo = new SimpleDateFormat("yyyy-MM-dd");
			return dafo.format(date);
		}else {
			return null;
		}
	}

	/**
	 *  将Date对象转换成String
	 * @param date Date对象
	 * @return 日期字符串
	 */
	public static String DataToString_02(Date date) {
		
		SimpleDateFormat dafo = null;
		
		if(date!=null) {
			dafo = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
			return dafo.format(date);
		}else {
			return null;
		}
	}
	
	/**
	 * 将日期字符串转换成Date对象
	 * @param str 日期字符串
	 * @return Date对象
	 */
	public static Date StringToDate(String str) {
		
		SimpleDateFormat dafo = null;
		
		if(str!=null) {
			
			dafo = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				return dafo.parse(str);
			} catch (ParseException e) {
				return null;		
			}
		
		}else {
			return null;
		}
		
	}
	
	
}
