package com.midland.web.service.impl;

import com.midland.web.dao.QuestionsMapper;
import com.midland.web.model.Questions;
import com.midland.web.service.QuestionsService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约看房Service实现类
 *
 * @author
 * @since 2016年6月10日 下午12:05:03
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {

    private Logger log = LoggerFactory.getLogger(PopularServiceImpl.class);
    @Resource
    private QuestionsMapper questionsMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return questionsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(List<String> idlist) {
        Map<String, Object> map = new HashMap<>();
        map.put("idlist", idlist);
        return questionsMapper.deleteByIds(map);
    }

    @Override
    public int insertSelective(Questions record) {
        record.setQuestionTime(MidlandHelper.getCurrentTime());
        return questionsMapper.insertSelective(record);
    }

    @Override
    public Questions selectByPrimaryKey(Integer id) {
        return questionsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Questions> questionPage(Questions questions) {
        return questionsMapper.questionPage(questions);
    }

    @Override
    public List<Questions> attentionQuestionPage(Questions questions) {
        return questionsMapper.attentionQuestionPage(questions);
    }

    @Override
    public int updateByPrimaryKeySelective(Questions record) {
        return questionsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void answerNumCount(Integer id) {
        int result = questionsMapper.answerNumCount(id);
        if (result < 1) {
            log.error("统计回答次数失败,问题id={}", id);
        }
    }

    @Override
    public int thumb_up(int id) {
        return questionsMapper.thumb_up(id);
    }

    @Override
    public int page_view(int id) {
        return questionsMapper.page_view(id);
    }

    @Override
    public int attentionAdd(int id) {
        return questionsMapper.attention_add(id);
    }

    @Override
    public int attentionCancel(int id) {
        return questionsMapper.attention_cancel(id);
    }

    @Override
    public void batchUpdate(List<Questions> questionsList) throws Exception {
        try {
            log.debug("batchUpdate  {}", questionsList);
            int result = questionsMapper.batchUpdate(questionsList);
            if (result < 1) {
                throw new Exception("batchUpdate失败");
            }
        } catch (Exception e) {
            log.error("batchUpdate  {}", questionsList, e);
            throw e;
        }
    }
}
