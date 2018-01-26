package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Privacy;
import com.midland.web.service.PrivacyService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RestController
@SuppressWarnings("all")
@RequestMapping("/privacy/")
public class PrivacyRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(PrivacyRestController.class);
	@Autowired
	private PrivacyService privacyServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addPrivacy(@RequestBody Privacy obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addPrivacy {}",obj);
			privacyServiceImpl.insertPrivacy(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addPrivacy异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getPrivacyById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getPrivacyById  {}",id);
			Privacy privacy = privacyServiceImpl.selectPrivacyById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(privacy);
		} catch(Exception e) {
			log.error("getPrivacy异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updatePrivacyById(@RequestBody Privacy obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updatePrivacyById  {}",obj);
			privacyServiceImpl.updatePrivacyById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updatePrivacyById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findPrivacyList(@RequestBody Privacy  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findPrivacyList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Privacy> list = (Page<Privacy>)privacyServiceImpl.findPrivacyList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findPrivacyList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
