package com.midland.web.annocontroller;

import com.midland.web.model.FeedbackEmail;
import com.midland.web.service.FeedbackEmailService;
import com.midland.base.BaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import java.util.HashMap;
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
@RequestMapping("/feedbackEmail/")
public class FeedbackEmailRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(FeedbackEmailRestController.class);
	@Autowired
	private FeedbackEmailService feedbackEmailServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addFeedbackEmail(@RequestBody FeedbackEmail obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addFeedbackEmail {}",obj);
			feedbackEmailServiceImpl.insertFeedbackEmail(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addFeedbackEmail异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getFeedbackEmailById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getFeedbackEmailById  {}",id);
			FeedbackEmail feedbackEmail = feedbackEmailServiceImpl.selectFeedbackEmailById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(feedbackEmail);
		} catch(Exception e) {
			log.error("getFeedbackEmail异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateFeedbackEmailById(@RequestBody FeedbackEmail obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateFeedbackEmailById  {}",obj);
			feedbackEmailServiceImpl.updateFeedbackEmailById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateFeedbackEmailById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findFeedbackEmailList(@RequestBody FeedbackEmail  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findFeedbackEmailList  {}",obj);
			MidlandHelper.doPage(request);
			Page<FeedbackEmail> list = (Page<FeedbackEmail>)feedbackEmailServiceImpl.findFeedbackEmailList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findFeedbackEmailList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
