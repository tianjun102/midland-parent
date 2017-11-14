package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Menu;
import com.midland.web.service.MenuService;
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
@RequestMapping("/menu/")
public class MenuRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(MenuRestController.class);
	@Autowired
	private MenuService menuServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addMenu(@RequestBody Menu obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addMenu {}",obj);
			menuServiceImpl.insertMenu(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addMenu异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getMenuById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getMenuById  {}",id);
			Menu menu = menuServiceImpl.selectMenuById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(menu);
		} catch(Exception e) {
			log.error("getMenu异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateMenuById(@RequestBody Menu obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateMenuById  {}",obj);
			menuServiceImpl.updateMenuById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateMenuById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findMenuList(@RequestBody Menu  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findMenuList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<Menu> list = (Page<Menu>)menuServiceImpl.findMenuList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findMenuList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
