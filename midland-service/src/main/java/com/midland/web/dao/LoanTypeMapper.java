package com.midland.web.dao;

import com.midland.web.model.LoanType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanTypeMapper {

	LoanType selectLoanTypeById(Integer loanType);

	int deleteLoanTypeById(Integer loanType);

	int updateLoanTypeById(LoanType loanType);

	int insertLoanType(LoanType loanType);

	List<LoanType> findLoanTypeList(LoanType loanType);

	int batchUpdate(@Param("loanTypeList") List<LoanType> loanTypeList);

	LoanType findLoanType(LoanType loanType);

}
