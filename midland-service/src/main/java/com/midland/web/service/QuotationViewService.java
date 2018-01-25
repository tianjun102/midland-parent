package com.midland.web.service;

import com.midland.web.model.QuotationView;

import java.util.List;

public interface QuotationViewService {

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<QuotationView> findQuotationViewList(QuotationView quotationView) throws Exception;

}
