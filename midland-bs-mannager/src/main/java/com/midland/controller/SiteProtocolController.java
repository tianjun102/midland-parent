package com.midland.controller;

import com.midland.web.model.SiteProtocol;
import com.midland.web.service.SettingService;
import com.midland.web.service.SiteProtocolService;
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
@RequestMapping("/siteProtocol/")
public class SiteProtocolController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(SiteProtocolController.class);
	@Autowired
	private SiteProtocolService siteProtocolServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;
	/**
	 * 
	 **/
	@RequestMapping("index")
	public String siteProtocolIndex(SiteProtocol siteProtocol,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "siteProtocol/siteProtocolIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddSiteProtocol(SiteProtocol siteProtocol,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "siteProtocol/addSiteProtocol";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addSiteProtocol(SiteProtocol siteProtocol) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addSiteProtocol {}",siteProtocol);
			siteProtocolServiceImpl.insertSiteProtocol(siteProtocol);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addSiteProtocol异常 {}",siteProtocol,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_siteProtocol")
	public String getSiteProtocolById(Integer id,Model model) {
		log.info("getSiteProtocolById  {}",id);
		SiteProtocol result = siteProtocolServiceImpl.selectSiteProtocolById(id);
		model.addAttribute("item",result);
		return "siteProtocol/updateSiteProtocol";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteSiteProtocolById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteSiteProtocolById  {}",id);
			siteProtocolServiceImpl.deleteSiteProtocolById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteSiteProtocolById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateSiteProtocol(Integer id,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		SiteProtocol result = siteProtocolServiceImpl.selectSiteProtocolById(id);
		model.addAttribute("item",result);
		return "siteProtocol/updateSiteProtocol";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateSiteProtocolById(SiteProtocol siteProtocol) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateSiteProtocolById  {}",siteProtocol);
			siteProtocolServiceImpl.updateSiteProtocolById(siteProtocol);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateSiteProtocolById  {}",siteProtocol,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findSiteProtocolList(SiteProtocol siteProtocol,Model model, HttpServletRequest request) {
		try {
			log.info("findSiteProtocolList  {}",siteProtocol);
			MidlandHelper.doPage(request);
			Page<SiteProtocol> result = (Page<SiteProtocol>)siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findSiteProtocolList  {}",siteProtocol,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "siteProtocol/siteProtocolList";
	}
}
