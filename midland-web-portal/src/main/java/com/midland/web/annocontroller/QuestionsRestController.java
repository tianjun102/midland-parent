package com.midland.web.annocontroller;

import com.midland.web.model.Questions;
import com.midland.web.service.QuestionsService;
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
@RequestMapping("/questions/")
public class QuestionsRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(QuestionsRestController.class);
	@Autowired
	private QuestionsService questionsServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addQuestions(@RequestBody Questions obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addQuestions {}",obj);
			questionsServiceImpl.insertSelective(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addQuestions异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getQuestionsById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getQuestionsById  {}",id);
			Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(questions);
		} catch(Exception e) {
			log.error("getQuestions异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateQuestionsById(@RequestBody Questions obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateQuestionsById  {}",obj);
			questionsServiceImpl.updateByPrimaryKeySelective(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateQuestionsById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findQuestionsList(@RequestBody Questions  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findQuestionsList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Questions> list = (Page<Questions>)questionsServiceImpl.questionPage(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findQuestionsList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
