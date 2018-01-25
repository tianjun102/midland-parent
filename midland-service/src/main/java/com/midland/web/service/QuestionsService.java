package com.midland.web.service;

import com.midland.web.model.Questions;

import java.util.List;

/**
 * @author
 * @since 2016年6月10日 下午12:02:39
 **/
public interface QuestionsService {
    int deleteByPrimaryKey(Integer id);

    int deleteByIds(List<String> ids);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer id);

    List<Questions> questionPage(Questions questions);

    List<Questions> attentionQuestionPage(Questions questions);

    int updateByPrimaryKeySelective(Questions record);

    void answerNumCount(Integer id);

    int thumb_up(int id);

    int page_view(int id);

    int attentionAdd(int id);

    int attentionCancel(int id);

    void batchUpdate(List<Questions> questionsList) throws Exception;
}
