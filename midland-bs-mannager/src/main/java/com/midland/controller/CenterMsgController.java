package com.midland.controller;

import com.midland.web.model.CenterMsg;
import com.midland.web.service.CenterMsgService;
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
/**
 * 消息中心
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/centerMsg/")
public class CenterMsgController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(CenterMsgController.class);
	@Autowired
	private CenterMsgService centerMsgServiceImpl;

	@RequestMapping("index")
	public String centerMsgIndex(CenterMsg centerMsg,Model model) throws Exception {
		return "centerMsg/centerMsgIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddCenterMsg(CenterMsg centerMsg,Model model) throws Exception {
		return "centerMsg/addCenterMsg";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addCenterMsg(CenterMsg centerMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addCenterMsg {}",centerMsg);
			centerMsgServiceImpl.insertCenterMsg(centerMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addCenterMsg异常 {}",centerMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_centerMsg")
	public String getCenterMsgById(Integer id,Model model) {
		log.info("getCenterMsgById  {}",id);
		CenterMsg result = centerMsgServiceImpl.selectCenterMsgById(id);
		model.addAttribute("item",result);
		return "centerMsg/updateCenterMsg";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteCenterMsgById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteCenterMsgById  {}",id);
			centerMsgServiceImpl.deleteCenterMsgById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteCenterMsgById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateCenterMsg(Integer id,Model model) throws Exception {
		CenterMsg result = centerMsgServiceImpl.selectCenterMsgById(id);
		model.addAttribute("item",result);
		return "centerMsg/updateCenterMsg";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateCenterMsgById(CenterMsg centerMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateCenterMsgById  {}",centerMsg);
			centerMsgServiceImpl.updateCenterMsgById(centerMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCenterMsgById  {}",centerMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findCenterMsgList(CenterMsg centerMsg,Model model, HttpServletRequest request) {
		try {
			log.info("findCenterMsgList  {}",centerMsg);
			MidlandHelper.doPage(request);
			Page<CenterMsg> result = (Page<CenterMsg>)centerMsgServiceImpl.findCenterMsgList(centerMsg);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findCenterMsgList  {}",centerMsg,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "centerMsg/centerMsgList";
	}
}
