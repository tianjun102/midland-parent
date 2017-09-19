package com.midland.web.SmsSender;

import com.midland.core.util.HttpUtils;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
public class SmsModel {
	

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
	private String fields;
	

	public String getPhones() {
		return phones;
	}
	
	public void setPhones(String phones) {
		this.phones = phones;
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
