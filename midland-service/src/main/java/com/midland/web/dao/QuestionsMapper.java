package com.midland.web.dao;

import com.midland.web.model.Questions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByIds(Map<String, Object> map);
    int insertSelective(Questions record);
    Questions selectByPrimaryKey(Integer id);
    List<Questions> questionPage(Questions questions);
    int answerNumCount(Integer id);
    List<Questions> attentionQuestionPage(Questions questions);
    int updateByPrimaryKeySelective(Questions record);
    int thumb_up(@Param("id") int id);
    int page_view(@Param("id") int id);
    int batchUpdate(@Param("questionsList") List<Questions> questionsList);

}