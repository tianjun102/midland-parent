package com.midland.web.service.impl;

import com.midland.web.model.Privacy;
import com.midland.web.dao.PrivacyMapper;
import com.midland.web.service.PrivacyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PrivacyServiceImpl implements PrivacyService {

	private Logger log = LoggerFactory.getLogger(PrivacyServiceImpl.class);
	@Autowired
	private PrivacyMapper privacyMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertPrivacy(Privacy privacy) throws Exception {
		try {
			log.info("insert {}",privacy);
			privacyMapper.insertPrivacy(privacy);
		} catch(Exception e) {
			log.error("insertPrivacy异常 {}",privacy,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Privacy selectPrivacyById(Integer id) {
		log.info("selectPrivacyById  {}",id);
		return privacyMapper.selectPrivacyById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deletePrivacyById(Integer id)throws Exception {
		try {
			log.info("deletePrivacyById  {}",id);
			int result = privacyMapper.deletePrivacyById(id);
			if (result < 1) {
				throw new Exception("deletePrivacyById失败");
			}
		} catch(Exception e) {
			log.error("deletePrivacyById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updatePrivacyById(Privacy privacy) throws Exception {
		try {
			log.info("updatePrivacyById  {}",privacy);
			int result = privacyMapper.updatePrivacyById(privacy);
			if (result < 1) {
				throw new Exception("updatePrivacyById失败");
			}
		} catch(Exception e) {
			log.error("updatePrivacyById  {}",privacy,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Privacy> findPrivacyList(Privacy privacy) throws Exception {
		try {
			log.info("findPrivacyList  {}",privacy);
			return privacyMapper.findPrivacyList(privacy);
		} catch(Exception e) {
			log.error("findPrivacyList  {}",privacy,e);
			throw e;
		}
	}
}
