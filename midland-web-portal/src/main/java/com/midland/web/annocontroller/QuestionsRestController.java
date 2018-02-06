package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Attention;
import com.midland.web.model.Questions;
import com.midland.web.service.AttentionService;
import com.midland.web.service.QuestionsService;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.service.impl.PublicService;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.midland.web.util.MidlandHelper;

import javax.servlet.http.HttpServletRequest;

@RestController
@SuppressWarnings("all")
@RequestMapping("/questions/")
public class QuestionsRestController extends ServiceBaseFilter {

    private Logger log = LoggerFactory.getLogger(QuestionsRestController.class);
    @Autowired
    private QuestionsService questionsServiceImpl;
    @Autowired
    private PublicService publicServiceImpl;
    @Autowired
    private AttentionService attentionServiceImpl;

    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addQuestions(@RequestBody Questions obj) throws Exception {
        Result result = new Result();
        try {
            if (obj.getUserId() == null) {
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("userId不能为空");
                return result;
            }
            log.info("addQuestions {}", obj);
            obj.setIsDelete(Contant.isNotDelete);
            questionsServiceImpl.insertSelective(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("addQuestions异常 {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getQuestionsById(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer) map.get("id");
            Integer userId = (Integer) map.get("userId");
            if (id==null){
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("id 不能为空");
                return result;
            }
            log.info("getQuestionsById  {}", id);
            Questions questions = questionsServiceImpl.selectByPrimaryKey(id);
            questions.setUserId(userId);
            Questions temp = null;
            if (questions != null) {
                temp = getQuestionIsAttention(questions);
            }
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(temp);
        } catch (Exception e) {
            log.error("getQuestions异常 {}", map, e);
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
        Result result = new Result();
        try {
            log.info("updateQuestionsById  {}", obj);
            questionsServiceImpl.updateByPrimaryKeySelective(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("updateQuestionsById  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public Object findQuestionsList(@RequestBody Questions obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            log.info("findQuestionsList  {}", obj);
            MidlandHelper.doPage(request);
            obj.setIsDelete(Contant.isNotDelete);
            if (obj.getUserId()==null){
                obj.setStatus(Contant.answerAuditPass);
            }

            if (obj.getSort() !=null&&obj.getSort()==1){
                obj.setDescName("click_num");
            }else if (obj.getSort() !=null&&obj.getSort()==0){
                obj.setDescName("question_time");
            }
            Page<Questions> list = (Page<Questions>) questionsServiceImpl.questionPage(obj);
            List<Questions> listTemp = getQuestionIsAttention(list);
            Paginator paginator = list.getPaginator();
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(listTemp);
            result.setPaginator(paginator);
        } catch (Exception e) {
            log.error("findQuestionsList  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }



    @RequestMapping("/myQuestions")
    public Object findMyQuestions(@RequestBody Questions obj, HttpServletRequest request) {
        if (obj.getUserId()==null){
            Result result = new Result();
            result.setCode(ResultStatusUtils.STATUS_CODE_202);
            result.setMsg("userId 不能为空");
            return result;
        }
        return findQuestionsList(obj,request);
    }

    /**
     * 热门问题  按点击率降序排
     *
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("hot/list")
    public Object hotQuestionList(@RequestBody Questions obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            MidlandHelper.doPage(request);
            obj.setDescName("click_num");
            Page<Questions> list = (Page<Questions>) questionsServiceImpl.questionPage(obj);
            List<Questions> listTemp = getQuestionIsAttention(list);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(listTemp);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            log.error("list", e);
        }
        return result;
    }

    private List<Questions> getQuestionIsAttention(Page<Questions> list) throws Exception {
        List<Attention> attentionList = getAttentions(list);
        List<Questions> list1 = new ArrayList<>();
        list.forEach(e -> {
            attentionList.forEach(e1 -> {
                if (e.getUserId() != null && e.getUserId().equals(e1.getUserId()) &&
                        Contant.ATTENTION_QUESTION == e1.getType() &&
                        e.getId().equals(e1.getOtherId())) {
                    e.setIsAttention(true);
                } else {
                    e.setIsAttention(false);
                }
            });
            list1.add(e);
        });
        return list1;
    }

    private Questions getQuestionIsAttention(Questions questions) throws Exception {
        List<Questions> questions1 = new ArrayList<>();
        questions1.add(questions);
        List<Attention> attentionList = getAttentions(questions1);
        List<Questions> list1 = new ArrayList<>();
        attentionList.forEach(e->{
            if (questions.getUserId() != null && questions.getUserId().equals(e.getUserId()) &&
                    Contant.ATTENTION_QUESTION == e.getType() &&
                    questions.getId().equals(e.getOtherId())) {
                questions.setIsAttention(true);
            } else {
                questions.setIsAttention(false);
            }
        });
        return questions;
    }

    private List<Attention> getAttentions(List<Questions> list) throws Exception {
        List<Map> maplist = new ArrayList<>();
        for (Questions temp : list) {
            Map map = new HashMap();
            map.put("userId", temp.getUserId());
            map.put("type", Contant.ATTENTION_QUESTION);
            map.put("otherId", temp.getId());
            maplist.add(map);
        }
        return attentionServiceImpl.findAttentionByList(maplist);
    }

    /**
     * 最新问题  按时间降序排
     *
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping("newest/list")
    public Object newestQuestionList(@RequestBody Questions obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            MidlandHelper.doPage(request);
            obj.setDescName("question_time");
            Page<Questions> list = (Page<Questions>) questionsServiceImpl.questionPage(obj);
            List<Questions> listTemp = getQuestionIsAttention(list);

            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(listTemp);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            log.error("list", e);
        }
        return result;
    }


    /**
     * 点赞
     *
     * @param obj
     * @return
     */
    @RequestMapping("thumb_up")
    public Object questionThumbUp(@RequestBody Questions obj) {
        Result result = new Result();
        try {
            if (obj.getUserId() == null || obj.getId() == null) {
                result.setCode(ResultStatusUtils.STATUS_CODE_400);
                result.setMsg("参数错误");
                return result;
            }
            StringBuffer sb = new StringBuffer();
            sb.append(Contant.QUESTON_THUMB_UP_KEY).append(obj.getId()).append(obj.getUserId());
            String key = sb.toString();
            String V = (String) publicServiceImpl.getV(key);
            if (V == null) {
                publicServiceImpl.setV(key, "1", Contant.timeOutDays, TimeUnit.DAYS);
                questionsServiceImpl.thumb_up(obj.getId());
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("success");
                return result;
            } else {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("7天之内只能点赞一次");
                return result;
            }
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            log.error("thumb_up", e);
        }
        return result;
    }

    /**
     * 浏览量
     *
     * @param obj
     * @return
     */
    @RequestMapping("page_view")
    public Object page_view(@RequestBody Questions obj) {
        Result result = new Result();
        try {
            questionsServiceImpl.page_view(obj.getId());
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("page_view", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 问题关注
     *
     * @param obj userId,otherId
     * @return
     */
    @RequestMapping("attention")
    public Object QuestionAttention(@RequestBody Attention obj) {
        Result result = new Result();
        try {
            obj.setType(Contant.ATTENTION_QUESTION);
            attentionServiceImpl.insertAttention(obj);
            questionsServiceImpl.attentionAdd(obj.getOtherId());
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                log.error("insertAttention,不能重复关注 {}", obj);
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("不能重复关注");
            } else {
                log.error("QuestionAttention", e);
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                result.setMsg("service error");
            }
        }
        return result;
    }

    /**
     * 问题关注
     *
     * @param obj userId,otherId
     * @return
     */
    @RequestMapping("attention/cancel")
    public Object QuestionAttentionCancel(@RequestBody Attention obj) {
        Result result = new Result();
        try {
            obj.setType(Contant.ATTENTION_QUESTION);
            Attention attention = attentionServiceImpl.selectAttentionById(obj.getId());
            questionsServiceImpl.deleteByPrimaryKey(obj.getId());
            questionsServiceImpl.attentionCancel(obj.getOtherId());
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("QuestionAttention", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }


    /**
     * 关注问题列表，
     *
     * @param obj
     * @param request userId
     * @return
     */
    @RequestMapping("attention/list")
    public Object attentionQuestionList(@RequestBody Questions obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            if (obj.getUserId()==null){
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("userId 不能为空");
                return result;
            }
            Attention attention = new Attention();
            attention.setUserId(obj.getUserId());
            attention.setType(Contant.ATTENTION_QUESTION);
            List<Attention> attentionList = attentionServiceImpl.findAttentionList(attention);
            List<Integer> listTemp = new ArrayList<>();
            for (Attention at : attentionList) {
                listTemp.add(at.getOtherId());
            }
            obj.setAttentionList(listTemp);
            MidlandHelper.doPage(request);
            obj.setDescName("question_time");
            Page<Questions> list = (Page<Questions>) questionsServiceImpl.attentionQuestionPage(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(list);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            log.error("list", e);
        }
        return result;
    }


}
