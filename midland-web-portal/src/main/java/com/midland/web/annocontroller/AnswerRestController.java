package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Answer;
import com.midland.web.model.Questions;
import com.midland.web.service.AnswerService;
import com.midland.web.service.QuestionsService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/answer/")
@RestController
public class AnswerRestController {
    private static Logger logger = LoggerFactory.getLogger(AnswerRestController.class);

    @Autowired
    private AnswerService answerServiceImpl;

    /**
     * 回答列表接口
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("list")
    public Object answerList(@RequestBody Answer obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            Page<Answer> list = (Page<Answer>)answerServiceImpl.findAnswerList(obj);
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
     * 回答提交接口
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("add")
    public Object addAnswer(@RequestBody Answer obj, HttpServletRequest request){
        Result result=new Result();
        try {
            answerServiceImpl.insertAnswer(obj);
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
