package com.midland.web.service.impl;

import com.midland.web.model.EntrustLog;
import com.midland.web.dao.EntrustLogMapper;
import com.midland.web.service.EntrustLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EntrustLogServiceImpl implements EntrustLogService {

	private Logger log = LoggerFactory.getLogger(EntrustLogServiceImpl.class);
	@Autowired
	private EntrustLogMapper entrustLogMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertEntrustLog(EntrustLog entrustLog) throws Exception {
		try {
			log.info("insert {}",entrustLog);
			entrustLogMapper.insertEntrustLog(entrustLog);
		} catch(Exception e) {
			log.error("insertEntrustLog异常 {}",entrustLog,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public EntrustLog selectEntrustLogByEntrustLogId(Integer entrustLogId) {
		log.info("selectEntrustLogByEntrustLogId  {}",entrustLogId);
		return entrustLogMapper.selectEntrustLogByEntrustLogId(entrustLogId);
	}
	/**
	 * 查询
	 **/
	@Override
	public List<EntrustLog> selectEntrustLogByEntrustId(Integer entrustId) {
		log.info("selectEntrustLogByEntrustId  {}",entrustId);
		return entrustLogMapper.selectEntrustLogByEntrustId(entrustId);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteEntrustLogByEntrustLogId(Integer entrustLogId)throws Exception {
		try {
			log.info("deleteEntrustLogByEntrustLogId  {}",entrustLogId);
			int result = entrustLogMapper.deleteEntrustLogByEntrustLogId(entrustLogId);
			if (result < 1) {
				throw new Exception("deleteEntrustLogByEntrustLogId失败");
			}
		} catch(Exception e) {
			log.error("deleteEntrustLogByEntrustLogId  {}",entrustLogId,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateEntrustLogByEntrustLogId(EntrustLog entrustLog) throws Exception {
		try {
			log.info("updateEntrustLogByEntrustLogId  {}",entrustLog);
			int result = entrustLogMapper.updateEntrustLogByEntrustLogId(entrustLog);
			if (result < 1) {
				throw new Exception("updateEntrustLogByEntrustLogId失败");
			}
		} catch(Exception e) {
			log.error("updateEntrustLogByEntrustLogId  {}",entrustLog,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<EntrustLog> findEntrustLogList(EntrustLog entrustLog) throws Exception {
		try {
			log.info("findEntrustLogList  {}",entrustLog);
			return entrustLogMapper.findEntrustLogList(entrustLog);
		} catch(Exception e) {
			log.error("findEntrustLogList  {}",entrustLog,e);
			throw e;
		}
	}
}
