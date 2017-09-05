package com.midland.web.service;

import com.midland.web.model.Answer;
import java.util.List;
public interface AnswerService {

	/**
	 * 主键查询
	 **/
	Answer selectById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateById(Answer answer) throws Exception;

	/**
	 * 插入
	 **/
	void insertAnswer(Answer answer) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.com.github.pagehelper.PageHelper）
	 **/
	List<Answer> findAnswerList(Answer answer) throws Exception;

}
