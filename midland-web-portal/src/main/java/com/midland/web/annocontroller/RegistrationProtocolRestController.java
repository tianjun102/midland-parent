package com.midland.web.annocontroller;

import com.midland.web.model.RegistrationProtocol;
import com.midland.web.service.RegistrationProtocolService;
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
@RequestMapping("/registrationProtocol/")
public class RegistrationProtocolRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RegistrationProtocolRestController.class);
	@Autowired
	private RegistrationProtocolService registrationProtocolServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addRegistrationProtocol(@RequestBody RegistrationProtocol obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addRegistrationProtocol {}",obj);
			registrationProtocolServiceImpl.insertRegistrationProtocol(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addRegistrationProtocol异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getRegistrationProtocolById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getRegistrationProtocolById  {}",id);
			RegistrationProtocol registrationProtocol = registrationProtocolServiceImpl.selectRegistrationProtocolById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(registrationProtocol);
		} catch(Exception e) {
			log.error("getRegistrationProtocol异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateRegistrationProtocolById(@RequestBody RegistrationProtocol obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateRegistrationProtocolById  {}",obj);
			registrationProtocolServiceImpl.updateRegistrationProtocolById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateRegistrationProtocolById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findRegistrationProtocolList(@RequestBody RegistrationProtocol  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findRegistrationProtocolList  {}",obj);
			MidlandHelper.doPage(request);
			Page<RegistrationProtocol> list = (Page<RegistrationProtocol>)registrationProtocolServiceImpl.findRegistrationProtocolList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findRegistrationProtocolList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
