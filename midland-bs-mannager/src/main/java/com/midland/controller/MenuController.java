package com.midland.controller;

import com.midland.web.model.Area;
import com.midland.web.model.LiaisonRecordEmail;
import com.midland.web.model.Menu;
import com.midland.web.model.temp.ListDescOtherParam;
import com.midland.web.model.user.User;
import com.midland.web.service.JdbcService;
import com.midland.web.service.MenuService;
import com.midland.base.BaseFilter;
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
public class MenuController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private JdbcService jdbcService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String menuIndex(Menu menu,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
		return "menu/menuIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddMenu(Menu menu,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
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
			int maxOrderBy = menuServiceImpl.getMaxOrderBy();
			menu.setOrderBy(maxOrderBy);
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
			if (StringUtils.isEmpty(menu.getCityId())){
				User user = MidlandHelper.getCurrentUser(request);
				menu.setCityId(user.getCityId());
			}
			MidlandHelper.doPage(request);
			Page<Menu> result = (Page<Menu>)menuServiceImpl.findMenuList(menu);
			Paginator paginator=result.getPaginator();
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
		String primaryKeyName="id";
		String primaryParam=String.valueOf(menu.getId());
		String tableName="menu";
		String orderByColumn="order_by";
		ListDescOtherParam obj = new ListDescOtherParam();
		if (StringUtils.isNotEmpty(menu.getCityId())) {
			obj.setCityId(menu.getCityId());
		}else{
			User currUser = MidlandHelper.getCurrentUser(request);
			obj.setCityId(currUser.getCityId());
		}
		obj.setType(null);
		obj.setSource(menu.getSource());
		String orderByParam=String.valueOf(menu.getOrderBy());
		Map map = new HashMap();
		try {
			jdbcService.menuListDesc(primaryKeyName,primaryParam,orderByColumn,tableName,orderByParam,obj,sort);
			map.put("state",0);
		} catch (Exception e) {
			log.error("",e);
			map.put("state",-1);
		}
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
			log.debug("updateCategoryById  {}",commentList);
			menuServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
