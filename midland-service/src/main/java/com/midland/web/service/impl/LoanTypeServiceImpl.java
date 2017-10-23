package com.midland.web.service.impl;

import com.midland.web.model.LoanType;
import com.midland.web.dao.LoanTypeMapper;
import com.midland.web.service.LoanTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LoanTypeServiceImpl implements LoanTypeService {

	private Logger log = LoggerFactory.getLogger(LoanTypeServiceImpl.class);
	@Autowired
	private LoanTypeMapper loanTypeMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertLoanType(LoanType loanType) throws Exception {
		try {
			log.info("insert {}",loanType);
			loanTypeMapper.insertLoanType(loanType);
		} catch(Exception e) {
			log.error("insertLoanType异常 {}",loanType,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public LoanType selectLoanTypeById(Integer id) {
		log.info("selectLoanTypeById  {}",id);
		return loanTypeMapper.selectLoanTypeById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteLoanTypeById(Integer id)throws Exception {
		try {
			log.info("deleteLoanTypeById  {}",id);
			int result = loanTypeMapper.deleteLoanTypeById(id);
			if (result < 1) {
				throw new Exception("deleteLoanTypeById失败");
			}
		} catch(Exception e) {
			log.error("deleteLoanTypeById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateLoanTypeById(LoanType loanType) throws Exception {
		try {
			log.info("updateLoanTypeById  {}",loanType);
			int result = loanTypeMapper.updateLoanTypeById(loanType);
			if (result < 1) {
				throw new Exception("updateLoanTypeById失败");
			}
		} catch(Exception e) {
			log.error("updateLoanTypeById  {}",loanType,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<LoanType> findLoanTypeList(LoanType loanType) throws Exception {
		try {
			log.info("findLoanTypeList  {}",loanType);
			return loanTypeMapper.findLoanTypeList(loanType);
		} catch(Exception e) {
			log.error("findLoanTypeList  {}",loanType,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<LoanType> loanTypeList) throws Exception {
		try {
			log.debug("loanTypeList  {}",loanTypeList);
			int result = loanTypeMapper.batchUpdate(loanTypeList);
			if (result < 1) {
				throw new Exception("updateLinkUrlManagerById失败");
			}
		} catch(Exception e) {
			log.error("updateLinkUrlManagerById  {}",loanTypeList,e);
			throw e;
		}
	}
}
