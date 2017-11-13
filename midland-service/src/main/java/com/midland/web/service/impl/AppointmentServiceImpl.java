package com.midland.web.service.impl;

import com.midland.web.model.Appointment;
import com.midland.web.dao.AppointmentMapper;
import com.midland.web.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {

	private Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);
	@Autowired
	private AppointmentMapper appointmentMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertAppointment(Appointment appointment) throws Exception {
		try {
			log.debug("insert {}",appointment);
			appointmentMapper.insertAppointment(appointment);
		} catch(Exception e) {
			log.error("insertAppointment异常 {}",appointment,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Appointment selectAppointmentById(Integer id) {
		log.debug("selectAppointmentById  {}",id);
		return appointmentMapper.selectAppointmentById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteAppointmentById(Integer id)throws Exception {
		try {
			log.debug("deleteAppointmentById  {}",id);
			int result = appointmentMapper.deleteAppointmentById(id);
			if (result < 1) {
				throw new Exception("deleteAppointmentById失败");
			}
		} catch(Exception e) {
			log.error("deleteAppointmentById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateAppointmentById(Appointment appointment) throws Exception {
		try {
			log.debug("updateAppointmentById  {}",appointment);
			int result = appointmentMapper.updateAppointmentById(appointment);
			if (result < 1) {
				throw new Exception("updateAppointmentById失败");
			}
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",appointment,e);
			throw e;
		}
	}
	/**
	 * 更新,慎用
	 **/
	@Override
	public void updateAppointmentByWebUserId(Appointment appointment) throws Exception {
		try {
			log.debug("updateAppointmentByWebUserId  {}",appointment);
			int result = appointmentMapper.updateAppointmentByWebUserId(appointment);
			if (result < 1) {
				throw new Exception("updateAppointmentByWebUserId失败");
			}
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",appointment,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Appointment> findAppointmentList(Appointment appointment) throws Exception {
		try {
			log.debug("findAppointmentList  {}",appointment);
			return appointmentMapper.findAppointmentList(appointment);
		} catch(Exception e) {
			log.error("findAppointmentList  {}",appointment,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<Appointment> appointmentList) throws Exception {
		try {
			log.debug("updateAppointmentById  {}",appointmentList);
			int result = appointmentMapper.batchUpdate(appointmentList);
			if (result < 1) {
				throw new Exception("updateAppointLogById失败");
			}
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",appointmentList,e);
			throw e;
		}
	}
}
