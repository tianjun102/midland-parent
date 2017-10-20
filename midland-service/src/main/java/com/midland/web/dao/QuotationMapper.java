package com.midland.web.dao;

import com.midland.web.model.Questions;
import com.midland.web.model.Quotation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface QuotationMapper {

	Quotation selectQuotationById(Integer quotation);

	int deleteQuotationById(Integer quotation);

	int updateQuotationById(Quotation quotation);

	int insertQuotation(Quotation quotation);
	int insertQuotationBatch(List<Quotation> list);

	List<Quotation> findQuotationList(Quotation quotation);
	List<Map> tooltip(Quotation quotation);

	int batchUpdate(@Param("quotationsList") List<Quotation> quotationList);

}
