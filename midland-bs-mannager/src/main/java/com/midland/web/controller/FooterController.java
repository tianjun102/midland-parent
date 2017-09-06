package com.midland.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.controller.base.BaseController;
import com.midland.web.model.Footer;
import com.midland.web.service.FooterService;
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
@RequestMapping("/footer/")
public class FooterController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(FooterController.class);
	@Autowired
	private FooterService footerServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String footerIndex(Footer footer, Model model) throws Exception {
		return "footer/footerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddFooter(Footer footer, Model model) throws Exception {
		return "footer/addFooter";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addFooter(Footer footer) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addFooter {}",footer);
			footerServiceImpl.insertFooter(footer);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addFooter异常 {}",footer,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_footer")
	public String getFooterById(Integer id,Model model) {
		log.info("getFooterById  {}",id);
		Footer result = footerServiceImpl.selectFooterById(id);
		model.addAttribute("item",result);
		return "footer/updateFooter";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteFooterById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteFooterById  {}",id);
			footerServiceImpl.deleteFooterById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteFooterById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateFooter(Integer id,Model model) throws Exception {
		Footer result = footerServiceImpl.selectFooterById(id);
		model.addAttribute("item",result);
		return "footer/updateFooter";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateFooterById(Footer footer) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateFooterById  {}",footer);
			footerServiceImpl.updateFooterById(footer);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateFooterById  {}",footer,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findFooterList(Footer footer, Model model, HttpServletRequest request) {
		try {
			log.info("findFooterList  {}",footer);
			MidlandHelper.doPage(request);
			Page<Footer> result = (Page<Footer>)footerServiceImpl.findFooterList(footer);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findFooterList  {}",footer,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "footer/footerList";
	}
}
