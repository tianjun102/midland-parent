package com.midland.web.dao;

import com.midland.web.model.QuotationSecondHandView;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuotationSecondHandViewMapper {

	List<QuotationSecondHandView> toolTip(QuotationSecondHandView quotationSecondHandView);

}
