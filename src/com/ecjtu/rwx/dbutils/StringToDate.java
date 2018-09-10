package com.ecjtu.rwx.dbutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate {

	/**
	 * 只能针对yyyy-MM-dd HH:mm  to yyyy/MM/dd HH:mm
	 * @param str
	 */
	public static String stringToDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String format ="";
		try {
			Date parse = sdf.parse(str);
			System.out.println(parse);
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm");
			format = sdf2.format(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format;
	}
}
