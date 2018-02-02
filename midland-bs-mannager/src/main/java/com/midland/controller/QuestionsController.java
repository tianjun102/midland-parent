package com.midland.controller;


import com.github.pagehelper.Page;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.model.Answer;
import com.midland.web.model.Questions;
import com.midland.web.model.user.User;
import com.midland.web.service.AnswerService;
import com.midland.web.service.QuestionsService;
import com.midland.web.service.RedisService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
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

    private Logger log = LoggerFactory.getLogger(QuestionsController.class);
    @Autowired
    private QuestionsService questionsServiceImpl;
    @Autowired
    private SettingService settingService;

    @Autowired
    private AnswerService answerServiceImpl;
    @Autowired
    private RedisService redisServiceImpl;
    @Autowired
    private ApiHelper apiHelper;
    Logger logger = LoggerFactory.getLogger(QuestionsController.class);

    @RequestMapping("/index")
    public String showAppointIndex(HttpServletRequest request, Model model) {
        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        settingService.getAllProvinceList(model);
        List<ParamObject> sources = JsonMapReader.getMap("source");
        model.addAttribute("sources", sources);
        model.addAttribute("auditFlag", redisServiceImpl.getAnswerAuditFlag());
        List<ParamObject> obj = JsonMapReader.getMap("is_delete");
        model.addAttribute("isDeletes", obj);
        return "/questions/qeustionsIndex";
    }

    @RequestMapping("/audit_answer")
    public String auditQuestion(HttpServletRequest request, Integer id, Model model) {
        Answer ans = answerServiceImpl.selectAnswerById(id);
        model.addAttribute("answer", ans);
        return "/questions/auditAnswer";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteByPrimaryKey(String ids) {
        Map map = new HashMap<>();
        map.put("state", -1);
        List<String> idlist = MidlandHelper.getStringRemoveEmpty(ids);
        int result = questionsServiceImpl.deleteByIds(idlist);
        if (result > 0) {
            map.put("state", 0);
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
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("deleteAnswerByPrimaryKey {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Object addAppointment(Questions record) {
        Map map = new HashMap();
        try {
            questionsServiceImpl.insertSelective(record);
            map.put("state", 0);
            return map;
        } catch (Exception e) {
            logger.error("addAppointment {}", record, e);
            map.put("state", -1);
            return map;
        }
    }

    @RequestMapping("/get")
    public Questions selectByPrimaryKey(Integer id) {


        return questionsServiceImpl.selectByPrimaryKey(id);
    }

    @RequestMapping("/open")
    @ResponseBody
    public Object openAudit(Integer id) {
        Map map = new HashMap();
        try {
            redisServiceImpl.setAnswerAuditFlag(id);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("openAudit ", e);
            map.put("state", 0);

        }
        return map;
    }


    /**
     * 审核页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/toAudit", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAudit(Integer id, Model model, HttpServletRequest request) {
        Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
        model.addAttribute("questions", questions);
        return "questions/auditIndex";
    }


    @RequestMapping("/page")
    public String questionPage(Model model, Questions record, HttpServletRequest request) {
        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        if (!Contant.isSuper.equals(user.getIsSuper())) {//不是超级管理员，只能看属性城市的相关信息
            record.setCityId(user.getCityId());
        }
        MidlandHelper.doPage(request);
        Page<Questions> result = (Page<Questions>) questionsServiceImpl.questionPage(record);
        model.addAttribute("paginator", result.getPaginator());
        model.addAttribute("questions", result.getResult());
        return "questions/questionsList";
    }


    @RequestMapping("/to_view")
    public String toUpdateAnswer(int id, Model model) throws Exception {
        Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
        Answer answer = new Answer();
        answer.setQuestionsId(id);
        answer.setIsDelete(Contant.isNotDelete);
        List<Answer> answerList = answerServiceImpl.findAnswerList(answer);
        List<ParamObject> paramObject = JsonMapReader.getMap("audit_status");
        model.addAttribute("auditStatusList", paramObject);
        model.addAttribute("questions", questions);
        model.addAttribute("answerList", answerList);
        return "questions/updateViewQuestion";
    }

    @RequestMapping("/to_repeat")
    public String toRepeat(int id, Model model) throws Exception {
        Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
        Answer answer = new Answer();
        answer.setQuestionsId(id);
        answer.setIsDelete(Contant.isNotDelete);
        List<Answer> answerList = answerServiceImpl.findAnswerList(answer);
        model.addAttribute("questions", questions);
        model.addAttribute("answerList", answerList);
        return "questions/repeat";
    }


    @RequestMapping("/update")
    @ResponseBody
    public Object updateByPrimaryKeySelective(Questions record, HttpServletRequest request) {
        Map map = new HashMap();
        User user = (User) request.getSession().getAttribute("userInfo");
        record.setAuditor(user.getUsername());
        int result = questionsServiceImpl.updateByPrimaryKeySelective(record);
        if (result > 0) {
            map.put("state", 0);
            return map;
        }
        map.put("state", -1);
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
        if (redisServiceImpl.getAnswerAuditFlag() == Contant.answerAuditClose) {
            //关闭审核功能，回复的审核状态就默认为“审核通过”
            answer.setAuditStatus(1);
        }
        try {
            answerServiceImpl.insertAnswer(answer);
            questionsServiceImpl.answerNumCount(answer.getQuestionsId());
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("repeat : {}", answer, e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("/updateAnswer")
    @ResponseBody
    public Object updateAnswer(Answer answer, HttpServletRequest request) {
        Map map = new HashMap();
        try {
            answerServiceImpl.updateAnswerById(answer);
            Answer answer1 = answerServiceImpl.selectAnswerById(answer.getId());
//			if (answer.getAuditStatus() == 3){
//				//审核不通过，发送短信通知经纪人
//				List list = new ArrayList();
//				String remark = request.getParameter("auditRemark");
//				list.add(remark);
//				list.add("dfef");
//				list.add("qqqq");
//				apiHelper.smsSender(answer1.getAnswerPhone(),465465,list);
//			}
            map.put("state", 0);

        } catch (Exception e) {
            logger.error("updateAnswer : {}", answer, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, Questions questions) throws Exception {
        List<Questions> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Questions comment1 = new Questions();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(questions.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateAnswerById  {}", commentList);
            questionsServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateAnswerById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}
