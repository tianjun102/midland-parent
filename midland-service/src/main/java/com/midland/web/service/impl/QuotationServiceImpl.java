package com.midland.web.service.impl;

import com.midland.web.dao.QuotationMapper;
import com.midland.web.model.Quotation;
import com.midland.web.service.QuotationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuotationServiceImpl implements QuotationService {

    private Logger log = LoggerFactory.getLogger(QuotationServiceImpl.class);
    @Autowired
    private QuotationMapper quotationMapper;

    /**
     * 插入
     **/
    @Override
    public void insertQuotation(Quotation quotation) throws Exception {
        try {
            log.debug("insert {}", quotation);
            quotationMapper.insertQuotation(quotation);
        } catch (Exception e) {
            log.error("insertQuotation异常 {}", quotation, e);
            throw e;
        }
    }

    /**
     * 批量插入
     **/
    @Override
    public void insertQuotationBatch(List<Quotation> quotation) throws Exception {
        try {
            log.debug("insertQuotationBatch {}", quotation);
            quotationMapper.insertQuotationBatch(quotation);
        } catch (Exception e) {
            log.error("insertQuotationBatch异常 {}", quotation, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Quotation selectQuotationById(Integer id) {
        log.debug("selectQuotationById  {}", id);
        return quotationMapper.selectQuotationById(id);
    }

    @Override
    public List<Map> tooltip(Quotation id) {
        log.debug("selectQuotationById  {}", id);
        return quotationMapper.tooltip(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteQuotationById(Integer id) throws Exception {
        try {
            log.debug("deleteQuotationById  {}", id);
            int result = quotationMapper.deleteQuotationById(id);
            if (result < 1) {
                throw new Exception("deleteQuotationById失败");
            }
        } catch (Exception e) {
            log.error("deleteQuotationById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateQuotationById(Quotation quotation) throws Exception {
        try {
            log.debug("updateQuotationById  {}", quotation);
            int result = quotationMapper.updateQuotationById(quotation);
            if (result < 1) {
                throw new Exception("updateQuotationById失败");
            }
        } catch (Exception e) {
            log.error("updateQuotationById  {}", quotation, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Quotation> findQuotationList(Quotation quotation) throws Exception {
        try {
            log.debug("findQuotationList  {}", quotation);
            return quotationMapper.findQuotationList(quotation);
        } catch (Exception e) {
            log.error("findQuotationList  {}", quotation, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Quotation> quotationList) throws Exception {
        try {
            log.debug("QuotationbatchUpdate  {}", quotationList);
            int result = quotationMapper.batchUpdate(quotationList);
            if (result < 1) {
                throw new Exception("QuotationbatchUpdate失败");
            }
        } catch (Exception e) {
            log.error("QuotationbatchUpdate  {}", quotationList, e);
            throw e;
        }
    }
}
