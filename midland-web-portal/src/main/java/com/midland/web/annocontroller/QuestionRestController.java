package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Answer;
import com.midland.web.model.Questions;
import com.midland.web.model.TradeFair;
import com.midland.web.service.AnswerService;
import com.midland.web.service.QuestionsService;
import com.midland.web.service.TradeFairService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/question/")
@RestController
public class QuestionRestController {
    private static Logger logger = LoggerFactory.getLogger(QuestionRestController.class);
    @Autowired
    private QuestionsService questionsServiceImpl;

    /**
     * 问题列表,带搜索功能
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody Questions obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            Page<Questions> list = (Page<Questions>)questionsServiceImpl.questionPage(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(list);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("list",e);
        }
        return result;
    }
    /**
     * 热门问题  按点击率降序排
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("hot/list")
    public Object hotQuestionList(@RequestBody Questions obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            obj.setDescName("click_num");
            Page<Questions> list = (Page<Questions>)questionsServiceImpl.questionPage(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(list);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("list",e);
        }
        return result;
    }
    /**
     * 最新问题  按时间降序排
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("newest/list")
    public Object newestQuestionList(@RequestBody Questions obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            obj.setDescName("question_time");
            Page<Questions> list = (Page<Questions>)questionsServiceImpl.questionPage(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(list);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("list",e);
        }
        return result;
    }
    /**
     * 问题提交接口
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("add")
    public Object addQuestion(@RequestBody Questions obj, HttpServletRequest request){
        Result result=new Result();
        try {
            questionsServiceImpl.insertSelective(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("add",e);
        }
        return result;
    }
}
