package com.bluemobi.core.util;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 服务器响应的状态码
 *
 * @author zhangrq
 *
 * @since 2016年07月10日下午2:06:09
 */
@SuppressWarnings("serial")
public class RestResponse implements java.io.Serializable {

    public RestResponse(){

    }
    /**
     * 　响应成功的code码
     */
    public final static String REST_RESPONSE_SUCCESS_CODE = "200";

    /**
     * 响应失败的code码
     */
    public final static String REST_RESPONSE_FIAL_CODE = "400";

    /**
     * 响应码
     */
    @JsonProperty("code")
    private String code;

    /**
     * 响应消息
     */
    @JsonProperty("message")
    private String msg;

    /**
     * 响应内容
     */
    @JsonProperty("data")
    private Object result;

    private RestResponse(String code) {
        this.code = code;
    }

    private RestResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean hasSuccess() {
        return REST_RESPONSE_SUCCESS_CODE.equals(this.code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @JsonIgnoreType
    public static class RestResponseBuilder {

        private final RestResponse response;

        private RestResponseBuilder(String code) {
            response = new RestResponse(code);
        }

        private RestResponseBuilder(String code, String message) {
            response = new RestResponse(code);
            setMessage(message);
            setResult("");
        }

        public static RestResponseBuilder createSuccessBuilder() {
            return createBuilder(REST_RESPONSE_SUCCESS_CODE);
        }

        public static RestResponseBuilder createSuccessBuilder(String message) {
            return createBuilder(REST_RESPONSE_SUCCESS_CODE, message);
        }

        public static RestResponseBuilder createFailBuilder() {
            return createBuilder(REST_RESPONSE_FIAL_CODE);
        }

        public static RestResponseBuilder createFailBuilder(String message) {
            return createBuilder(REST_RESPONSE_FIAL_CODE, message);
        }

        public static RestResponseBuilder createBuilder(String code) {
            return createBuilder(code, "");
        }

        public static RestResponseBuilder createBuilder(String code, String message) {
            return new RestResponseBuilder(code, message);
        }

        public RestResponseBuilder setMessage(String message) {
            if (null == message) {
                response.setMsg("");
            } else {
                response.setMsg(message);
            }
            return this;
        }

        public <T> RestResponseBuilder setResult(List<T> result) {
            if (null != result) {
                response.setResult(result);
            }
            return this;
        }

        public <T> RestResponseBuilder setResult(T result) {
            response.setResult(result);
            return this;
        }

        public <T> RestResponseBuilder setResult(Collection<T> result) {
            response.setResult(result);
            return this;
        }

        public <KEY, VALUE> RestResponseBuilder setResult(Map<KEY, VALUE> result) {
            response.setResult(result);
            return this;
        }

        public RestResponse buidler() {
            return response;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestResponse{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
