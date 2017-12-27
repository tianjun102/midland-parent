package com.midland.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.midland.core.util.DateUtils;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.user.Agenter;
import com.midland.web.model.user.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

/**
 * Created by 'ms.x' on 2017/7/31.
 */
public class MidlandHelper {

    private static final String COMMON_DATE = "yyyy-MM-dd HH:mm:ss";
    private static final String MONTH = "yyyy-MM";
    private static final String CODE_FORMAT = "#00000000";
    private static final String LONG_CODE_FORMAT = "#000000000000";


    public static String scientificNotation(String s) {
        BigDecimal bigDecimal = new BigDecimal(s);
        return bigDecimal.toPlainString();
    }

    public static String formatMonth(String time) {
        Date tempTime = DateUtils.parseStringToDateYYMMDD(time);
        DateFormat format = new SimpleDateFormat(MONTH);
        return format.format(tempTime);
    }

    public static String formatCode(Integer data) {
        NumberFormat format;
        if (data < 99999999) {
            format = new DecimalFormat(CODE_FORMAT);
        } else {
            format = new DecimalFormat(LONG_CODE_FORMAT);
        }
        return format.format(data);
    }

    public static String getCurrentTime() {
        long intTime = System.currentTimeMillis();
        DateFormat format = new SimpleDateFormat(COMMON_DATE);
        return format.format(intTime);
    }

    public static String getMonth(Date time, Integer amount) {
        DateFormat format = new SimpleDateFormat(COMMON_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, amount);
        return format.format(calendar.getTime());
    }

    public static String getFormatPreMonth(String yyMMddtime, Integer amount) {
        Date tempTime = DateUtils.parseStringToDateYYMMDD(yyMMddtime);
        DateFormat format = new SimpleDateFormat(COMMON_DATE);
        ;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempTime);
        calendar.add(Calendar.MONTH, amount);
        return format.format(calendar.getTime());
    }

    public static String getFormatyyMMToMonth(String time, Integer amount) {
        StringBuffer sb = new StringBuffer(time);
        sb.append("-01");
        Date tempTime = DateUtils.parseStringToDateYYMMDD(sb.toString());
        DateFormat format = new SimpleDateFormat(MONTH);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempTime);
        calendar.add(Calendar.MONTH, amount);
        return format.format(calendar.getTime());
    }

    public static Date stringToDate(String time) throws ParseException {
        DateFormat format = new SimpleDateFormat(COMMON_DATE);
        return format.parse(time);
    }


    /**
     * request 包含 pageNo，pageSize
     *
     * @param request
     */
    public static void doPage(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        if (pageNo == null || pageNo.equals("")) {
            pageNo = ContextEnums.PAGENO;
        }
        if (pageSize == null || pageSize.equals("")) {

            pageSize = ContextEnums.PAGESIZE;
        }
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
    }

    /**
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List getPojoList(String str, Class<T> clazz) {
        List resList = new ArrayList<T>();
        JSONObject rootJsonObject = null;
        try {
            rootJsonObject = JSON.parseObject(str);
        } catch (Exception e) {
            return null;
        }
        String messageStr;
        if (rootJsonObject != null && rootJsonObject.getString("STATE").equals("SUCCESS")) {
            messageStr = rootJsonObject.getString("DATA");
        } else {
            return null;
        }
        List lists = JSON.parseObject(messageStr, List.class);
        for (Object obj : lists) {
            T t = JSON.parseObject(obj.toString(), clazz);
            resList.add(t);
        }
        return resList;
    }

    public static Agenter getPojo(String str) {
        JSONObject rootJsonObject = null;
        try {
            rootJsonObject = JSON.parseObject(str);
        } catch (Exception e) {
            return null;
        }
        String messageStr;
        if (rootJsonObject != null && rootJsonObject.getString("STATE").equals("SUCCESS")) {
            messageStr = rootJsonObject.getString("DATA");
        } else {
            return null;
        }
        Agenter lists = JSON.parseObject(messageStr, Agenter.class);
        return lists;
    }

    /**
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List getAgentPojoList(String str, Class<T> clazz) {
        List resList = new ArrayList<T>();
        JSONObject rootJsonObject = null;
        try {
            ;
            rootJsonObject = JSON.parseObject(str);
        } catch (Exception e) {
            return null;
        }
        String messageStr;
        if (rootJsonObject != null && rootJsonObject.getString("STATE").equals("SUCCESS")) {
            messageStr = rootJsonObject.getString("DATA");
        } else {
            return null;
        }
        //JSONObject jsonObject = JSONObject.parseObject(messageStr);
        //messageStr = jsonObject.getString("EBDATA");
        List lists = JSON.parseObject(messageStr, List.class);
        for (Object obj : lists) {
            T t = JSON.parseObject(obj.toString(), clazz);
            resList.add(t);
        }
        return resList;
    }

    /**
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getPojo(String str, Class<T> clazz) {
        JSONObject rootJsonObject = JSON.parseObject(str);
        String messageStr = rootJsonObject.getString("data");
        if (StringUtils.isEmpty(messageStr)){
            messageStr = rootJsonObject.getString("DATA");
        }
        T t = JSON.parseObject(messageStr, clazz);
        return t;
    }


    public static List<String> getStringRemoveEmpty(String ids) {
        if (StringUtils.isEmpty(ids)) {
            return null;
        }
        String[] array = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String str : array) {
            if (StringUtils.isNotEmpty(str)) {
                list.add(str);
            }
        }
        return list;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userInfo");
        return user;
    }

    public static String getNameById(Integer id, List<ParamObject> paramObjects) {
        String source = null;
        for (ParamObject paramObject : paramObjects) {
            if (paramObject.getId().equals(String.valueOf(id))) {
                source = paramObject.getName();
                break;
            }
        }
        return source;
    }


    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    public static String getSmsField(List<String> list) {
        int length = list.size();
        if (list == null && length > 0) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str).append("||");
            }
            String temp = sb.toString();
            return temp.substring(0, temp.length() - 2);
        }
    }

    public static String dropEmpty(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            String[] temp = str.split(",");
            int i = 0;
            for (String st : temp) {
                if (StringUtils.isNotEmpty(st)) {
                    if (i == 0) {
                        sb.append(st);
                    } else {
                        sb.append(",").append(st);
                    }
                    i++;
                }
            }
        }
        return sb.toString();
    }

}

