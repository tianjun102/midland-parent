package com.midland.web.commons;

import com.github.pagehelper.Paginator;
import com.midland.web.model.Meta;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author yek
 * @Title: Result.java
 * @Package com.huixin.wks.web.commons
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年3月22日 下午3:19:41
 */
@Component
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum resultMsg {
        SUCCESS, FAIL
    }

    private Integer number;
    private Long len;
    private String token;
    private String code_url;

    /**
     * 请求接口返回状态值
     */
    private Integer code;
    /**
     * 接口请求结果信息
     */
    private String msg;
    private Paginator paginator;
    private T model;
    private List<T> list;
    private Map<Object, Object> map;

    private List<Map<T, T>> listMap;
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public List<Map<T, T>> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map<T, T>> listMap) {
        this.listMap = listMap;
    }


    @Override
    public String toString() {
        return "Result{" +
                "number=" + number +
                ", len=" + len +
                ", token='" + token + '\'' +
                ", code_url='" + code_url + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", paginator=" + paginator +
                ", model=" + model +
                ", list=" + list +
                ", map=" + map +
                ", listMap=" + listMap +
                '}';
    }
}
