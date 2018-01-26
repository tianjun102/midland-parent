package com.midland.controller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.MenuType;
import com.midland.web.model.user.User;
import com.midland.web.service.MenuTypeService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/menuType/")
public class MenuTypeController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(MenuTypeController.class);
	@Autowired
	private MenuTypeService menuTypeServiceImpl;
@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String menuTypeIndex(MenuType menuType,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		List<ParamObject> obj = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes",obj);
		return "menuType/menuTypeIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddMenuType(MenuType menuType,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<MenuType> rootMentType = menuTypeServiceImpl.findRootMenuTypeList();
		model.addAttribute("rootMentTypes",rootMentType);
		return "menuType/addMenuType";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addMenuType(MenuType menuType) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addMenuType {}",menuType);
			menuTypeServiceImpl.insertMenuType(menuType);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addMenuType异常 {}",menuType,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_menuType")
	public String getMenuTypeById(Integer id,Model model) {
		log.info("getMenuTypeById  {}",id);
		MenuType result = menuTypeServiceImpl.selectMenuTypeById(id);
		model.addAttribute("item",result);
		return "menuType/updateMenuType";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteMenuTypeById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteMenuTypeById  {}",id);
			menuTypeServiceImpl.deleteMenuTypeById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteMenuTypeById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}

	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(MenuType menuType, int sort, Model model, HttpServletRequest request) throws Exception {
		if (sort==1){
			menuTypeServiceImpl.shiftUp(menuType);
		}else{
			menuTypeServiceImpl.shiftDown(menuType);
		}
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}


	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateMenuType(Integer id,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		MenuType result = menuTypeServiceImpl.selectMenuTypeById(id);
		List<MenuType> rootMentType = menuTypeServiceImpl.findRootMenuTypeList();
		model.addAttribute("rootMentTypes",rootMentType);
		model.addAttribute("item",result);
		return "menuType/updateMenuType";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateMenuTypeById(MenuType menuType) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateMenuTypeById  {}",menuType);
			menuTypeServiceImpl.updateMenuTypeById(menuType);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateMenuTypeById  {}",menuType,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findMenuTypeList(MenuType menuType,Model model, HttpServletRequest request) {
		try {
			log.info("findMenuTypeList  {}",menuType);
			User user = MidlandHelper.getCurrentUser(request);
			model.addAttribute("isSuper",user.getIsSuper());
			if(!Contant.isSuper.equals(user.getIsSuper())){//不是超级管理员，只能看属性城市的相关信息
				menuType.setCityId(user.getCityId());
				menuType.setIsDelete(Contant.isNotDelete);
			}
			MidlandHelper.doPage(request);
			Page<MenuType> result = (Page<MenuType>)menuTypeServiceImpl.findMenuTypeList(menuType);
			Paginator paginator=result.getPaginator();
			List<ParamObject> obj = JsonMapReader.getMap("is_delete");
			model.addAttribute("isDeletes",obj);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findMenuTypeList  {}",menuType,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "menuType/menuTypeList";
	}
	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,MenuType menuType) throws Exception {
		List<MenuType> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			MenuType comment1 = new MenuType();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(menuType.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("batchUpdate  {}",commentList);
			menuTypeServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("batchUpdate  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}

}
