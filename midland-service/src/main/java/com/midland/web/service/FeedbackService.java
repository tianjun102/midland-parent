package com.midland.web.service;

import com.midland.web.model.Feedback;
import java.util.List;
public interface FeedbackService {

	/**
	 * 主键查询
	 **/
	Feedback selectFeedbackById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteFeedbackById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateFeedbackById(Feedback feedback) throws Exception;

	/**
	 * 插入
	 **/
	void insertFeedback(Feedback feedback) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Feedback> findFeedbackList(Feedback feedback) throws Exception;

}
