package com.midland.web.service;

import com.midland.web.model.SmsConfig;
import java.util.List;
public interface SmsConfigService {

	/**
	 * 主键查询
	 **/
	SmsConfig selectSmsConfigById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteSmsConfigById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateSmsConfigById(SmsConfig smsConfig) throws Exception;

	/**
	 * 插入
	 **/
	void insertSmsConfig(SmsConfig smsConfig) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<SmsConfig> findSmsConfigList(SmsConfig smsConfig) throws Exception;

}
