package com.midland.controller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Menu;
import com.midland.web.model.MenuType;
import com.midland.web.model.temp.ListDescOtherParam;
import com.midland.web.model.user.User;
import com.midland.web.service.JdbcService;
import com.midland.web.service.MenuService;
import com.midland.base.BaseFilter;
import com.midland.web.service.MenuTypeService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/menu/")
public class MenuController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private MenuTypeService menuTypeServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private JdbcService jdbcService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String menuIndex(Menu menu,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		List<ParamObject> obj = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes",obj);
		return "menu/menuIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddMenu(Menu menu,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		String res = menuTypeServiceImpl.findRootMenuTypeTree(new MenuType());
		model.addAttribute("menuTypes", res);
		return "menu/addMenu";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addMenu(Menu menu) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addMenu {}",menu);

			menuServiceImpl.insertMenu(menu);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addMenu异常 {}",menu,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_menu")
	public String getMenuById(Integer id,Model model) {
		log.debug("getMenuById  {}",id);
		Menu result = menuServiceImpl.selectMenuById(id);
		model.addAttribute("item",result);
		return "menu/updateMenu";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteMenuById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteMenuById  {}",id);
			menuServiceImpl.deleteMenuById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteMenuById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateMenu(Integer id,Model model) throws Exception {
		settingService.getAllProvinceList(model);

		Menu result = menuServiceImpl.selectMenuById(id);
		model.addAttribute("item",result);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
		settingService.getAllProvinceList(model);
		String res = menuTypeServiceImpl.findRootMenuTypeTree(new MenuType());
		model.addAttribute("menuTypes", res);
		return "menu/updateMenu";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateMenuById(Menu menu) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateMenuById  {}",menu);
			menuServiceImpl.updateMenuById(menu);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateMenuById  {}",menu,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findMenuList(Menu menu,Model model, HttpServletRequest request) {
		try {
			log.debug("findMenuList  {}",menu);
			User user = MidlandHelper.getCurrentUser(request);
			model.addAttribute("isSuper",user.getIsSuper());
			if(!Contant.isSuper.equals(user.getIsSuper())){//不是超级管理员，只能看属性城市的相关信息
				menu.setCityId(user.getCityId());
			}
			MidlandHelper.doPage(request);
			Page<Menu> result = (Page<Menu>)menuServiceImpl.findMenuList(menu);
			Paginator paginator=result.getPaginator();
			List<ParamObject> obj = JsonMapReader.getMap("is_delete");
			model.addAttribute("isDeletes",obj);
			List<ParamObject> sources = JsonMapReader.getMap("source");
			model.addAttribute("sources",sources);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findMenuList  {}",menu,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "menu/menuList";
	}
	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(Menu menu, int sort, Model model, HttpServletRequest request) throws Exception {
		if (sort==1){
			menuServiceImpl.shiftUp(menu);
		}else{
			menuServiceImpl.shiftDown(menu);
		}
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,Menu menu) throws Exception {
		List<Menu> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			Menu comment1 = new Menu();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(menu.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("batchUpdate  {}",commentList);
			menuServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("batchUpdate  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
