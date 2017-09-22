package com.midland.web.dao;

import com.midland.web.model.QuotationView;
import java.util.List;

public interface QuotationViewMapper {

	List<QuotationView> findQuotationViewList(QuotationView quotationView);

}
