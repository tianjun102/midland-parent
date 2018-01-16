package com.midland.web.annocontroller;

import com.midland.web.model.AppointLog;
import com.midland.web.service.AppointLogService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
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
@RequestMapping("/appointLog/")
public class AppointLogRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(AppointLogRestController.class);
	@Autowired
	private AppointLogService appointLogServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addAppointLog(@RequestBody AppointLog obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addAppointLog {}",obj);
			appointLogServiceImpl.insertAppointLog(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addAppointLog异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getAppointLogByAppointLogId(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer appointLogId =(Integer)map.get("appointLogId");
			log.info("getAppointLogByAppointLogId  {}",appointLogId);
			AppointLog appointLog = appointLogServiceImpl.selectAppointLogByAppointLogId(appointLogId);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(appointLog);
		} catch(Exception e) {
			log.error("getAppointLog异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateAppointLogByAppointLogId(@RequestBody AppointLog obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateAppointLogByAppointLogId  {}",obj);
			appointLogServiceImpl.updateAppointLogByAppointLogId(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateAppointLogByAppointLogId  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findAppointLogList(@RequestBody AppointLog  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findAppointLogList  {}",obj);
			MidlandHelper.doPage(request);
			Page<AppointLog> list = (Page<AppointLog>)appointLogServiceImpl.findAppointLogList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findAppointLogList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
