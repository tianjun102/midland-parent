package com.midland.web.service.impl;

import com.midland.web.dao.FooterMapper;
import com.midland.web.model.Footer;
import com.midland.web.service.FooterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FooterServiceImpl implements FooterService {

    private Logger log = LoggerFactory.getLogger(FooterServiceImpl.class);
    @Autowired
    private FooterMapper footerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertFooter(Footer footer) throws Exception {
        try {
            log.debug("insert {}", footer);
            footerMapper.insertFooter(footer);
        } catch (Exception e) {
            log.error("insertFooter异常 {}", footer, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Footer selectFooterById(Integer id) {
        log.debug("selectFooterById  {}", id);
        return footerMapper.selectFooterById(id);
    }

    /**
     * 查询
     **/
    @Override
    public Footer getFooter() {

        return footerMapper.getFooter();
    }

    /**
     * 删除
     **/
    @Override
    public void deleteFooterById(Integer id) throws Exception {
        try {
            log.debug("deleteFooterById  {}", id);
            int result = footerMapper.deleteFooterById(id);
            if (result < 1) {
                throw new Exception("deleteFooterById失败");
            }
        } catch (Exception e) {
            log.error("deleteFooterById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateFooterById(Footer footer) throws Exception {
        try {
            log.debug("updateFooterById  {}", footer);
            int result = footerMapper.updateFooterById(footer);
            if (result < 1) {
                throw new Exception("updateFooterById失败");
            }
        } catch (Exception e) {
            log.error("updateFooterById  {}", footer, e);
            throw e;
        }
    }
 @Override
    public void updateFooterSelectiveById(Footer footer) throws Exception {
        try {
            log.debug("updateFooterById  {}", footer);
            int result = footerMapper.updateFooterSelectiveById(footer);
            if (result < 1) {
                throw new Exception("updateFooterById失败");
            }
        } catch (Exception e) {
            log.error("updateFooterById  {}", footer, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Footer> findFooterList(Footer footer) throws Exception {
        try {
            log.debug("findFooterList  {}", footer);
            return footerMapper.findFooterList(footer);
        } catch (Exception e) {
            log.error("findFooterList  {}", footer, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Footer> footerList) throws Exception {
        try {
            log.debug("updateFooterById  {}", footerList);
            int result = footerMapper.batchUpdate(footerList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateFooterById  {}", footerList, e);
            throw e;
        }
    }
}
