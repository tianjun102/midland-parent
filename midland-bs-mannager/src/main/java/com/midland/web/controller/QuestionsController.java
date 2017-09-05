package com.midland.web.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.midland.web.controller.base.BaseController;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.Answer;
import com.midland.web.model.Questions;
import com.midland.web.model.user.User;
import com.midland.web.service.AnswerService;
import com.midland.web.service.QuestionsService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/1.
 */
@Controller
@RequestMapping("/questions")
public class QuestionsController extends BaseController{
	@Autowired
	private QuestionsService questionsServiceImpl;
	
	@Autowired
	private AnswerService answerServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(QuestionsController.class);
	
	@RequestMapping("/index")
	public String showAppointIndex(HttpServletRequest request) {
		
		return "/questions/qeustionsIndex";
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteByPrimaryKey(String ids) {
		Map map = new HashMap<>();
		map.put("state",-1);
		List<String> idlist = MidlandHelper.getStringRemoveEmpty(ids);
		int result = questionsServiceImpl.deleteByIds(idlist);
		if (result>0){
			map.put("state",0);
		}
		return map;
	}
	
	@RequestMapping("/answer/delete")
	@ResponseBody
	public Object deleteAnswerByPrimaryKey(Integer id) {
		Map map = new HashMap<>();
		try {
			answerServiceImpl.deleteById(id);
			map.put("state",0);
		} catch (Exception e) {
			logger.error("deleteAnswerByPrimaryKey {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addAppointment(Questions record) {
		Map map = new HashMap();
		try {
			questionsServiceImpl.insertSelective(record);
			map.put("state",0);
			return map;
		} catch (Exception e) {
			logger.error("addAppointment {}",record,e);
			map.put("state",-1);
			return map;
		}
	}
	@RequestMapping("/get")
	public Questions selectByPrimaryKey(Integer id) {
		
		
		return questionsServiceImpl.selectByPrimaryKey(id);
	}
	/**
	 * 审核页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toAudit", method = {RequestMethod.GET,RequestMethod.POST})
	public String toAudit(Integer id, Model model, HttpServletRequest request){
		Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
		model.addAttribute("questions",questions);
		return "questions/auditIndex";
	}
	
	
	
	@RequestMapping("/page")
	public String questionPage(Model model, Questions record, String pageNo, String pageSize) {
		if(pageNo==null||pageNo.equals("")){
			pageNo = ContextEnums.PAGENO;
		}
		if(pageSize==null||pageSize.equals("")){
			pageSize = ContextEnums.PAGESIZE;
		}
		PageHelper.startPage(Integer.valueOf(pageNo),Integer.valueOf(pageSize));
		Page<Questions> result = (Page<Questions>) questionsServiceImpl.questionPage(record);
		model.addAttribute("paginator", result.getPaginator());
		model.addAttribute("questions", result.getResult());
		return "questions/questionsList";
	}
	
	
	
	
	@RequestMapping("/to_view")
	public String toUpdateAppointment(int id,Model model) throws Exception {
		Questions questions=questionsServiceImpl.selectByPrimaryKey(id);
		Answer answer = new Answer();
		answer.setQuestionsId(id);
		List<Answer> answerList = answerServiceImpl.findAnswerList(answer);
		model.addAttribute("questions",questions);
		model.addAttribute("answerList",answerList);
		return "questions/updateViewQuestion";
	}
	
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Object updateByPrimaryKeySelective(Questions record,HttpServletRequest request) {
		Map map = new HashMap();
		User user = (User)request.getSession().getAttribute("userInfo");
		record.setAuditor(user.getUsername());
		int result = questionsServiceImpl.updateByPrimaryKeySelective(record);
		if (result >0){
			map.put("state",0);
			return map;
		}
		map.put("state",-1);
		return map;
	}
}
