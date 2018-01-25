package com.midland.web.api.SmsSender;

import com.midland.web.util.MidlandHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
public class SmsModel {
    public SmsModel() {
    }

    public SmsModel(String phones, String tpId, List<String> fieldsList) {
        this.phones = phones;
        this.tpId = tpId;
        this.fieldsList = fieldsList;
    }

    /**
     * 手机号码用半角‘,’分开，最多5000个(尽量以此方式提交)，必选
     */
    private String phones;

    /**
     * 用户指定模板id
     */
    private String tpId;


    /**
     * 用户指定模板类容填充字段多个使用||隔开
     */
    private List<String> fieldsList;

    private ArrayList<String> parems;

    public void setFieldsList(List<String> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public List<String> getFieldsList() {
        return fieldsList;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    /**
     * 用户指定模板类容填充字段多个使用||隔开
     */
    public String getFields() {

        return MidlandHelper.getSmsField(fieldsList);
    }

    public ArrayList<String> getParems() {
        return parems;
    }

    public void setParems(ArrayList<String> parems) {
        this.parems = parems;
    }


}
