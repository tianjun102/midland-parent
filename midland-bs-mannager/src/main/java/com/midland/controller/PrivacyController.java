package com.midland.controller;

import com.midland.web.model.Privacy;
import com.midland.web.model.user.User;
import com.midland.web.service.PrivacyService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/privacy/")
public class PrivacyController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(PrivacyController.class);
	@Autowired
	private PrivacyService privacyServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index1")
	public String privacyIndex(Privacy privacy,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "privacy/privacyIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddPrivacy(Privacy privacy,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "privacy/addPrivacy";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addPrivacy(Privacy privacy,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addPrivacy {}",privacy);
			if (StringUtils.isEmpty(privacy.getCityId())) {
				User user = MidlandHelper.getCurrentUser(request);
				privacy.setCityId(user.getCityId());
				privacy.setCityName(user.getCityName());
			}
			privacy.setCreateTime(MidlandHelper.getCurrentTime());
			privacyServiceImpl.insertPrivacy(privacy);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addPrivacy异常 {}",privacy,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_privacy")
	public String getPrivacyById(Integer id,Model model) {
		log.info("getPrivacyById  {}",id);
		Privacy result = privacyServiceImpl.selectPrivacyById(id);
		model.addAttribute("item",result);
		return "privacy/updatePrivacy";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deletePrivacyById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deletePrivacyById  {}",id);
			privacyServiceImpl.deletePrivacyById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deletePrivacyById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdatePrivacy(Integer id,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		Privacy result = privacyServiceImpl.selectPrivacyById(id);
		model.addAttribute("item",result);
		return "privacy/updatePrivacy";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updatePrivacyById(Privacy privacy) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updatePrivacyById  {}",privacy);
			privacyServiceImpl.updatePrivacyById(privacy);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updatePrivacyById  {}",privacy,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findPrivacyList(Privacy privacy,Model model, HttpServletRequest request) {
		try {
			log.info("findPrivacyList  {}",privacy);
			MidlandHelper.doPage(request);
			Page<Privacy> result = (Page<Privacy>)privacyServiceImpl.findPrivacyList(privacy);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findPrivacyList  {}",privacy,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "privacy/privacyList";
	}
}
