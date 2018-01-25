package com.midland.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * Created by jim on 2017/6/14.
 */
public class JsonMapReader {
    private Logger logger = LoggerFactory.getLogger(JsonMapReader.class);
    private static String PAY_CHANNEL = "jsonMap/midland.json";
    private static List<ParamObject> objects = null;
    private static ClassPathResource cpr = null;

    private static String SENSITIVE = "jsonMap/sensitive.json";
    private static ClassPathResource cpr1 = null;
    private static String objects1 = null;

    public void getInfo(String pro) {
        if (cpr == null) {
            cpr = new ClassPathResource(PAY_CHANNEL);
        }
        if (cpr != null && cpr.exists()) {
            try {
                String packDeliveries = JsonUtil.getNodeValue(cpr.getInputStream(), pro);
                objects = JsonUtil.getListValues(packDeliveries, ParamObject.class);
            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }

    public void getSensitiveInfo(String pro) {
        if (cpr1 == null) {
            cpr1 = new ClassPathResource(SENSITIVE);
        }
        if (cpr1 != null && cpr1.exists()) {
            try {
                String object = JsonUtil.getNodeValue(cpr1.getInputStream(), pro);
                objects1 = object.substring(1, object.length() - 1);
            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }

    public static String getSensitive(String note) {
        JsonMapReader jsonMapReader = new JsonMapReader();
        jsonMapReader.getSensitiveInfo(note);
        return objects1;
    }

    public static List<ParamObject> getMap(String note) {
        JsonMapReader jsonMapReader = new JsonMapReader();
        jsonMapReader.getInfo(note);
        return objects;
    }


    public static ParamObject getObject(String note, Object id) {
        List<ParamObject> re = getMap(note);
        for (ParamObject o : re) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return new ParamObject();
    }

    public void setMap(List<ParamObject> objects) {
        this.objects = objects;
    }


    public static void main(String[] args) {
        JsonMapReader properties = new JsonMapReader();

        System.out.println(getMap("sensitive"));
    }
}
