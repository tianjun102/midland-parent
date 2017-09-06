package com.midland.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.controller.base.BaseController;
import com.midland.web.model.Area;
import com.midland.web.model.Menu;
import com.midland.web.service.JdbcService;
import com.midland.web.service.MenuService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/1.
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private JdbcService jdbcService;
	@RequestMapping("/index")
	public String menuIndex(Model model ,HttpServletRequest request){
		
		return "/menu/menuIndex";
	}
	@RequestMapping("list")
	public String showMenuIndex(Menu menu, Model model, HttpServletRequest request) throws Exception {
		MidlandHelper.doPage(request);
		Page<Menu> result=(Page<Menu>) menuServiceImpl.findMenuList(menu);
		Paginator paginator = result.getPaginator();
		model.addAttribute("paginator", paginator);
		model.addAttribute("items", result);
		return "/menu/menuList";
	}
	
	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(Menu menu, int sort, Model model, HttpServletRequest request) throws Exception {
		String primaryKeyName="id";
		String primaryParam=String.valueOf(menu.getId());
		String tableName="menu";
		String orderByColumn="order_by";
		String orderByParam=String.valueOf(menu.getOrderBy());
		
		jdbcService.listDesc(primaryKeyName,primaryParam,orderByColumn,tableName,orderByParam,sort);
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}
	@RequestMapping("to_update")
	public String toUpdate(int id, Model model, HttpServletRequest request) throws Exception {
		Menu result = menuServiceImpl.selectMenuById(id);
		model.addAttribute("item",result);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources",sources);
		
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		return "/menu/updateMenu";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Object update(Menu menu) throws Exception {
		menuServiceImpl.updateMenuById(menu);
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(Menu menu) {
		Map map = new HashMap();
		try {
			menuServiceImpl.updateMenuById(menu);
			map.put("state",0);
			
		} catch (Exception e) {
			logger.error("delete : {}",menu,e);
			map.put("state",-1);
		}
		
		return map;
	}
	
}
