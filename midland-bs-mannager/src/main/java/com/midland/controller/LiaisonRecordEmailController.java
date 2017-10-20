package com.midland.controller;

import com.midland.web.model.LiaisonRecordEmail;
import com.midland.web.service.LiaisonRecordEmailService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.slf4j.Logger;

import java.util.ArrayList;
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
@RequestMapping("/liaisonRecordEmail/")
public class LiaisonRecordEmailController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(LiaisonRecordEmailController.class);
	@Autowired
	private LiaisonRecordEmailService liaisonRecordEmailServiceImpl;
@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String liaisonRecordEmailIndex(LiaisonRecordEmail liaisonRecordEmail,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> obj  = JsonMapReader.getMap("liaisonRecord_category");
		model.addAttribute("categorys",obj);
		return "liaisonRecordEmail/liaisonRecordEmailIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLiaisonRecordEmail(LiaisonRecordEmail liaisonRecordEmail,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> obj  = JsonMapReader.getMap("liaisonRecord_category");
		model.addAttribute("categorys",obj);
		return "liaisonRecordEmail/addLiaisonRecordEmail";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLiaisonRecordEmail(LiaisonRecordEmail liaisonRecordEmail) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addLiaisonRecordEmail {}",liaisonRecordEmail);
			liaisonRecordEmailServiceImpl.insertLiaisonRecordEmail(liaisonRecordEmail);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addLiaisonRecordEmail异常 {}",liaisonRecordEmail,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_liaisonRecordEmail")
	public String getLiaisonRecordEmailById(Integer id,Model model) {
		log.info("getLiaisonRecordEmailById  {}",id);
		LiaisonRecordEmail result = liaisonRecordEmailServiceImpl.selectLiaisonRecordEmailById(id);
		model.addAttribute("item",result);
		return "liaisonRecordEmail/updateLiaisonRecordEmail";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteLiaisonRecordEmailById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteLiaisonRecordEmailById  {}",id);
			liaisonRecordEmailServiceImpl.deleteLiaisonRecordEmailById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteLiaisonRecordEmailById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLiaisonRecordEmail(Integer id,Model model) throws Exception {
		LiaisonRecordEmail result = liaisonRecordEmailServiceImpl.selectLiaisonRecordEmailById(id);
		model.addAttribute("item",result);
		settingService.getAllProvinceList(model);
		List<ParamObject> obj  = JsonMapReader.getMap("liaisonRecord_category");
		model.addAttribute("categorys",obj);
		return "liaisonRecordEmail/updateLiaisonRecordEmail";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLiaisonRecordEmailById(LiaisonRecordEmail liaisonRecordEmail) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateLiaisonRecordEmailById  {}",liaisonRecordEmail);
			liaisonRecordEmailServiceImpl.updateLiaisonRecordEmailById(liaisonRecordEmail);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateLiaisonRecordEmailById  {}",liaisonRecordEmail,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLiaisonRecordEmailList(LiaisonRecordEmail liaisonRecordEmail,Model model, HttpServletRequest request) {
		try {
			log.info("findLiaisonRecordEmailList  {}",liaisonRecordEmail);
			MidlandHelper.doPage(request);
			Page<LiaisonRecordEmail> result = (Page<LiaisonRecordEmail>)liaisonRecordEmailServiceImpl.findLiaisonRecordEmailList(liaisonRecordEmail);
			Paginator paginator=result.getPaginator();
			List<ParamObject> obj  = JsonMapReader.getMap("liaisonRecord_category");
			model.addAttribute("categorys",obj);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findLiaisonRecordEmailList  {}",liaisonRecordEmail,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "liaisonRecordEmail/liaisonRecordEmailList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,LiaisonRecordEmail liaisonRecordEmail) throws Exception {
		List<LiaisonRecordEmail> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			LiaisonRecordEmail comment1 = new LiaisonRecordEmail();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(liaisonRecordEmail.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			liaisonRecordEmailServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
