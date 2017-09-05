package com.midland.web.dao;

import com.midland.web.model.Answer;
import java.util.List;

public interface AnswerMapper {

	Answer selectById(Integer answer);

	int deleteById(Integer answer);

	int updateById(Answer answer);

	int insertAnswer(Answer answer);

	List<Answer> findAnswerList(Answer answer);

}
