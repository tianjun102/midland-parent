package com.midland.web.SmsSender;

import com.midland.core.util.HttpUtils;
import com.midland.core.util.XMLUtil;
import org.jdom.JDOMException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
public class SmsClient {
	
	
	public void SmsClient(SmsModel smsModel) {
		this.phones=smsModel.getPhones();
		this.cont = smsModel.getCont();
		this.sendType=smsModel.getSendType();
		this.ucCode=smsModel.getUcCode();
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
	 * 	1、通过用户操作 2、非用户操作
	 */
	private Integer sendType;
	/**
	 * 	用户名编号
	 */
	private String userId;
	/**
	 * 接口通信唯一标识，64位16进制数，每次登录重新生成,如果非用户操作，可以为空。
	 */
	private String webKey;
	/**
	 * 	会话标识，非用户操作时，必填
	 */
	private String sessionId;
	/**
	 * 手机号码用半角‘,’分开，最多5000个(尽量以此方式提交)，必选
	 */
	private String phones;
	/**
	 * 	短信内容（base64编码）
	 */
	private String cont;
	/**
	 * 	产品的类别
	 */
	private Integer type;
	/**
	 * 用户指定uc号码发送短信，允许为空。
	 */
	private String ucCode;
	
	
	private String charset ="utf-8";
	
	
	
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
			sb.append("&bizAppId=").append(bizAppId);
		}
		if (customerId != null){
			sb.append("&customerId=").append(customerId);
			
		}
		if (sendType != null){
			sb.append("&sendType=").append(sendType);
			
		}
		if (userId != null){
			sb.append("&userId=").append(userId);
			
		}
		if (webKey != null){
			sb.append("&webKey=").append(webKey);
		}
		
		if (phones != null){
			sb.append("&phones=").append(phones);
			
		}
		if (sessionId != null){
			sb.append("&sessionId=").append(sessionId);
			
		}
		if (cont != null){
			sb.append("&cont=").append(cont);
			
		}
		if (type != null){
			sb.append("&type=").append(type);
			
		}
		if (ucCode != null){
			sb.append("&ucCode=").append(ucCode);
			
		}
		if (charset != null){
			sb.append("&charset=").append(charset);
			
		}
		
		
		return sb.toString();
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
	
	private Integer getSendType() {
		return sendType;
	}
	
	private void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getWebKey() {
		return webKey;
	}
	
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	private String getPhones() {
		return phones;
	}
	
	private void setPhones(String phones) {
		this.phones = phones;
	}
	
	private String getCont() {
		return cont;
	}
	
	private void setCont(String cont) {
		this.cont = cont;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	private String getUcCode() {
		return ucCode;
	}
	
	private void setUcCode(String ucCode) {
		this.ucCode = ucCode;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCharset() {
		return charset;
	}
	
	public void setCharset(String charset) {
		this.charset = charset;
	}
}
