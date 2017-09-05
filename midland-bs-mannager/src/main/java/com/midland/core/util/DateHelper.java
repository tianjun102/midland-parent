package com.midland.core.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

	/**
	 * 获取当前的系统时间
	 * @return
	 */
	public static String getNow(){
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 获取当前的系统时间
	 * @return
	 */
	public static String getDateString(Date date){
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 将时间字符串转换为时间字符串
	 * @param dateStr
	 * @return
	 */
	public static String parseDate(String dateStr) {
		String retStr = "";
		try {
			Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			retStr = getDateString(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retStr;
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(String date,String format){
		Date parse = null;
		try {
			parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (parse==null) {
			parse = new Date();
		}
		
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(format);
		return simpleDateFormat.format(parse);
	}
	
	public static Date ToDate(String date)
	{
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			d  = new Date();
		}
		return d;
	}
	
	
	public static Date ToDate(String date,String strFormat)
	{
		SimpleDateFormat sdf =  new SimpleDateFormat(strFormat, Locale.CHINA);
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			d  = new Date();
		}
		return d;
	}
	
	/**
	 * 添加小时
	 * @param strTime
	 * @param hour
	 * @return
	 */
	public static String AddHour(String strTime, int hour)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
		date = sdf.parse(strTime);
		} catch (ParseException e) {
		// TODO 自动生成 catch 块
		e.printStackTrace();
		}
		Calendar ca=Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.HOUR_OF_DAY, hour);
		
		return sdf.format(ca.getTime());
	}
	
	/**
	 * 比较两个日期大小
	 * @param time1
	 * @param time2
	 * @return
	 * @throws ParseException
	 */
	public static boolean Before(String time1,String time2) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date d = sdf.parse(time1);
		Date d2 = sdf.parse(time2);
		
		return d.before(d2);
		
	}
	
}
