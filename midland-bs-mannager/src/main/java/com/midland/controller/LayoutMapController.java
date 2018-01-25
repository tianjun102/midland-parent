package com.midland.controller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.LayoutMap;
import com.midland.web.model.temp.ListDescOtherParam;
import com.midland.web.service.JdbcService;
import com.midland.web.service.LayoutMapService;
import com.midland.base.BaseFilter;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
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
@RequestMapping("/layoutMap/")
public class LayoutMapController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(LayoutMapController.class);
	@Autowired
	private LayoutMapService layoutMapServiceImpl;
	@Autowired
	private JdbcService jdbcService;
	/**
	 * 
	 **/
	@RequestMapping("index")
	public String layoutMapIndex(LayoutMap layoutMap,Model model) throws Exception {
		model.addAttribute("item",layoutMap);
		List<ParamObject> ojbect = JsonMapReader.getMap("turned");
		model.addAttribute("turneds",ojbect);
		return "layoutMap/layoutMapIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLayoutMap(LayoutMap layoutMap,Model model) throws Exception {
		model.addAttribute("item",layoutMap);
		List<ParamObject> ojbect = JsonMapReader.getMap("turned");
		model.addAttribute("turneds",ojbect);
		return "layoutMap/addLayoutMap";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLayoutMap(LayoutMap layoutMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addLayoutMap {}",layoutMap);
			layoutMap.setCreateTime(MidlandHelper.getCurrentTime());
			layoutMap.setIsDelete(Contant.isNotDelete);
			layoutMap.setIsShow(Contant.isShow);

			layoutMapServiceImpl.insertLayoutMap(layoutMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addLayoutMap异常 {}",layoutMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_layoutMap")
	public String getLayoutMapById(Integer id,Model model) {
		log.info("getLayoutMapById  {}",id);
		LayoutMap result = layoutMapServiceImpl.selectLayoutMapById(id);
		model.addAttribute("item",result);
		return "layoutMap/updateLayoutMap";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteLayoutMapById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteLayoutMapById  {}",id);
			layoutMapServiceImpl.deleteLayoutMapById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteLayoutMapById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLayoutMap(Integer id,Model model) throws Exception {
		LayoutMap result = layoutMapServiceImpl.selectLayoutMapById(id);
		model.addAttribute("item",result);
		List<ParamObject> ojbect = JsonMapReader.getMap("turned");
		model.addAttribute("turneds",ojbect);
		return "layoutMap/updateLayoutMap";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLayoutMapById(LayoutMap layoutMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateLayoutMapById  {}",layoutMap);
			layoutMapServiceImpl.updateLayoutMapById(layoutMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateLayoutMapById  {}",layoutMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLayoutMapList(LayoutMap layoutMap,Model model, HttpServletRequest request) {
		try {
			log.info("findLayoutMapList  {}",layoutMap);
			MidlandHelper.doPage(request);
			Page<LayoutMap> result = (Page<LayoutMap>)layoutMapServiceImpl.findLayoutMapList(layoutMap);
			Paginator paginator=result.getPaginator();
			List<ParamObject> ojbect = JsonMapReader.getMap("turned");
			model.addAttribute("turneds",ojbect);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findLayoutMapList  {}",layoutMap,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "layoutMap/layoutMapList";
	}

	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(LayoutMap layoutMap, int sort, Model model, HttpServletRequest request) throws Exception {
		if (sort==1){
			layoutMapServiceImpl.shiftUp(layoutMap);
		}else{
			layoutMapServiceImpl.shiftDown(layoutMap);
		}
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}
}
