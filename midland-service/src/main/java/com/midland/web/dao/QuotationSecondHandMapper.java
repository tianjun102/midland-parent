package com.midland.web.dao;

import com.midland.web.model.Quotation;
import com.midland.web.model.QuotationSecondHand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuotationSecondHandMapper {

	QuotationSecondHand selectQuotationSecondHandById(Integer quotationSecondHand);

	QuotationSecondHand selectQuotationSecondHand(QuotationSecondHand quotationSecondHand);

	int deleteQuotationSecondHandById(Integer quotationSecondHand);

	int updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand);

	int insertQuotationSecondHand(QuotationSecondHand quotationSecondHand);
	int insertQuotationSecondHandBatch(List list);

	List<QuotationSecondHand> findQuotationSecondHandList(QuotationSecondHand quotationSecondHand);

	int batchUpdate(@Param("quotationSecondHandList") List<QuotationSecondHand> quotationSecondHandList);

}
