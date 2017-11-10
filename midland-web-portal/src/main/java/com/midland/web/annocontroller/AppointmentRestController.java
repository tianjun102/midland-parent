package com.midland.web.annocontroller;

import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import com.midland.base.BaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/appointment/")
public class AppointmentRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(AppointmentRestController.class);
	@Autowired
	private AppointmentService appointmentServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addAppointment(@RequestBody Appointment obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addAppointment {}",obj);
			appointmentServiceImpl.insertAppointment(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addAppointment异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getAppointmentById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getAppointmentById  {}",id);
			Appointment appointment = appointmentServiceImpl.selectAppointmentById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(appointment);
		} catch(Exception e) {
			log.error("getAppointment异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateAppointmentById(@RequestBody Appointment obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateAppointmentById  {}",obj);
			appointmentServiceImpl.updateAppointmentById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findAppointmentList(@RequestBody Appointment  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findAppointmentList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Appointment> list = (Page<Appointment>)appointmentServiceImpl.findAppointmentList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findAppointmentList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
