package com.midland.web.service.impl;

import com.midland.web.model.HeadMsg;
import com.midland.web.dao.HeadMsgMapper;
import com.midland.web.service.HeadMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class HeadMsgServiceImpl implements HeadMsgService {

	private Logger log = LoggerFactory.getLogger(HeadMsgServiceImpl.class);
	@Autowired
	private HeadMsgMapper headMsgMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertHeadMsg(HeadMsg headMsg) throws Exception {
		try {
			log.info("insert {}",headMsg);
			headMsgMapper.insertHeadMsg(headMsg);
		} catch(Exception e) {
			log.error("insertHeadMsg异常 {}",headMsg,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public HeadMsg selectHeadMsgById(Integer id) {
		log.info("selectHeadMsgById  {}",id);
		return headMsgMapper.selectHeadMsgById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteHeadMsgById(Integer id)throws Exception {
		try {
			log.info("deleteHeadMsgById  {}",id);
			int result = headMsgMapper.deleteHeadMsgById(id);
			if (result < 1) {
				throw new Exception("deleteHeadMsgById失败");
			}
		} catch(Exception e) {
			log.error("deleteHeadMsgById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateHeadMsgById(HeadMsg headMsg) throws Exception {
		try {
			log.info("updateHeadMsgById  {}",headMsg);
			int result = headMsgMapper.updateHeadMsgById(headMsg);
			if (result < 1) {
				throw new Exception("updateHeadMsgById失败");
			}
		} catch(Exception e) {
			log.error("updateHeadMsgById  {}",headMsg,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<HeadMsg> findHeadMsgList(HeadMsg headMsg) throws Exception {
		try {
			log.info("findHeadMsgList  {}",headMsg);
			return headMsgMapper.findHeadMsgList(headMsg);
		} catch(Exception e) {
			log.error("findHeadMsgList  {}",headMsg,e);
			throw e;
		}
	}
}
