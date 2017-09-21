package com.midland.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.Answer;
import com.midland.web.model.Questions;
import com.midland.web.model.user.User;
import com.midland.web.service.AnswerService;
import com.midland.web.service.QuestionsService;
import com.midland.web.service.RedisService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/1.
 */
@Controller
@RequestMapping("/questions")
public class QuestionsController extends BaseFilter {
	@Autowired
	private QuestionsService questionsServiceImpl;
	
	@Autowired
	private AnswerService answerServiceImpl;
	@Autowired
	private RedisService redisServiceImpl;
	@Autowired
	private ApiHelper apiHelper;
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
			Answer answer = new Answer();
			answer.setId(id);
			answer.setIsDelete(1);
			answerServiceImpl.updateAnswerById(answer);
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
	public String toUpdateAnswer(int id,Model model) throws Exception {
		Questions questions=questionsServiceImpl.selectByPrimaryKey(id);
		Answer answer = new Answer();
		answer.setQuestionsId(id);
		List<Answer> answerList = answerServiceImpl.findAnswerList(answer);
		model.addAttribute("questions",questions);
		model.addAttribute("answerList",answerList);
		return "questions/updateViewQuestion";
	}
	
	@RequestMapping("/to_repeat")
	public String toRepeat(int id,Model model) throws Exception {
		Questions questions=questionsServiceImpl.selectByPrimaryKey(id);
		Answer answer = new Answer();
		answer.setQuestionsId(id);
		List<Answer> answerList = answerServiceImpl.findAnswerList(answer);
		model.addAttribute("questions",questions);
		model.addAttribute("answerList",answerList);
		return "questions/repeat";
	}
	
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Object updateByPrimaryKeySelective(Questions record, HttpServletRequest request) {
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
	
	
	@RequestMapping("/repeat")
	@ResponseBody
	public Object repeat(Answer answer, HttpServletRequest request) {
		Map map = new HashMap();
		answer.setAnswerTime(MidlandHelper.getCurrentTime());
		User user = MidlandHelper.getCurrentUser(request);
		answer.setAnswerName(user.getUserCnName());
		answer.setAnswerPhone(user.getPhone());
		if (redisServiceImpl.getAnswerAuditFlag()== Contant.answerAuditClose){
			//关闭审核功能，回复的审核状态就默认为“审核通过”
			answer.setAuditStatus(1);
		}
		try {
			answerServiceImpl.insertAnswer(answer);
			map.put("state",0);
		} catch (Exception e) {
			logger.error("repeat : {}",answer,e);
			map.put("state",-1);
		}
		return map;
	}
	@RequestMapping("/updateAnswer")
	@ResponseBody
	public Object updateAnswer(Answer answer,HttpServletRequest request){
		Map map = new HashMap();
		try {
			answerServiceImpl.updateAnswerById(answer);
			Answer answer1= answerServiceImpl.selectAnswerById(answer.getId());
			if (answer.getAuditStatus() == 2){
				//审核不通过，发送短信通知经纪人
				List list = new ArrayList();
				String remark = request.getParameter("auditRemark");
				list.add(remark);
				list.add("dfef");
				list.add("qqqq");
				SmsModel smsModel = new SmsModel(answer1.getAnswerPhone(),"2029157",list);
				apiHelper.smsSender("updateAnswer",smsModel);
			}
			map.put("state",0);
			
		} catch (Exception e) {
			logger.error("updateAnswer : {}",answer,e);
			map.put("state",-1);
		}
		return map;
	}
	
}
