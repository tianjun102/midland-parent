package com.midland.web.enums;

public final class PayEnums {
	/**
	 * @author TianJun
	 * 支付接口常用参数（微信扫码支付）
	 */
	public static final String APP_ID = "wx543354b696aa48bb"; //微信开放平台应用id;
	public static final String APP_SECRET = "";
	public static final String MCH_ID = "1330220601";//商户id
	public static final String API_KEY = "5K8264ILTKCH16CQ2502SI8ZNMTM6WKS";//生成签名值的
	public static final String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//模式二微信统一支付接口
	public static final String CREATE_IP = "";//本机ip
	public static final String NOTIFY_URL = "http://192.168.26.202:8081/webmvc/rest/user/checkLink";//回调地址
	

}
