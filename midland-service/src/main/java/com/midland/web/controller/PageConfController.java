package com.midland.web.controller;

import com.midland.web.model.Area;
import com.midland.web.model.PageConf;
import com.midland.web.service.PageConfService;
import com.midland.web.controller.base.BaseController;
import com.midland.web.service.SettingService;
import org.slf4j.Logger;
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
@RequestMapping("/pageConf/")
public class PageConfController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(PageConfController.class);
	@Autowired
	private PageConfService pageConfServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String pageConfIndex(PageConf pageConf,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "pageConf/pageConfIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddPageConf(PageConf pageConf,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "pageConf/addPageConf";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addPageConf(PageConf pageConf) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addPageConf {}",pageConf);
			pageConfServiceImpl.insertPageConf(pageConf);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addPageConf异常 {}",pageConf,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_pageConf")
	public String getPageConfById(Integer id,Model model) {
		log.info("getPageConfById  {}",id);
		PageConf result = pageConfServiceImpl.selectPageConfById(id);
		model.addAttribute("item",result);
		return "pageConf/updatePageConf";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deletePageConfById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deletePageConfById  {}",id);
			pageConfServiceImpl.deletePageConfById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deletePageConfById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdatePageConf(Integer id,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		PageConf result = pageConfServiceImpl.selectPageConfById(id);
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "pageConf/updatePageConf";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updatePageConfById(PageConf pageConf) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updatePageConfById  {}",pageConf);
			pageConfServiceImpl.updatePageConfById(pageConf);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updatePageConfById  {}",pageConf,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findPageConfList(PageConf pageConf,Model model, HttpServletRequest request) {
		try {
			log.info("findPageConfList  {}",pageConf);
			MidlandHelper.doPage(request);
			Page<PageConf> result = (Page<PageConf>)pageConfServiceImpl.findPageConfList(pageConf);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findPageConfList  {}",pageConf,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "pageConf/pageConfList";
	}
}
