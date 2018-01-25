package com.midland.web.dao;

import com.midland.web.model.SmsConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SmsConfigMapper {

    SmsConfig selectSmsConfigById(Integer smsConfig);

    int deleteSmsConfigById(Integer smsConfig);

    int updateSmsConfigById(SmsConfig smsConfig);

    int insertSmsConfig(SmsConfig smsConfig);

    List<SmsConfig> findSmsConfigList(SmsConfig smsConfig);

}
