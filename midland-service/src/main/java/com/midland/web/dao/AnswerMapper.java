package com.midland.web.dao;

import com.midland.web.model.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerMapper {

	Answer selectAnswerById(Integer answer);

	int deleteAnswerById(Integer answer);

	int updateAnswerById(Answer answer);

	int insertAnswer(Answer answer);

	List<Answer> findAnswerList(Answer answer);

	int batchUpdate(@Param("answerList") List<Answer> answerList);
	int thumb_up(@Param("id") int id);
	int thumb_down(@Param("id") int id);

}
