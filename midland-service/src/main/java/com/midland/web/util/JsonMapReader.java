package com.midland.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

/**
 * Created by jim on 2017/6/14.
 */
public class JsonMapReader
{
    private Logger logger = LoggerFactory.getLogger(JsonMapReader.class);
    private static String PAY_CHANNEL = "jsonMap/midland.json";
    private ObjectMapper mapper = new ObjectMapper();
    private static List<ParamObject> objects = null;
    private static ClassPathResource cpr = null;

    public  void getPayChannelInfo(String pro) {
        if (cpr == null){
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

    public static List<ParamObject> getMap(String note) {
        JsonMapReader jsonMapReader = new JsonMapReader();
        jsonMapReader.getPayChannelInfo(note);
        return objects;
    }

    public static ParamObject getObject(String note, Object id){
        List<ParamObject> re = getMap(note);
        for (ParamObject o:re){
            if (o.getId().equals(id)){
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
        List<ParamObject> result = properties.getMap("quotation_type");
        System.out.println(result);
    }
}
