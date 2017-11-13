package com.midland.web.annocontroller;

import com.midland.web.model.MenuType;
import com.midland.web.service.MenuTypeService;
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
@RequestMapping("/menuType/")
public class MenuTypeRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(MenuTypeRestController.class);
	@Autowired
	private MenuTypeService menuTypeServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addMenuType(@RequestBody MenuType obj) throws Exception {
		Result result=new Result();
		try {
			log.info("addMenuType {}",obj);
			menuTypeServiceImpl.insertMenuType(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addMenuType异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getMenuTypeById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getMenuTypeById  {}",id);
			MenuType menuType = menuTypeServiceImpl.selectMenuTypeById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(menuType);
		} catch(Exception e) {
			log.error("getMenuType异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateMenuTypeById(@RequestBody MenuType obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateMenuTypeById  {}",obj);
			menuTypeServiceImpl.updateMenuTypeById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateMenuTypeById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findMenuTypeList(@RequestBody MenuType  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findMenuTypeList  {}",obj);
			MidlandHelper.doPage(request);
			Page<MenuType> list = (Page<MenuType>)menuTypeServiceImpl.findMenuTypeList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findMenuTypeList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
