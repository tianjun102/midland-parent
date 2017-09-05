package com.midland.web.service;

import com.midland.web.model.AppointLog;
import java.util.List;
public interface AppointLogService {

	/**
	 * 主键查询
	 **/
	AppointLog selectAppointLogByAppointLogId(Integer appointLogId);
	
	List<AppointLog> selectAppointLogByAppointId(Integer appointId);
	
	/**
	 * 主键删除
	 **/
	void deleteAppointLogByAppointLogId(Integer appointLogId) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateAppointLogByAppointLogId(AppointLog appointLog) throws Exception;

	/**
	 * 插入
	 **/
	void insertAppointLog(AppointLog appointLog) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<AppointLog> findAppointLogList(AppointLog appointLog) throws Exception;

}
