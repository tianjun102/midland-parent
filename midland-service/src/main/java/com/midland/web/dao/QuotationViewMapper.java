package com.midland.web.dao;

import com.midland.web.model.QuotationView;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuotationViewMapper {

	List<QuotationView> findQuotationViewList(QuotationView quotationView);

}
