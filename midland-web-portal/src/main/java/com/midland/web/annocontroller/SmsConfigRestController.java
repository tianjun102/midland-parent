package com.midland.web.annocontroller;

import com.midland.web.model.SmsConfig;
import com.midland.web.service.SmsConfigService;
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
@RequestMapping("/smsConfig/")
public class SmsConfigRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(SmsConfigRestController.class);
	@Autowired
	private SmsConfigService smsConfigServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addSmsConfig(@RequestBody SmsConfig obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addSmsConfig {}",obj);
			smsConfigServiceImpl.insertSmsConfig(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addSmsConfig异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getSmsConfigById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getSmsConfigById  {}",id);
			SmsConfig smsConfig = smsConfigServiceImpl.selectSmsConfigById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(smsConfig);
		} catch(Exception e) {
			log.error("getSmsConfig异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateSmsConfigById(@RequestBody SmsConfig obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateSmsConfigById  {}",obj);
			smsConfigServiceImpl.updateSmsConfigById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateSmsConfigById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findSmsConfigList(@RequestBody SmsConfig  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findSmsConfigList  {}",obj);
			MidlandHelper.doPage(request);
			Page<SmsConfig> list = (Page<SmsConfig>)smsConfigServiceImpl.findSmsConfigList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findSmsConfigList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
