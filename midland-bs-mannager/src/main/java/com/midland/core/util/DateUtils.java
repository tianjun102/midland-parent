package com.midland.core.util;



import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期格式实用类
 *
 * @author zhangrq
 *
 * @since 2016年07月10日下午4:06:09
 */
public class DateUtils {

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YY_MM_DD = "yyyy/MM/dd";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_YYYYMM = "yyyyMM";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_HH_MM_SS = "HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 获取日期为YYYY-MM-DD格式
     *
     * @return
     */
    public static String formatDateToStringYYMMDD(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取日期为YYYY-MM-DD格式
     *
     * @return
     */
    public static String formatDateToStringYYMMDDHHmmss(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String nowDateToStringYYMMDDHHmmss() {
        Date date = nowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String nowDateToStringYYMM() {
        Date date = nowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYYMM);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


    /**
     * 获取今年的第一个月份
     *
     * @return
     */
    public static String getCurrentYearFirstMonth() {
        Calendar cla = Calendar.getInstance();
        return cla.get(Calendar.YEAR) + "01";
    }

    /**
     * 根据日期字符，转化日期格式为：YYYY-MM-DD
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMMDD(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM_DD);
    }

    /**
     * 根据日期字符，转化日期格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMMDDHHmmss(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 根据日期字符，转化日期格式为：yyyy-MM-dd HH:mm
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMMDDHHmm(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM_DD_HH_MM);
    }

    public static Date parseStringToDate(String value, String format) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static int getCurrentUnixTimestamp() {
        return getUnixTimestamp(nowDate());
    }

    public static int getUnixTimestamp(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return (int) (calendar.getTimeInMillis() / 1000);
        }
        return 0;
    }

    public static Long getUnixTimestampByLong() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    public static int compareDate(Date left, Date right) {
        int leftUnix = getUnixTimestamp(left);
        int rightUnix = getUnixTimestamp(right);
        if (leftUnix > rightUnix) {
            return 1;
        } else if (leftUnix == rightUnix) {
            return 0;
        }
        return -1;
    }
}
