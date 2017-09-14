package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.HotSearch;
import com.midland.web.model.Information;
import com.midland.web.service.HotSearchService;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
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
@RequestMapping("/hotSearch/")
public class HotSearchController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(HotSearchController.class);
	@Autowired
	private HotSearchService hotSearchServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private JdbcService jdbcService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String hotSearchIndex(HotSearch hotSearch, Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "hotSearch/hotSearchIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddHotSearch(HotSearch hotSearch, Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "hotSearch/addHotSearch";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addHotSearch(HotSearch hotSearch) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addHotSearch {}",hotSearch);
			hotSearchServiceImpl.insertHotSearch(hotSearch);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addHotSearch异常 {}",hotSearch,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_hotSearch")
	public String getHotSearchById(Integer id,Model model) {
		log.info("getHotSearchById  {}",id);
		HotSearch result = hotSearchServiceImpl.selectHotSearchById(id);
		model.addAttribute("item",result);
		return "hotSearch/updateHotSearch";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteHotSearchById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteHotSearchById  {}",id);
			hotSearchServiceImpl.deleteHotSearchById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteHotSearchById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateHotSearch(Integer id,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		HotSearch result = hotSearchServiceImpl.selectHotSearchById(id);
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "hotSearch/updateHotSearch";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateHotSearchById(HotSearch hotSearch) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateHotSearchById  {}",hotSearch);
			hotSearchServiceImpl.updateHotSearchById(hotSearch);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateHotSearchById  {}",hotSearch,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findHotSearchList(HotSearch hotSearch, Model model, HttpServletRequest request) {
		try {
			log.info("findHotSearchList  {}",hotSearch);
			MidlandHelper.doPage(request);
			Page<HotSearch> result = (Page<HotSearch>)hotSearchServiceImpl.findHotSearchList(hotSearch);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findHotSearchList  {}",hotSearch,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "hotSearch/hotSearchList";
	}

	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(Information information, int sort, Model model, HttpServletRequest request) throws Exception {
		String primaryKeyName="id";
		String primaryParam=String.valueOf(information.getId());
		String tableName="hot_search";
		String orderByColumn="order_by";
		String orderByParam=String.valueOf(information.getOrderBy());
		jdbcService.listDesc(primaryKeyName,primaryParam,orderByColumn,tableName,orderByParam,sort);
		Map map = new HashMap();
		map.put("state",0);
		return map;
	}
}
