package com.midland.web.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 2016/4/19.
 */
public class JsonUtil {
    private static volatile ObjectMapper objectMapper;

    private static synchronized ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (JsonUtil.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
                        @Override
                        public void serialize(
                                Object value,
                                JsonGenerator jg,
                                SerializerProvider sp) throws IOException, JsonProcessingException {
                            jg.writeString("");
                        }
                    });
                }
            }
        }
        return objectMapper;
    }

    public static <T> List<T> getListValues(String jsonContent, Class<T> valueType) throws IOException {
        if (StringUtils.isEmpty(jsonContent) || "{}".equals(jsonContent)) {
            return new ArrayList<T>(0);
        }
        return getObjectMapper().readValue(jsonContent, getObjectMapper().getTypeFactory().constructCollectionType(List.class, valueType));
    }

    public static <T> T getObjectValues(String jsonContent, Class<T> valueType) throws IOException {
        if (StringUtils.isEmpty(jsonContent) || "{}".equals(jsonContent)) {
            return null;
        }
        return getObjectMapper().readValue(jsonContent, valueType);
    }

    public static <T> List<T> getListValues(InputStream jsonContent, Class<T> valueType) throws IOException {
        if (StringUtils.isEmpty(jsonContent) || "{}".equals(jsonContent)) {
            return new ArrayList<T>(0);
        }
        return getObjectMapper().readValue(jsonContent, getObjectMapper().getTypeFactory().constructCollectionType(List.class, valueType));
    }

    public static String writeValueAsString(Object object) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }


    public static String writeObjectValueAsString(Object object) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(object);

        } catch (Exception e) {

        }
        return "";
    }


    public static String getNodeValue(String json, String filedName) throws IOException {
        JsonNode jsonNode = getObjectMapper().readTree(json);
        return jsonNode.get(filedName) == null ? "" : jsonNode.get(filedName).toString();
    }

    public static String getNodeValue(InputStream jsonStream, String filedName) throws IOException {
        JsonNode jsonNode = getObjectMapper().readTree(jsonStream);
        return jsonNode.get(filedName) == null ? "" : jsonNode.get(filedName).toString();
    }

    public static String readValue(String json) throws IOException {
        return getObjectMapper().readValue(json, String.class);
    }

    public static Map readValueForMap(String json) throws IOException {
        return getObjectMapper().readValue(json, Map.class);
    }

    public static Map readValueForMap(InputStream json) throws IOException {
        return getObjectMapper().readValue(json, Map.class);
    }


    /**
     * add by cliff
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static Object filterObjectFields(Object object) throws IOException {
        if (object != null) {
            Field[] f = object.getClass().getDeclaredFields();
            for (int i = 0; i < f.length; i++) {
                Field field = f[i];
                //
                try {

                    if (field.getGenericType().toString().equals(
                            "class java.lang.String")) { //
                        //
                        String val = (String) field.get(field.getName());//
                        if (val == null) {

                            //  setMethod.invoke(object, "");
                            field.set(field.getName(), "");
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
        return object;
    }


    //
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

}
