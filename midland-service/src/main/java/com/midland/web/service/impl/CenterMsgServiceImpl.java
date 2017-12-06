package com.midland.web.service.impl;

import com.midland.web.model.CenterMsg;
import com.midland.web.dao.CenterMsgMapper;
import com.midland.web.service.CenterMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CenterMsgServiceImpl implements CenterMsgService {

	private Logger log = LoggerFactory.getLogger(CenterMsgServiceImpl.class);
	@Autowired
	private CenterMsgMapper centerMsgMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertCenterMsg(CenterMsg centerMsg) throws Exception {
		try {
			log.info("insert {}",centerMsg);
			centerMsgMapper.insertCenterMsg(centerMsg);
		} catch(Exception e) {
			log.error("insertCenterMsg异常 {}",centerMsg,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public CenterMsg selectCenterMsgById(Integer id) {
		log.info("selectCenterMsgById  {}",id);
		return centerMsgMapper.selectCenterMsgById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteCenterMsgById(Integer id)throws Exception {
		try {
			log.info("deleteCenterMsgById  {}",id);
			int result = centerMsgMapper.deleteCenterMsgById(id);
			if (result < 1) {
				throw new Exception("deleteCenterMsgById失败");
			}
		} catch(Exception e) {
			log.error("deleteCenterMsgById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateCenterMsgById(CenterMsg centerMsg) throws Exception {
		try {
			log.info("updateCenterMsgById  {}",centerMsg);
			int result = centerMsgMapper.updateCenterMsgById(centerMsg);
			if (result < 1) {
				throw new Exception("updateCenterMsgById失败");
			}
		} catch(Exception e) {
			log.error("updateCenterMsgById  {}",centerMsg,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<CenterMsg> findCenterMsgList(CenterMsg centerMsg) throws Exception {
		try {
			log.info("findCenterMsgList  {}",centerMsg);
			return centerMsgMapper.findCenterMsgList(centerMsg);
		} catch(Exception e) {
			log.error("findCenterMsgList  {}",centerMsg,e);
			throw e;
		}
	}
}
