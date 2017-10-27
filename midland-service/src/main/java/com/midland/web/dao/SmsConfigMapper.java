package com.midland.web.dao;

import com.midland.web.model.SmsConfig;
import java.util.List;

public interface SmsConfigMapper {

	SmsConfig selectSmsConfigById(Integer smsConfig);

	int deleteSmsConfigById(Integer smsConfig);

	int updateSmsConfigById(SmsConfig smsConfig);

	int insertSmsConfig(SmsConfig smsConfig);

	List<SmsConfig> findSmsConfigList(SmsConfig smsConfig);

}
