package com.midland.web.service.impl;

import com.midland.web.model.SmsConfig;
import com.midland.web.dao.SmsConfigMapper;
import com.midland.web.service.SmsConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SmsConfigServiceImpl implements SmsConfigService {

	private Logger log = LoggerFactory.getLogger(SmsConfigServiceImpl.class);
	@Autowired
	private SmsConfigMapper smsConfigMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertSmsConfig(SmsConfig smsConfig) throws Exception {
		try {
			log.info("insert {}",smsConfig);
			smsConfigMapper.insertSmsConfig(smsConfig);
		} catch(Exception e) {
			log.error("insertSmsConfig异常 {}",smsConfig,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public SmsConfig selectSmsConfigById(Integer id) {
		log.info("selectSmsConfigById  {}",id);
		return smsConfigMapper.selectSmsConfigById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteSmsConfigById(Integer id)throws Exception {
		try {
			log.info("deleteSmsConfigById  {}",id);
			int result = smsConfigMapper.deleteSmsConfigById(id);
			if (result < 1) {
				throw new Exception("deleteSmsConfigById失败");
			}
		} catch(Exception e) {
			log.error("deleteSmsConfigById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateSmsConfigById(SmsConfig smsConfig) throws Exception {
		try {
			log.info("updateSmsConfigById  {}",smsConfig);
			int result = smsConfigMapper.updateSmsConfigById(smsConfig);
			if (result < 1) {
				throw new Exception("updateSmsConfigById失败");
			}
		} catch(Exception e) {
			log.error("updateSmsConfigById  {}",smsConfig,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<SmsConfig> findSmsConfigList(SmsConfig smsConfig) throws Exception {
		try {
			log.info("findSmsConfigList  {}",smsConfig);
			return smsConfigMapper.findSmsConfigList(smsConfig);
		} catch(Exception e) {
			log.error("findSmsConfigList  {}",smsConfig,e);
			throw e;
		}
	}
}
