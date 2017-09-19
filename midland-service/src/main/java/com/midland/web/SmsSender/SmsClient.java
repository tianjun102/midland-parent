package com.midland.web.SmsSender;

import com.midland.core.util.HttpUtils;
import com.midland.core.util.XMLUtil;
import org.jdom.JDOMException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
@SuppressWarnings("all")
public class SmsClient {
	
	
	public void SmsClient(SmsModel smsModel) {
		this.phones=smsModel.getPhones();
		this.fields=smsModel.getFields();
		this.tpId=smsModel.getTpId();
	}
	private String url;
	/**
	 * 应用id
	 */
	private String bizAppId;
	/**
	 * 客户编号
	 */
	private String customerId;
	/**
	 * 	用户名编号
	 */
	private String userId;
	/**
	 * 手机号码用半角‘,’分开，最多5000个(尽量以此方式提交)，必选
	 */
	private String phones;

	private String charset ="utf-8";

	private String password;

	private String fields;

	private String tpId;
	
	
	
	private String postSend(String strUrl,String param){
		
		String result = null;
		try {
			result= HttpUtils.post(strUrl,param,this.charset);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public SmsResult execute(SmsModel smsModel) throws JDOMException, IOException {
		String str = _execute(smsModel);
		Map map = XMLUtil.doXMLParse(str);
		String resultCode = (String)map.get("resultCode");
		String batchId = (String)map.get("batchId");
		String desc = (String)map.get("desc");
		return new SmsResult(resultCode,batchId,desc);
	}
	public String _execute(SmsModel smsModel){
		SmsClient(smsModel);
		return postSend(this.url, toString());
	}

	public String toString(){
		StringBuffer sb = new StringBuffer("temp=");
		if (bizAppId != null){
			sb.append("&appId=").append(bizAppId);
		}
		if (customerId != null){
			sb.append("&customerId=").append(customerId);

		}

		if (userId != null){
			sb.append("&userId=").append(userId);

		}


		if (phones != null){
			sb.append("&phones=").append(phones);

		}

		if (charset != null){
			sb.append("&charset=").append(charset);

		}
		if (password != null){
			sb.append("&password=").append(password);

		}
		if (password != null){
			sb.append("&password=").append(password);

		}
		if (tpId != null){
			sb.append("&tpId=").append(tpId);

		}
		if (fields != null){
			sb.append("&fields=").append(fields);

		}


		return sb.toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBizAppId() {
		return bizAppId;
	}

	public void setBizAppId(String bizAppId) {
		this.bizAppId = bizAppId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}
}
