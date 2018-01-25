package com.midland.web.commons;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.List;
import java.util.Map;

/**
 * @author yek
 * @Title: FastJsonUtils.java
 * @Package com.huixin.wks.web.commons
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年3月24日 下午3:09:45
 */
public class FastJsonUtils {
    /**
     * 把JSON数据转换成普通字符串列表
     *
     * @param jsonData JSON数据
     * @return
     * @throws Exception
     * @author myclover
     */
    public static List<String> getStringList(String jsonData) throws Exception {
        return JSON.parseArray(jsonData, String.class);
    }

    /**
     * 把JSON数据转换成指定的java对象
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return
     * @throws Exception
     * @author myclover
     */
    public static <T> T getSingleBean(String jsonData, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * 把JSON数据转换成指定的java对象列表
     *
     * @param jsonData JSON数据
     * @param clazz    指定的java对象
     * @return
     * @throws Exception
     * @author myclover
     */
    public static <T> List<T> getBeanList(String jsonData, Class<T> clazz) throws Exception {
        return JSON.parseArray(jsonData, clazz);
    }

    /**
     * 把JSON数据转换成较为复杂的java对象列表
     *
     * @param jsonData JSON数据
     * @return
     * @throws Exception
     * @author myclover
     */
    public static List<Map<String, Object>> getBeanMapList(String jsonData) throws Exception {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
     *
     * @param result
     * @return
     */
    public static String dealResponseResult(String result) {
        result = JSONObject.toJSONString(result, SerializerFeature.WriteClassName, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteSlashAsSpecial, SerializerFeature.WriteTabAsSpecial);
        return result;
    }

    public static String toJSONStr(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();
        String s;
        JSONSerializer serializer = new JSONSerializer(out);
        SerializerFeature arr$[] = features;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            SerializerFeature feature = arr$[i$];
            serializer.config(feature, true);
        }
        serializer.getValueFilters().add(new ValueFilter() {
            public Object process(Object obj, String s, Object value) {
                if (null != value) {
                    if (value instanceof java.util.Date) {
                        return String.format("%1$tF %1tT", value);
                    }
                    return value;
                } else {
                    return "";
                }
            }
        });
        serializer.write(object);
        s = out.toString();
        out.close();
        return s;
    }
}
