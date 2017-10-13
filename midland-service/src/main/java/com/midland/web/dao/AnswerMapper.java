package com.midland.web.dao;

import com.midland.web.model.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerMapper {

	Answer selectAnswerById(Integer answer);

	int deleteAnswerById(Integer answer);

	int updateAnswerById(Answer answer);

	int insertAnswer(Answer answer);

	List<Answer> findAnswerList(Answer answer);

}
