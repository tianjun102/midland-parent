package com.midland.web.controller;

import com.midland.web.model.Comment;
import com.midland.web.model.Information;
import com.midland.web.service.CommentService;
import com.midland.web.controller.base.BaseController;
import com.midland.web.service.InformationService;
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
@RequestMapping("/comment/")
public class CommentController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private CommentService commentServiceImpl;
	@Autowired
	private InformationService informationService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String commentIndex(Comment comment,Model model) throws Exception {
		Information information = new Information();
		information = informationService.selectInformationById(comment.getInformationId());
		model.addAttribute("comment",comment);
		model.addAttribute("information",information);
		return "comment/commentIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddComment(Comment comment,Model model) throws Exception {
		return "comment/addComment";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addComment(Comment comment) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addComment {}",comment);
			commentServiceImpl.insertComment(comment);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addComment异常 {}",comment,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_comment")
	public String getCommentById(Integer id,Model model) {
		log.info("getCommentById  {}",id);
		Comment result = commentServiceImpl.selectCommentById(id);
		model.addAttribute("item",result);
		return "comment/updateComment";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteCommentById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteCommentById  {}",id);
			commentServiceImpl.deleteCommentById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteCommentById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateComment(Integer id,Model model) throws Exception {
		Comment result = commentServiceImpl.selectCommentById(id);
		model.addAttribute("item",result);
		return "comment/updateComment";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateCommentById(Comment comment) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateCommentById  {}",comment);
			commentServiceImpl.updateCommentById(comment);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCommentById  {}",comment,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findCommentList(Comment comment,Model model, HttpServletRequest request) {
		try {
			log.info("findCommentList  {}",comment);
			MidlandHelper.doPage(request);
			Page<Comment> result = (Page<Comment>)commentServiceImpl.findCommentList(comment);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findCommentList  {}",comment,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "comment/commentList";
	}
}
