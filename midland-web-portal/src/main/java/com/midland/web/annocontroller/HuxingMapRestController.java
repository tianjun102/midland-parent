package com.midland.web.annocontroller;

import com.midland.web.model.HuxingMap;
import com.midland.web.service.HuxingMapService;
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
@RequestMapping("/huxingMap/")
public class HuxingMapRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(HuxingMapRestController.class);
	@Autowired
	private HuxingMapService huxingMapServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addHuxingMap(@RequestBody HuxingMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addHuxingMap {}",obj);
			huxingMapServiceImpl.insertHuxingMap(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addHuxingMap异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getHuxingMapById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getHuxingMapById  {}",id);
			HuxingMap huxingMap = huxingMapServiceImpl.selectHuxingMapById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(huxingMap);
		} catch(Exception e) {
			log.error("getHuxingMap异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateHuxingMapById(@RequestBody HuxingMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateHuxingMapById  {}",obj);
			huxingMapServiceImpl.updateHuxingMapById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateHuxingMapById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findHuxingMapList(@RequestBody HuxingMap  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findHuxingMapList  {}",obj);
			MidlandHelper.doPage(request);
			Page<HuxingMap> list = (Page<HuxingMap>)huxingMapServiceImpl.findHuxingMapList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findHuxingMapList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
