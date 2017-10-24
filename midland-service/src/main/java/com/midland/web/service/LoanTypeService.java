package com.midland.web.service;

import com.midland.web.model.LoanType;
import java.util.List;
public interface LoanTypeService {

	/**
	 * 主键查询
	 **/
	LoanType selectLoanTypeById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteLoanTypeById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateLoanTypeById(LoanType loanType) throws Exception;

	/**
	 * 插入
	 **/
	void insertLoanType(LoanType loanType) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<LoanType> findLoanTypeList(LoanType loanType) throws Exception;

	void batchUpdate(List<LoanType> loanTypeList) throws Exception;

	LoanType findLoanType(LoanType loanType)  throws Exception;

}
