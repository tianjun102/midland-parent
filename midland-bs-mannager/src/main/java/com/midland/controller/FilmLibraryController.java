package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.model.Area;
import com.midland.web.model.FilmLibrary;
import com.midland.web.model.user.User;
import com.midland.web.service.FilmLibraryService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
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
@RequestMapping("/filmLibrary/")
public class FilmLibraryController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(FilmLibraryController.class);
	@Autowired
	private FilmLibraryService filmLibraryServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String filmLibraryIndex(FilmLibrary filmLibrary, Model model,HttpServletRequest request) throws Exception {
		List<Area> list = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		List<ParamObject> obj1 = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes",obj1);
		List<ParamObject> obj = JsonMapReader.getMap("film_type");
		model.addAttribute("filmTypes",obj);
		model.addAttribute("citys",list);
		return "filmLibrary/filmLibraryIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddFilmLibrary(FilmLibrary filmLibrary, Model model) throws Exception {
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);
		return "filmLibrary/addFilmLibrary";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addFilmLibrary(FilmLibrary filmLibrary, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = MidlandHelper.getCurrentUser(request);
			filmLibrary.setOperatorName(user.getUserCnName());
			filmLibrary.setOperatorId(user.getId());
			log.debug("addFilmLibrary {}",filmLibrary);
			filmLibraryServiceImpl.insertFilmLibrary(filmLibrary);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addFilmLibrary异常 {}",filmLibrary,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_filmLibrary")
	public String getFilmLibraryById(Integer id,Model model) {
		log.debug("getFilmLibraryById  {}",id);
		FilmLibrary result = filmLibraryServiceImpl.selectFilmLibraryById(id);
		model.addAttribute("item",result);
		return "filmLibrary/updateFilmLibrary";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteFilmLibraryById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteFilmLibraryById  {}",id);
			filmLibraryServiceImpl.deleteFilmLibraryById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteFilmLibraryById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateFilmLibrary(Integer id,Model model) throws Exception {
		FilmLibrary result = filmLibraryServiceImpl.selectFilmLibraryById(id);
		model.addAttribute("item",result);
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);

		return "filmLibrary/updateFilmLibrary";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateFilmLibraryById(FilmLibrary filmLibrary) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateFilmLibraryById  {}",filmLibrary);
			filmLibraryServiceImpl.updateFilmLibraryById(filmLibrary);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateFilmLibraryById  {}",filmLibrary,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findFilmLibraryList(FilmLibrary filmLibrary, Model model, HttpServletRequest request) {
		try {
			log.debug("findFilmLibraryList  {}",filmLibrary);
			User user = MidlandHelper.getCurrentUser(request);
			model.addAttribute("isSuper",user.getIsSuper());
			if(!Contant.isSuper.equals(user.getIsSuper())){//不是超级管理员，只能看属性城市的相关信息
				filmLibrary.setCityId(user.getCityId());
			}
			List<ParamObject> obj1 = JsonMapReader.getMap("is_delete");
			model.addAttribute("isDeletes",obj1);
			MidlandHelper.doPage(request);
			Page<FilmLibrary> result = (Page<FilmLibrary>)filmLibraryServiceImpl.findFilmLibraryList(filmLibrary);
			Paginator paginator=result.getPaginator();
			List<ParamObject> obj = JsonMapReader.getMap("film_type");
			model.addAttribute("filmTypes",obj);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findFilmLibraryList  {}",filmLibrary,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "filmLibrary/filmLibraryList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,FilmLibrary filmLibrary) throws Exception {
		List<FilmLibrary> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			FilmLibrary comment1 = new FilmLibrary();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(filmLibrary.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			filmLibraryServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
