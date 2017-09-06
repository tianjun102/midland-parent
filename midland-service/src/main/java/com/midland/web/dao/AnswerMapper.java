package com.midland.web.dao;

import com.midland.web.model.Answer;
import java.util.List;

public interface AnswerMapper {

	Answer selectAnswerById(Integer answer);

	int deleteAnswerById(Integer answer);

	int updateAnswerById(Answer answer);

	int insertAnswer(Answer answer);

	List<Answer> findAnswerList(Answer answer);

}
