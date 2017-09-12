package com.midland.web.dao;

import com.midland.web.model.QuotationSecondHand;
import java.util.List;

public interface QuotationSecondHandMapper {

	QuotationSecondHand selectQuotationSecondHandById(Integer quotationSecondHand);

	int deleteQuotationSecondHandById(Integer quotationSecondHand);

	int updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand);

	int insertQuotationSecondHand(QuotationSecondHand quotationSecondHand);
	int insertQuotationSecondHandBatch(List list);

	List<QuotationSecondHand> findQuotationSecondHandList(QuotationSecondHand quotationSecondHand);

}
