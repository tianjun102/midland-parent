package com.midland.controller;

import com.midland.web.model.HuxingMap;
import com.midland.web.service.HuxingMapService;
import com.midland.base.BaseFilter;
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
@RequestMapping("/huxingMap/")
public class HuxingMapController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(HuxingMapController.class);
	@Autowired
	private HuxingMapService huxingMapServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String huxingMapIndex(HuxingMap huxingMap,Model model) throws Exception {
		return "huxingMap/huxingMapIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddHuxingMap(HuxingMap huxingMap,Model model) throws Exception {
		return "huxingMap/addHuxingMap";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addHuxingMap(HuxingMap huxingMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addHuxingMap {}",huxingMap);
			huxingMapServiceImpl.insertHuxingMap(huxingMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addHuxingMap异常 {}",huxingMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_huxingMap")
	public String getHuxingMapById(Integer id,Model model) {
		log.info("getHuxingMapById  {}",id);
		HuxingMap result = huxingMapServiceImpl.selectHuxingMapById(id);
		model.addAttribute("item",result);
		return "huxingMap/updateHuxingMap";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteHuxingMapById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteHuxingMapById  {}",id);
			huxingMapServiceImpl.deleteHuxingMapById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteHuxingMapById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateHuxingMap(Integer id,Model model) throws Exception {
		HuxingMap result = huxingMapServiceImpl.selectHuxingMapById(id);
		model.addAttribute("item",result);
		return "huxingMap/updateHuxingMap";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateHuxingMapById(HuxingMap huxingMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateHuxingMapById  {}",huxingMap);
			huxingMapServiceImpl.updateHuxingMapById(huxingMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateHuxingMapById  {}",huxingMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findHuxingMapList(HuxingMap huxingMap,Model model, HttpServletRequest request) {
		try {
			log.info("findHuxingMapList  {}",huxingMap);
			MidlandHelper.doPage(request);
			Page<HuxingMap> result = (Page<HuxingMap>)huxingMapServiceImpl.findHuxingMapList(huxingMap);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findHuxingMapList  {}",huxingMap,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "huxingMap/huxingMapList";
	}
}
