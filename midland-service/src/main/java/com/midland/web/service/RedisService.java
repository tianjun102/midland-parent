package com.midland.web.service;

/**
 * Created by 'ms.x' on 2017/9/20.
 */
public interface RedisService {
	void setAnswerAuditFlag(int value);
	
	Integer getAnswerAuditFlag();

	Integer getValue(String key);

	void setValue(String key, Integer value);
}
