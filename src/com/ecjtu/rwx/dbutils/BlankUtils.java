package com.ecjtu.rwx.dbutils;


public class BlankUtils {
/**
 * 判断是否是空串
 * @param str
 * @return
 */
	public static boolean isblank(String str){
		if("".equals(str)&&str==null){
			return true;
		}else {
			return false;
		}
	}
}
