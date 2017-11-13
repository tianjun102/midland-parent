package com.midland.web.annocontroller;

import com.midland.web.model.EntrustLog;
import com.midland.web.service.EntrustLogService;
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
@RequestMapping("/entrustLog/")
public class EntrustLogRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(EntrustLogRestController.class);
	@Autowired
	private EntrustLogService entrustLogServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addEntrustLog(@RequestBody EntrustLog obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addEntrustLog {}",obj);
			entrustLogServiceImpl.insertEntrustLog(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addEntrustLog异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getEntrustLogByEntrustLogId(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer entrustLogId =(Integer)map.get("entrustLogId");
			log.info("getEntrustLogByEntrustLogId  {}",entrustLogId);
			EntrustLog entrustLog = entrustLogServiceImpl.selectEntrustLogByEntrustLogId(entrustLogId);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(entrustLog);
		} catch(Exception e) {
			log.error("getEntrustLog异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateEntrustLogByEntrustLogId(@RequestBody EntrustLog obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateEntrustLogByEntrustLogId  {}",obj);
			entrustLogServiceImpl.updateEntrustLogByEntrustLogId(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateEntrustLogByEntrustLogId  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findEntrustLogList(@RequestBody EntrustLog  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findEntrustLogList  {}",obj);
			MidlandHelper.doPage(request);
			Page<EntrustLog> list = (Page<EntrustLog>)entrustLogServiceImpl.findEntrustLogList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findEntrustLogList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
