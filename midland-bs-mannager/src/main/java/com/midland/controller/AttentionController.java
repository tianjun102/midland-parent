package com.midland.controller;

import com.midland.web.model.Attention;
import com.midland.web.service.AttentionService;
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
@RequestMapping("/attention/")
public class AttentionController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(AttentionController.class);
	@Autowired
	private AttentionService attentionServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String attentionIndex(Attention attention,Model model) throws Exception {
		return "attention/attentionIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddAttention(Attention attention,Model model) throws Exception {
		return "attention/addAttention";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addAttention(Attention attention) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addAttention {}",attention);
			attentionServiceImpl.insertAttention(attention);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addAttention异常 {}",attention,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_attention")
	public String getAttentionById(Integer id,Model model) {
		log.info("getAttentionById  {}",id);
		Attention result = attentionServiceImpl.selectAttentionById(id);
		model.addAttribute("item",result);
		return "attention/updateAttention";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteAttentionById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteAttentionById  {}",id);
			attentionServiceImpl.deleteAttentionById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteAttentionById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateAttention(Integer id,Model model) throws Exception {
		Attention result = attentionServiceImpl.selectAttentionById(id);
		model.addAttribute("item",result);
		return "attention/updateAttention";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateAttentionById(Attention attention) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateAttentionById  {}",attention);
			attentionServiceImpl.updateAttentionById(attention);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateAttentionById  {}",attention,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findAttentionList(Attention attention,Model model, HttpServletRequest request) {
		try {
			log.info("findAttentionList  {}",attention);
			MidlandHelper.doPage(request);
			Page<Attention> result = (Page<Attention>)attentionServiceImpl.findAttentionList(attention);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findAttentionList  {}",attention,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "attention/attentionList";
	}
}
