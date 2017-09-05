package com.midland.core.util;
public class Convert {
	/**
	 * 转换为int
	 * @param str 被转换之前的值
	 * @return
	 */
	public static int toInt32(Object str) {
		if(str==null){
			return 0;
		}else{
			try {
				return Integer.parseInt(str.toString());
			} catch (Exception e) {
				return 0;
			}
		}
	}
	
	public static int toInt32(Object str,int defaultValue) {
		if(str==null){
			return defaultValue;
		}else{
			try {
				return Integer.parseInt(str.toString());
			} catch (Exception e) {
				return defaultValue;
			}
		}
	}
	
	/**
	 * 装换为double
	 * @param str
	 * @return
	 */
	public static double toDouble(Object str) {
		if(str==null || str.equals("")){
			return 0;
		}else{
			try {
				return Double.parseDouble(str.toString());
			} catch (Exception e) {
				return 0;
			}
		}
	}
}
