package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.LayoutMap;
import com.midland.web.service.LayoutMapService;
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
@RequestMapping("/layoutMap/")
public class LayoutMapRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(LayoutMapRestController.class);
	@Autowired
	private LayoutMapService layoutMapServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addLayoutMap(@RequestBody LayoutMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addLayoutMap {}",obj);
			layoutMapServiceImpl.insertLayoutMap(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addLayoutMap异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getLayoutMapById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getLayoutMapById  {}",id);
			LayoutMap layoutMap = layoutMapServiceImpl.selectLayoutMapById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(layoutMap);
		} catch(Exception e) {
			log.error("getLayoutMap异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateLayoutMapById(@RequestBody LayoutMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateLayoutMapById  {}",obj);
			layoutMapServiceImpl.updateLayoutMapById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLayoutMapById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLayoutMapList(@RequestBody LayoutMap  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findLayoutMapList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<LayoutMap> list = (Page<LayoutMap>)layoutMapServiceImpl.findLayoutMapList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findLayoutMapList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
