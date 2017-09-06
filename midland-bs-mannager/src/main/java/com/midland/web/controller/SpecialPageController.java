package com.midland.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.controller.base.BaseController;
import com.midland.web.model.Area;
import com.midland.web.model.SpecialPage;
import com.midland.web.service.SettingService;
import com.midland.web.service.SpecialPageService;
import com.midland.web.util.MidlandHelper;
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

@Controller
@SuppressWarnings("all")
@RequestMapping("/specialPage/")
public class SpecialPageController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(SpecialPageController.class);
	@Autowired
	private SpecialPageService specialPageServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String specialPageIndex(SpecialPage specialPage, Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "specialPage/specialPageIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddSpecialPage(SpecialPage specialPage, Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "specialPage/addSpecialPage";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addSpecialPage(SpecialPage specialPage) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addSpecialPage {}",specialPage);
			specialPageServiceImpl.insertSpecialPage(specialPage);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addSpecialPage异常 {}",specialPage,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_specialPage")
	public String getSpecialPageById(Integer id,Model model) {
		log.info("getSpecialPageById  {}",id);
		SpecialPage result = specialPageServiceImpl.selectSpecialPageById(id);
		model.addAttribute("item",result);
		return "specialPage/updateSpecialPage";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteSpecialPageById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteSpecialPageById  {}",id);
			specialPageServiceImpl.deleteSpecialPageById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteSpecialPageById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateSpecialPage(Integer id,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		SpecialPage result = specialPageServiceImpl.selectSpecialPageById(id);
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "specialPage/updateSpecialPage";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateSpecialPageById(SpecialPage specialPage) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateSpecialPageById  {}",specialPage);
			specialPageServiceImpl.updateSpecialPageById(specialPage);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateSpecialPageById  {}",specialPage,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findSpecialPageList(SpecialPage specialPage, Model model, HttpServletRequest request) {
		try {
			log.info("findSpecialPageList  {}",specialPage);
			MidlandHelper.doPage(request);
			Page<SpecialPage> result = (Page<SpecialPage>)specialPageServiceImpl.findSpecialPageList(specialPage);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findSpecialPageList  {}",specialPage,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "specialPage/specialPageList";
	}
}
