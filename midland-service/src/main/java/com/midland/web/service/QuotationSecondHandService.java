package com.midland.web.service;

import com.midland.web.model.QuotationSecondHand;
import java.util.List;
public interface QuotationSecondHandService {
	
	void insertQuotationSecondHandBatch(List quotationSecondHand) throws Exception;
	
	/**
	 * 主键查询
	 **/
	QuotationSecondHand selectQuotationSecondHandById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteQuotationSecondHandById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand) throws Exception;

	/**
	 * 插入
	 **/
	void insertQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<QuotationSecondHand> findQuotationSecondHandList(QuotationSecondHand quotationSecondHand) throws Exception;

    List<QuotationSecondHand> findQuotationSecondHandListTemp(QuotationSecondHand quotationSecondHand, List<String> list) throws Exception;

    void batchUpdate(List<QuotationSecondHand> quotationSecondHandList) throws Exception;
    QuotationSecondHand findQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception;
}
