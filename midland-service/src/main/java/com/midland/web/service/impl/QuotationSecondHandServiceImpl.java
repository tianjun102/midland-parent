package com.midland.web.service.impl;

import com.midland.web.model.QuotationSecondHand;
import com.midland.web.dao.QuotationSecondHandMapper;
import com.midland.web.service.QuotationSecondHandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class QuotationSecondHandServiceImpl implements QuotationSecondHandService {

	private Logger log = LoggerFactory.getLogger(QuotationSecondHandServiceImpl.class);
	@Autowired
	private QuotationSecondHandMapper quotationSecondHandMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception {
		try {
			log.info("insert {}",quotationSecondHand);
			quotationSecondHandMapper.insertQuotationSecondHand(quotationSecondHand);
		} catch(Exception e) {
			log.error("insertQuotationSecondHand异常 {}",quotationSecondHand,e);
			throw e;
		}
	}
/**
	 * 插入
	 **/
	@Override
	public void insertQuotationSecondHandBatch(List quotationSecondHand) throws Exception {
		try {
			log.info("insert {}",quotationSecondHand);
			quotationSecondHandMapper.insertQuotationSecondHandBatch(quotationSecondHand);
		} catch(Exception e) {
			log.error("insertQuotationSecondHand异常 {}",quotationSecondHand,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public QuotationSecondHand selectQuotationSecondHandById(Integer id) {
		log.info("selectQuotationSecondHandById  {}",id);
		return quotationSecondHandMapper.selectQuotationSecondHandById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteQuotationSecondHandById(Integer id)throws Exception {
		try {
			log.info("deleteQuotationSecondHandById  {}",id);
			int result = quotationSecondHandMapper.deleteQuotationSecondHandById(id);
			if (result < 1) {
				throw new Exception("deleteQuotationSecondHandById失败");
			}
		} catch(Exception e) {
			log.error("deleteQuotationSecondHandById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand) throws Exception {
		try {
			log.info("updateQuotationSecondHandById  {}",quotationSecondHand);
			int result = quotationSecondHandMapper.updateQuotationSecondHandById(quotationSecondHand);
			if (result < 1) {
				throw new Exception("updateQuotationSecondHandById失败");
			}
		} catch(Exception e) {
			log.error("updateQuotationSecondHandById  {}",quotationSecondHand,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<QuotationSecondHand> findQuotationSecondHandList(QuotationSecondHand quotationSecondHand) throws Exception {
		try {
			log.info("findQuotationSecondHandList  {}",quotationSecondHand);
			return quotationSecondHandMapper.findQuotationSecondHandList(quotationSecondHand);
		} catch(Exception e) {
			log.error("findQuotationSecondHandList  {}",quotationSecondHand,e);
			throw e;
		}
	}
}
