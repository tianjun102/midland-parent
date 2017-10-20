package com.midland.web.service.impl;

import com.midland.web.model.AppointLog;
import com.midland.web.dao.AppointLogMapper;
import com.midland.web.service.AppointLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AppointLogServiceImpl implements AppointLogService {

	private Logger log = LoggerFactory.getLogger(AppointLogServiceImpl.class);
	@Autowired
	private AppointLogMapper appointLogMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertAppointLog(AppointLog appointLog) throws Exception {
		try {
			log.debug("insert {}",appointLog);
			appointLogMapper.insertAppointLog(appointLog);
		} catch(Exception e) {
			log.error("insertAppointLog异常 {}",appointLog,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public AppointLog selectAppointLogByAppointLogId(Integer appointLogId) {
		log.debug("selectAppointLogByAppointLogId  {}",appointLogId);
		return appointLogMapper.selectAppointLogByAppointLogId(appointLogId);
	}
	/**
	 * 查询
	 **/
	@Override
	public List<AppointLog> selectAppointLogByAppointId(Integer appointId) {
		log.debug("selectAppointLogByAppointId  {}",appointId);
		return appointLogMapper.selectAppointLogByAppointId(appointId);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteAppointLogByAppointLogId(Integer appointLogId)throws Exception {
		try {
			log.debug("deleteAppointLogByAppointLogId  {}",appointLogId);
			int result = appointLogMapper.deleteAppointLogByAppointLogId(appointLogId);
			if (result < 1) {
				throw new Exception("deleteAppointLogByAppointLogId失败");
			}
		} catch(Exception e) {
			log.error("deleteAppointLogByAppointLogId  {}",appointLogId,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateAppointLogByAppointLogId(AppointLog appointLog) throws Exception {
		try {
			log.debug("updateAppointLogByAppointLogId  {}",appointLog);
			int result = appointLogMapper.updateAppointLogByAppointLogId(appointLog);
			if (result < 1) {
				throw new Exception("updateAppointLogByAppointLogId失败");
			}
		} catch(Exception e) {
			log.error("updateAppointLogByAppointLogId  {}",appointLog,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<AppointLog> findAppointLogList(AppointLog appointLog) throws Exception {
		try {
			log.debug("findAppointLogList  {}",appointLog);
			return appointLogMapper.findAppointLogList(appointLog);
		} catch(Exception e) {
			log.error("findAppointLogList  {}",appointLog,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<AppointLog> appointLogList) throws Exception {
		try {
			log.debug("updateAppointLogById  {}",appointLogList);
			int result = appointLogMapper.batchUpdate(appointLogList);
			if (result < 1) {
				throw new Exception("updateAppointLogById失败");
			}
		} catch(Exception e) {
			log.error("updateAnswerById  {}",appointLogList,e);
			throw e;
		}
	}
}
