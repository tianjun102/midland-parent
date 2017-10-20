package com.midland.web.service.impl;

import com.midland.web.model.FeedbackEmail;
import com.midland.web.dao.FeedbackEmailMapper;
import com.midland.web.service.FeedbackEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FeedbackEmailServiceImpl implements FeedbackEmailService {

	private Logger log = LoggerFactory.getLogger(FeedbackEmailServiceImpl.class);
	@Autowired
	private FeedbackEmailMapper feedbackEmailMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertFeedbackEmail(FeedbackEmail feedbackEmail) throws Exception {
		try {
			log.debug("insert {}",feedbackEmail);
			feedbackEmailMapper.insertFeedbackEmail(feedbackEmail);
		} catch(Exception e) {
			log.error("insertFeedbackEmail异常 {}",feedbackEmail,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public FeedbackEmail selectFeedbackEmailById(Integer id) {
		log.debug("selectFeedbackEmailById  {}",id);
		return feedbackEmailMapper.selectFeedbackEmailById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteFeedbackEmailById(Integer id)throws Exception {
		try {
			log.debug("deleteFeedbackEmailById  {}",id);
			int result = feedbackEmailMapper.deleteFeedbackEmailById(id);
			if (result < 1) {
				throw new Exception("deleteFeedbackEmailById失败");
			}
		} catch(Exception e) {
			log.error("deleteFeedbackEmailById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateFeedbackEmailById(FeedbackEmail feedbackEmail) throws Exception {
		try {
			log.debug("updateFeedbackEmailById  {}",feedbackEmail);
			int result = feedbackEmailMapper.updateFeedbackEmailById(feedbackEmail);
			if (result < 1) {
				throw new Exception("updateFeedbackEmailById失败");
			}
		} catch(Exception e) {
			log.error("updateFeedbackEmailById  {}",feedbackEmail,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<FeedbackEmail> findFeedbackEmailList(FeedbackEmail feedbackEmail) throws Exception {
		try {
			log.debug("findFeedbackEmailList  {}",feedbackEmail);
			return feedbackEmailMapper.findFeedbackEmailList(feedbackEmail);
		} catch(Exception e) {
			log.error("findFeedbackEmailList  {}",feedbackEmail,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<FeedbackEmail> feedbackEmailList) throws Exception {
		try {
			log.debug("updateFeedbackEmailById  {}",feedbackEmailList);
			int result = feedbackEmailMapper.batchUpdate(feedbackEmailList);
			if (result < 1) {
				throw new Exception("updateFeedbackEmailById失败");
			}
		} catch(Exception e) {
			log.error("updateFeedbackEmailById  {}",feedbackEmailList,e);
			throw e;
		}
	}
}
