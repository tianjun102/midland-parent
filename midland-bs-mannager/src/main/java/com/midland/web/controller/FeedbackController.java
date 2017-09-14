package com.midland.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Feedback;
import com.midland.web.model.user.User;
import com.midland.web.service.FeedbackService;
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
@RequestMapping("/feedback/")
public class FeedbackController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(FeedbackController.class);
	@Autowired
	private FeedbackService feedbackServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String feedbackIndex(Feedback feedback, Model model) throws Exception {
		return "feedback/feedbackIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddFeedback(Feedback feedback, Model model) throws Exception {
		return "feedback/addFeedback";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addFeedback(Feedback feedback, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			
			feedback.setAddTime(MidlandHelper.getCurrentTime());
			log.info("addFeedback {}",feedback);
			feedbackServiceImpl.insertFeedback(feedback);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addFeedback异常 {}",feedback,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_feedback")
	public String getFeedbackById(Integer id,Model model) {
		log.info("getFeedbackById  {}",id);
		Feedback result = feedbackServiceImpl.selectFeedbackById(id);
		model.addAttribute("item",result);
		return "feedback/updateFeedback";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteFeedbackById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteFeedbackById  {}",id);
			feedbackServiceImpl.deleteFeedbackById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteFeedbackById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateFeedback(Integer id,Model model,HttpServletRequest request) throws Exception {
		Feedback result = feedbackServiceImpl.selectFeedbackById(id);
		model.addAttribute("item",result);
		return "feedback/updateFeedback";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateFeedbackById(Feedback feedback, HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			User user = MidlandHelper.getCurrentUser(request);
			feedback.setOperatorId(user.getId());
			feedback.setOperatorName(user.getUserCnName());
			log.info("updateFeedbackById  {}",feedback);
			feedbackServiceImpl.updateFeedbackById(feedback);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateFeedbackById  {}",feedback,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findFeedbackList(Feedback feedback, Model model, HttpServletRequest request) {
		try {
			log.info("findFeedbackList  {}",feedback);
			MidlandHelper.doPage(request);
			Page<Feedback> result = (Page<Feedback>)feedbackServiceImpl.findFeedbackList(feedback);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findFeedbackList  {}",feedback,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "feedback/feedbackList";
	}
}
