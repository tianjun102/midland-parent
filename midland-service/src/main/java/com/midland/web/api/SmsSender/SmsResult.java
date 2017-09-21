package com.midland.web.api.SmsSender;

/**
 * Created by 'ms.x' on 2017/8/31.
 */
public class SmsResult {
	/**
	 * 接口状态码
	 */
	private String resultCode;
	/**
	 * 成功时返回批次号
	 */
	private String batchId;
	/**
	 * 	出现禁用语时返回
	 */
	private String desc;
	
	public SmsResult(String resultCode, String batchId, String desc) {
		this.resultCode = resultCode;
		this.batchId = batchId;
		this.desc = desc;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("SmsResult{");
		sb.append("resultCode='").append(resultCode).append('\'');
		sb.append(", batchId='").append(batchId).append('\'');
		sb.append(", desc='").append(desc).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
