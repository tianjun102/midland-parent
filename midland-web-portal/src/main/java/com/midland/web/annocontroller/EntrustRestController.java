package com.midland.web.annocontroller;

import com.midland.web.model.Entrust;
import com.midland.web.service.EntrustService;
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
@RequestMapping("/entrust/")
public class EntrustRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(EntrustRestController.class);
	@Autowired
	private EntrustService entrustServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addEntrust(@RequestBody Entrust obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addEntrust {}",obj);
			entrustServiceImpl.insertEntrust(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addEntrust异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getEntrustById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getEntrustById  {}",id);
			Entrust entrust = entrustServiceImpl.selectEntrustById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(entrust);
		} catch(Exception e) {
			log.error("getEntrust异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateEntrustById(@RequestBody Entrust obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateEntrustById  {}",obj);
			entrustServiceImpl.updateEntrustById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateEntrustById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findEntrustList(@RequestBody Entrust  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findEntrustList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Entrust> list = (Page<Entrust>)entrustServiceImpl.findEntrustList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findEntrustList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
