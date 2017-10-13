package com.midland.web.service.impl;

import com.midland.web.model.Feedback;
import com.midland.web.dao.FeedbackMapper;
import com.midland.web.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FeedbackServiceImpl implements FeedbackService {

	private Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);
	@Autowired
	private FeedbackMapper feedbackMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertFeedback(Feedback feedback) throws Exception {
		try {
			log.debug("insert {}",feedback);
			feedbackMapper.insertFeedback(feedback);
		} catch(Exception e) {
			log.error("insertFeedback异常 {}",feedback,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Feedback selectFeedbackById(Integer id) {
		log.debug("selectFeedbackById  {}",id);
		return feedbackMapper.selectFeedbackById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteFeedbackById(Integer id)throws Exception {
		try {
			log.debug("deleteFeedbackById  {}",id);
			int result = feedbackMapper.deleteFeedbackById(id);
			if (result < 1) {
				throw new Exception("deleteFeedbackById失败");
			}
		} catch(Exception e) {
			log.error("deleteFeedbackById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateFeedbackById(Feedback feedback) throws Exception {
		try {
			log.debug("updateFeedbackById  {}",feedback);
			int result = feedbackMapper.updateFeedbackById(feedback);
			if (result < 1) {
				throw new Exception("updateFeedbackById失败");
			}
		} catch(Exception e) {
			log.error("updateFeedbackById  {}",feedback,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Feedback> findFeedbackList(Feedback feedback) throws Exception {
		try {
			log.debug("findFeedbackList  {}",feedback);
			return feedbackMapper.findFeedbackList(feedback);
		} catch(Exception e) {
			log.error("findFeedbackList  {}",feedback,e);
			throw e;
		}
	}
}
