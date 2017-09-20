package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.EliteVip;
import com.midland.web.service.EliteVipService;
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
@RequestMapping("/eliteVip/")
public class EliteVipController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(EliteVipController.class);
	@Autowired
	private EliteVipService eliteVipServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String eliteVipIndex(EliteVip eliteVip,Model model) throws Exception {
		return "eliteVip/eliteVipIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddEliteVip(EliteVip eliteVip,Model model) throws Exception {
		return "eliteVip/addEliteVip";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addEliteVip(EliteVip eliteVip) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addEliteVip {}",eliteVip);
			eliteVipServiceImpl.insertEliteVip(eliteVip);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addEliteVip异常 {}",eliteVip,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_eliteVip")
	public String getEliteVipById(Integer id,Model model) {
		log.info("getEliteVipById  {}",id);
		EliteVip result = eliteVipServiceImpl.selectEliteVipById(id);
		model.addAttribute("item",result);
		return "eliteVip/updateEliteVip";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteEliteVipById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteEliteVipById  {}",id);
			eliteVipServiceImpl.deleteEliteVipById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteEliteVipById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateEliteVip(Integer id,Model model) throws Exception {
		EliteVip result = eliteVipServiceImpl.selectEliteVipById(id);
		model.addAttribute("item",result);
		return "eliteVip/updateEliteVip";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateEliteVipById(EliteVip eliteVip) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateEliteVipById  {}",eliteVip);
			eliteVipServiceImpl.updateEliteVipById(eliteVip);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateEliteVipById  {}",eliteVip,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findEliteVipList(EliteVip eliteVip,Model model, HttpServletRequest request) {
		try {
			log.info("findEliteVipList  {}",eliteVip);
			MidlandHelper.doPage(request);
			Page<EliteVip> result = (Page<EliteVip>)eliteVipServiceImpl.findEliteVipList(eliteVip);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findEliteVipList  {}",eliteVip,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "eliteVip/eliteVipList";
	}
}