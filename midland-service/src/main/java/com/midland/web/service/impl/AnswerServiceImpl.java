package com.midland.web.service.impl;

import com.midland.web.model.Answer;
import com.midland.web.dao.AnswerMapper;
import com.midland.web.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {

	private Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);
	@Autowired
	private AnswerMapper answerMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertAnswer(Answer answer) throws Exception {
		try {
			log.info("insert {}",answer);
			answerMapper.insertAnswer(answer);
		} catch(Exception e) {
			log.error("insertAnswer异常 {}",answer,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Answer selectById(Integer id) {
		log.info("selectById  {}",id);
		return answerMapper.selectById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteById(Integer id)throws Exception {
		try {
			log.info("deleteById  {}",id);
			int result = answerMapper.deleteById(id);
			if (result < 1) {
				throw new Exception("deleteById失败");
			}
		} catch(Exception e) {
			log.error("deleteById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateById(Answer answer) throws Exception {
		try {
			log.info("updateById  {}",answer);
			int result = answerMapper.updateById(answer);
			if (result < 1) {
				throw new Exception("updateById失败");
			}
		} catch(Exception e) {
			log.error("updateById  {}",answer,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Answer> findAnswerList(Answer answer) throws Exception {
		try {
			log.info("findAnswerList  {}",answer);
			return answerMapper.findAnswerList(answer);
		} catch(Exception e) {
			log.error("findAnswerList  {}",answer,e);
			throw e;
		}
	}
}
