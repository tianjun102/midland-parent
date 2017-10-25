package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.EliteClub;
import com.midland.web.model.user.User;
import com.midland.web.service.EliteClubService;
import com.midland.web.service.SettingService;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/eliteClub/")
public class EliteClubController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(EliteClubController.class);
	@Autowired
	private EliteClubService eliteClubServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String eliteClubIndex(EliteClub eliteClub,Model model,HttpServletRequest request) throws Exception {
		/*Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);*/
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "eliteClub/eliteClubIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddEliteClub(EliteClub eliteClub,Model model,HttpServletRequest request) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "eliteClub/addEliteClub";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addEliteClub(EliteClub eliteClub) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addEliteClub {}",eliteClub);
			eliteClubServiceImpl.insertEliteClub(eliteClub);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addEliteClub异常 {}",eliteClub,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_eliteClub")
	public String getEliteClubById(Integer id,Model model) {
		log.debug("getEliteClubById  {}",id);
		EliteClub result = eliteClubServiceImpl.selectEliteClubById(id);
		model.addAttribute("item",result);
		return "eliteClub/updateEliteClub";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteEliteClubById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteEliteClubById  {}",id);
			eliteClubServiceImpl.deleteEliteClubById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteEliteClubById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateEliteClub(Integer id,Model model,HttpServletRequest request) throws Exception {
		EliteClub result = eliteClubServiceImpl.selectEliteClubById(id);
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "eliteClub/updateEliteClub";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateEliteClubById(EliteClub eliteClub) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateEliteClubById  {}",eliteClub);
			eliteClubServiceImpl.updateEliteClubById(eliteClub);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateEliteClubById  {}",eliteClub,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findEliteClubList(EliteClub eliteClub,Model model, HttpServletRequest request) {
		try {
			log.debug("findEliteClubList  {}",eliteClub);
			MidlandHelper.doPage(request);
			Page<EliteClub> result = (Page<EliteClub>)eliteClubServiceImpl.findEliteClubList(eliteClub);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findEliteClubList  {}",eliteClub,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "eliteClub/eliteClubList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,EliteClub eliteClub) throws Exception {
		List<EliteClub> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			EliteClub comment1 = new EliteClub();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(eliteClub.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			eliteClubServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
