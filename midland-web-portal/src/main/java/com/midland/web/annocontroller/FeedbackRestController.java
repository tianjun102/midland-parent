package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Feedback;
import com.midland.web.service.FeedbackService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/feedback/")
public class FeedbackRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(FeedbackRestController.class);
	@Autowired
	private FeedbackService feedbackServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addFeedback(@RequestBody Feedback obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addFeedback {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setAddTime(MidlandHelper.getCurrentTime());
			feedbackServiceImpl.insertFeedback(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addFeedback异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getFeedbackById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getFeedbackById  {}",id);
			Feedback feedback = feedbackServiceImpl.selectFeedbackById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(feedback);
		} catch(Exception e) {
			log.error("getFeedback异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateFeedbackById(@RequestBody Feedback obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateFeedbackById  {}",obj);
			feedbackServiceImpl.updateFeedbackById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateFeedbackById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findFeedbackList(@RequestBody Feedback  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findFeedbackList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsDelete(Contant.isNotDelete);
			Page<Feedback> list = (Page<Feedback>)feedbackServiceImpl.findFeedbackList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findFeedbackList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
