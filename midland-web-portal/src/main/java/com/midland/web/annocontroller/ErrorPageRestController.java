package com.midland.web.annocontroller;

import com.midland.web.model.ErrorPage;
import com.midland.web.service.ErrorPageService;
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
@RequestMapping("/errorPage/")
public class ErrorPageRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(ErrorPageRestController.class);
	@Autowired
	private ErrorPageService errorPageServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addErrorPage(@RequestBody ErrorPage obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addErrorPage {}",obj);
			errorPageServiceImpl.insertErrorPage(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addErrorPage异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getErrorPageById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getErrorPageById  {}",id);
			ErrorPage errorPage = errorPageServiceImpl.selectErrorPageById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(errorPage);
		} catch(Exception e) {
			log.error("getErrorPage异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateErrorPageById(@RequestBody ErrorPage obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateErrorPageById  {}",obj);
			errorPageServiceImpl.updateErrorPageById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateErrorPageById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findErrorPageList(@RequestBody ErrorPage  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findErrorPageList  {}",obj);
			MidlandHelper.doPage(request);
			Page<ErrorPage> list = (Page<ErrorPage>)errorPageServiceImpl.findErrorPageList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findErrorPageList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
