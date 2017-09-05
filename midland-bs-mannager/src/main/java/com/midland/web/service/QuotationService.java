package com.midland.web.service;

import com.midland.web.model.Quotation;
import java.util.List;
public interface QuotationService {

	/**
	 * 主键查询
	 **/
	Quotation selectQuotationById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteQuotationById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateQuotationById(Quotation quotation) throws Exception;

	/**
	 * 插入
	 **/
	void insertQuotation(Quotation quotation) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Quotation> findQuotationList(Quotation quotation) throws Exception;

}
