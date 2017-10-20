package com.midland.web.service;

import com.midland.web.model.FeedbackEmail;
import java.util.List;
public interface FeedbackEmailService {

	/**
	 * 主键查询
	 **/
	FeedbackEmail selectFeedbackEmailById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteFeedbackEmailById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateFeedbackEmailById(FeedbackEmail feedbackEmail) throws Exception;

	/**
	 * 插入
	 **/
	void insertFeedbackEmail(FeedbackEmail feedbackEmail) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<FeedbackEmail> findFeedbackEmailList(FeedbackEmail feedbackEmail) throws Exception;

	void batchUpdate(List<FeedbackEmail> feedbackEmailList) throws Exception;
}
