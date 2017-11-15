package com.midland.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

//import common.context.SystemContext;

public class SmsUtil {
	private static final String URL =AppSetting.getAppSetting("sms_url");// IP和端口号需要更改为正式环境下的。
	private static final String USERNAME = AppSetting.getAppSetting("sms_username");
	private static final String PWD = AppSetting.getAppSetting("sms_password");

	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	public static boolean send(String mobile, String content) {

		Map<String, String> param = new HashMap<String, String>();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		param.put("username", USERNAME);
		param.put("password", PWD);
		param.put("from", "001");
		param.put("to", mobile);
		param.put("expandPrefix", "102");
		param.put("content", content);
		//param.put("presendTime", df.format(Calendar.getInstance().getTime()));
		param.put("isVoice", "0|0|0|0");

		String result = HttpClientUtil.sendPostRequest(URL, param,"gbk","gbk");
		logger.error("发送短信结果：{}", result);
		if (result.startsWith("OK")) {
			return true;
		} else {
			logger.error("发送短信失败：{}", result);
			return false;
		}
	}
	
	/**
	 * 随机生成6位随机验证码
	 * @return
	 */
	public static String createRandomVCode(){
        //验证码
        String vCode="";
        for (int i = 0; i < 6; i++) {
            vCode = vCode + (int)(Math.random() * 9);
        }
        return vCode;
    }
 
}
