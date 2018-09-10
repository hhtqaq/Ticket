package com.ecjtu.rwx.dbutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dateutil {

	public static String changeDate(String str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date parse=null;
		try {
			parse = sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(parse.getTime());
	}
}
