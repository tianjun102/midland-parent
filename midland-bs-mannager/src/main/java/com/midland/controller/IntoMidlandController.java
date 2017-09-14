package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.IntoMidland;
import com.midland.web.service.IntoMidlandService;
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
import java.util.Map;
@Controller
@SuppressWarnings("all")
@RequestMapping("/intoMidland/")
public class IntoMidlandController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(IntoMidlandController.class);
	@Autowired
	private IntoMidlandService intoMidlandServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String intoMidlandIndex(IntoMidland intoMidland,Model model) throws Exception {
		return "intoMidland/intoMidlandIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddIntoMidland(IntoMidland intoMidland,Model model) throws Exception {
		return "intoMidland/addIntoMidland";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addIntoMidland(IntoMidland intoMidland) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addIntoMidland {}",intoMidland);
			intoMidlandServiceImpl.insertIntoMidland(intoMidland);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addIntoMidland异常 {}",intoMidland,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_intoMidland")
	public String getIntoMidlandById(Integer id,Model model) {
		log.info("getIntoMidlandById  {}",id);
		IntoMidland result = intoMidlandServiceImpl.selectIntoMidlandById(id);
		model.addAttribute("item",result);
		return "intoMidland/updateIntoMidland";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteIntoMidlandById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteIntoMidlandById  {}",id);
			intoMidlandServiceImpl.deleteIntoMidlandById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteIntoMidlandById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateIntoMidland(Integer id,Model model) throws Exception {
		IntoMidland result = intoMidlandServiceImpl.selectIntoMidlandById(id);
		model.addAttribute("item",result);
		return "intoMidland/updateIntoMidland";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateIntoMidlandById(IntoMidland intoMidland) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateIntoMidlandById  {}",intoMidland);
			intoMidlandServiceImpl.updateIntoMidlandById(intoMidland);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateIntoMidlandById  {}",intoMidland,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findIntoMidlandList(IntoMidland intoMidland,Model model, HttpServletRequest request) {
		try {
			log.info("findIntoMidlandList  {}",intoMidland);
			MidlandHelper.doPage(request);
			Page<IntoMidland> result = (Page<IntoMidland>)intoMidlandServiceImpl.findIntoMidlandList(intoMidland);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findIntoMidlandList  {}",intoMidland,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "intoMidland/intoMidlandList";
	}
}
