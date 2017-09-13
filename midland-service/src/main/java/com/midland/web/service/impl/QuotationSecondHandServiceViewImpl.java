package com.midland.web.service.impl;

import com.midland.web.dao.QuotationSecondHandViewMapper;
import com.midland.web.model.QuotationSecondHandView;
import com.midland.web.service.QuotationSecondHandViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationSecondHandServiceViewImpl implements QuotationSecondHandViewService {

	private Logger log = LoggerFactory.getLogger(QuotationSecondHandServiceViewImpl.class);
	@Autowired
	private QuotationSecondHandViewMapper quotationSecondHandViewMapper;
	
	@Override
	public List toolTip(QuotationSecondHandView quotationSecondHandView){
		return quotationSecondHandViewMapper.toolTip(quotationSecondHandView);
	}
	
}
