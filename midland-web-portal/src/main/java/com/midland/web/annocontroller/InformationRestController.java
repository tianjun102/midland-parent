package com.midland.web.annocontroller;

import com.midland.web.model.Information;
import com.midland.web.service.InformationService;
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
@RequestMapping("/information/")
public class InformationRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(InformationRestController.class);
	@Autowired
	private InformationService informationServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addInformation(@RequestBody Information obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addInformation {}",obj);
			informationServiceImpl.insertInformation(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addInformation异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getInformationById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getInformationById  {}",id);
			Information information = informationServiceImpl.selectInformationById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(information);
		} catch(Exception e) {
			log.error("getInformation异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateInformationById(@RequestBody Information obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateInformationById  {}",obj);
			informationServiceImpl.updateInformationById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateInformationById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findInformationList(@RequestBody Information  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findInformationList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Information> list = (Page<Information>)informationServiceImpl.findInformationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findInformationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
