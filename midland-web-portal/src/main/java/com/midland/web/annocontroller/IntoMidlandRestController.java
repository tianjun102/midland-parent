package com.midland.web.annocontroller;

import com.midland.web.model.IntoMidland;
import com.midland.web.service.IntoMidlandService;
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
@RequestMapping("/intoMidland/")
public class IntoMidlandRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(IntoMidlandRestController.class);
	@Autowired
	private IntoMidlandService intoMidlandServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addIntoMidland(@RequestBody IntoMidland obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addIntoMidland {}",obj);
			intoMidlandServiceImpl.insertIntoMidland(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addIntoMidland异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getIntoMidlandById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getIntoMidlandById  {}",id);
			IntoMidland intoMidland = intoMidlandServiceImpl.selectIntoMidlandById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(intoMidland);
		} catch(Exception e) {
			log.error("getIntoMidland异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateIntoMidlandById(@RequestBody IntoMidland obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateIntoMidlandById  {}",obj);
			intoMidlandServiceImpl.updateIntoMidlandById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateIntoMidlandById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findIntoMidlandList(@RequestBody IntoMidland  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findIntoMidlandList  {}",obj);
			MidlandHelper.doPage(request);
			Page<IntoMidland> list = (Page<IntoMidland>)intoMidlandServiceImpl.findIntoMidlandList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findIntoMidlandList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
