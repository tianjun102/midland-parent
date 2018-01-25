package com.midland.web.service.impl;

import com.midland.web.dao.ErrorPageMapper;
import com.midland.web.model.ErrorPage;
import com.midland.web.service.ErrorPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorPageServiceImpl implements ErrorPageService {

    private Logger log = LoggerFactory.getLogger(ErrorPageServiceImpl.class);
    @Autowired
    private ErrorPageMapper errorPageMapper;

    /**
     * 插入
     **/
    @Override
    public void insertErrorPage(ErrorPage errorPage) throws Exception {
        try {
            log.info("insert {}", errorPage);
            errorPageMapper.insertErrorPage(errorPage);
        } catch (Exception e) {
            log.error("insertErrorPage异常 {}", errorPage, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public ErrorPage selectErrorPageById(Integer id) {
        log.info("selectErrorPageById  {}", id);
        return errorPageMapper.selectErrorPageById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteErrorPageById(Integer id) throws Exception {
        try {
            log.info("deleteErrorPageById  {}", id);
            int result = errorPageMapper.deleteErrorPageById(id);
            if (result < 1) {
                throw new Exception("deleteErrorPageById失败");
            }
        } catch (Exception e) {
            log.error("deleteErrorPageById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateErrorPageById(ErrorPage errorPage) throws Exception {
        try {
            log.info("updateErrorPageById  {}", errorPage);
            int result = errorPageMapper.updateErrorPageById(errorPage);
            if (result < 1) {
                throw new Exception("updateErrorPageById失败");
            }
        } catch (Exception e) {
            log.error("updateErrorPageById  {}", errorPage, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<ErrorPage> findErrorPageList(ErrorPage errorPage) throws Exception {
        try {
            log.info("findErrorPageList  {}", errorPage);
            return errorPageMapper.findErrorPageList(errorPage);
        } catch (Exception e) {
            log.error("findErrorPageList  {}", errorPage, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<ErrorPage> errorPageList) throws Exception {
        try {
            log.debug("updateErrorPageById  {}", errorPageList);
            int result = errorPageMapper.batchUpdate(errorPageList);
            if (result < 1) {
                throw new Exception("updateAppointLogById失败");
            }
        } catch (Exception e) {
            log.error("updateErrorPageById  {}", errorPageList, e);
            throw e;
        }
    }
}
