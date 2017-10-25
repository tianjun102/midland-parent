package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.model.temp.ListDescOtherParam;
import com.midland.web.model.user.User;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
import com.midland.web.service.SiteMapService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@SuppressWarnings("all")
@RequestMapping("/siteMap/")
public class SiteMapController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(SiteMapController.class);
	@Autowired
	private SiteMapService siteMapServiceImpl;
	@Autowired
	private SettingService settingService;
    @Autowired
    private JdbcService jdbcService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String siteMapIndex(SiteMap siteMap,Model model,HttpServletRequest request) throws Exception {
		/*Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);*/
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "siteMap/siteMapIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddSiteMap(SiteMap siteMap,Model model,HttpServletRequest request) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		Category category = new Category();
		category.setType(4);
		String resultCate = getCategoryTree("",category);
		if(StringUtils.isNotEmpty(resultCate)){
			model.addAttribute("categoryData",resultCate );
		}
		model.addAttribute("cityList",cityList);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "siteMap/addSiteMap";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addSiteMap(SiteMap siteMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addSiteMap {}",siteMap);
			siteMapServiceImpl.insertSiteMap(siteMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addSiteMap异常 {}",siteMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_siteMap")
	public String getSiteMapById(Integer id,Model model) {
		log.debug("getSiteMapById  {}",id);
		SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
		model.addAttribute("item",result);
		return "siteMap/updateSiteMap";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteSiteMapById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteSiteMapById  {}",id);
			siteMapServiceImpl.deleteSiteMapById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteSiteMapById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateSiteMap(Integer id,Model model,HttpServletRequest request) throws Exception {
		SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		Category category = new Category();
		category.setType(4);
		String resultCate = getCategoryTree("",category);
		if(StringUtils.isNotEmpty(resultCate)){
			model.addAttribute("categoryData",resultCate );
		}
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "siteMap/updateSiteMap";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateSiteMapById(SiteMap siteMap) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateSiteMapById  {}",siteMap);
			siteMapServiceImpl.updateSiteMapById(siteMap);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateSiteMapById  {}",siteMap,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findSiteMapList(SiteMap siteMap,Model model, HttpServletRequest request) {
		try {
			log.debug("findSiteMapList  {}",siteMap);
			MidlandHelper.doPage(request);
			Page<SiteMap> result = (Page<SiteMap>)siteMapServiceImpl.findSiteMapList(siteMap);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findSiteMapList  {}",siteMap,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "siteMap/siteMapList";
	}

    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(SiteMap siteMap, int sort, Model model, HttpServletRequest request) throws Exception {
        String primaryKeyName="id";
        String primaryParam=String.valueOf(siteMap.getId());
        String tableName="site_map";
        String orderByColumn="order_by";
		ListDescOtherParam obj = new ListDescOtherParam();
		obj.setCityId(null);
		obj.setType(null);
		obj.setSource(null);
        String orderByParam=String.valueOf(siteMap.getOrderBy());
        jdbcService.listDesc(primaryKeyName,primaryParam,orderByColumn,tableName,orderByParam,obj,sort);
        Map map = new HashMap();
        map.put("state",0);
        return map;
    }

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,SiteMap siteMap) throws Exception {
		List<SiteMap> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			SiteMap comment1 = new SiteMap();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(siteMap.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			siteMapServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
