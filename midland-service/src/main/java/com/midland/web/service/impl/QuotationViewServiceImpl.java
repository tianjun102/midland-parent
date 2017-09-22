package com.midland.web.service.impl;

import com.midland.web.model.QuotationView;
import com.midland.web.dao.QuotationViewMapper;
import com.midland.web.service.QuotationViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class QuotationViewServiceImpl implements QuotationViewService {

	private Logger log = LoggerFactory.getLogger(QuotationViewServiceImpl.class);
	@Autowired
	private QuotationViewMapper quotationViewMapper;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<QuotationView> findQuotationViewList(QuotationView quotationView) throws Exception {
		try {
			log.info("findQuotationViewList  {}",quotationView);
			return quotationViewMapper.findQuotationViewList(quotationView);
		} catch(Exception e) {
			log.error("findQuotationViewList  {}",quotationView,e);
			throw e;
		}
	}
}
