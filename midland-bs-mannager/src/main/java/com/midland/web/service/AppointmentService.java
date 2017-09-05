package com.midland.web.service;

import com.midland.web.model.Appointment;
import java.util.List;
public interface AppointmentService {

	/**
	 * 主键查询
	 **/
	Appointment selectAppointmentById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteAppointmentById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateAppointmentById(Appointment appointment) throws Exception;

	/**
	 * 插入
	 **/
	void insertAppointment(Appointment appointment) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Appointment> findAppointmentList(Appointment appointment) throws Exception;

}
