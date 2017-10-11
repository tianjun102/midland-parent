package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.PopularCate;
import com.midland.web.model.user.User;
import com.midland.web.service.PopularCateService;
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
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/popularCate/")
public class PopularCateController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(PopularCateController.class);
	@Autowired
	private PopularCateService popularCateServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String popularCateIndex(PopularCate popularCate,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("cityId",user.getCityId());
		return "popularCate/popularCateIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddPopularCate(PopularCate popularCate,Model model) throws Exception {
		return "popularCate/addPopularCate";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addPopularCate(PopularCate popularCate) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addPopularCate {}",popularCate);
			popularCateServiceImpl.insertPopularCate(popularCate);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addPopularCate异常 {}",popularCate,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_popularCate")
	public String getPopularCateById(Integer id,Model model) {
		log.info("getPopularCateById  {}",id);
		PopularCate result = popularCateServiceImpl.selectPopularCateById(id);
		model.addAttribute("item",result);
		return "popularCate/updatePopularCate";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deletePopularCateById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deletePopularCateById  {}",id);
			popularCateServiceImpl.deletePopularCateById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deletePopularCateById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdatePopularCate(Integer id,Model model) throws Exception {
		PopularCate result = popularCateServiceImpl.selectPopularCateById(id);
		model.addAttribute("item",result);
		return "popularCate/updatePopularCate";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updatePopularCateById(PopularCate popularCate) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updatePopularCateById  {}",popularCate);
			popularCateServiceImpl.updatePopularCateById(popularCate);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updatePopularCateById  {}",popularCate,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findPopularCateList(PopularCate popularCate,Model model, HttpServletRequest request) {
		try {
			log.info("findPopularCateList  {}",popularCate);
			MidlandHelper.doPage(request);
			Page<PopularCate> result = (Page<PopularCate>)popularCateServiceImpl.findPopularCateList(popularCate);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findPopularCateList  {}",popularCate,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "popularCate/popularCateList";
	}
}
