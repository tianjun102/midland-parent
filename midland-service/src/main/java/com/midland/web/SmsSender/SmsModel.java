package com.midland.web.SmsSender;

import com.midland.core.util.HttpUtils;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
public class SmsModel {
	
	/**
	 * 	1、通过用户操作 2、非用户操作
	 */
	private Integer sendType;
	
	/**
	 * 手机号码用半角‘,’分开，最多5000个(尽量以此方式提交)，必选
	 */
	private String phones;
	/**
	 * 	短信内容（base64编码）
	 */
	private String cont;
	
	/**
	 * 用户指定uc号码发送短信，允许为空。
	 */
	private String ucCode;
	/**
	 * 用户指定模板id
	 */
	private String tpId;
	/**
	 * 用户指定模板类容填充字段多个使用||隔开
	 */
	private String fields;
	
	public Integer getSendType() {
		return sendType;
	}
	
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	
	public String getPhones() {
		return phones;
	}
	
	public void setPhones(String phones) {
		this.phones = phones;
	}
	
	public String getCont() {
		return cont;
	}
	
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public String getUcCode() {
		return ucCode;
	}
	
	public void setUcCode(String ucCode) {
		this.ucCode = ucCode;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
}
