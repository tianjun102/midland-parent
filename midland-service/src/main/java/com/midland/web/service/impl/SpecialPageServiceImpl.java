package com.midland.web.service.impl;

import com.midland.web.dao.SpecialPageMapper;
import com.midland.web.model.SpecialPage;
import com.midland.web.service.SpecialPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecialPageServiceImpl implements SpecialPageService {

    private Logger log = LoggerFactory.getLogger(SpecialPageServiceImpl.class);
    @Autowired
    private SpecialPageMapper specialPageMapper;

    /**
     * 插入
     **/
    @Override
    public void insertSpecialPage(SpecialPage specialPage) throws Exception {
        try {
            log.debug("insert {}", specialPage);
            specialPageMapper.insertSpecialPage(specialPage);
        } catch (Exception e) {
            log.error("insertSpecialPage异常 {}", specialPage, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public SpecialPage selectSpecialPageById(Integer id) {
        log.debug("selectSpecialPageById  {}", id);
        return specialPageMapper.selectSpecialPageById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteSpecialPageById(Integer id) throws Exception {
        try {
            log.debug("deleteSpecialPageById  {}", id);
            int result = specialPageMapper.deleteSpecialPageById(id);
            if (result < 1) {
                throw new Exception("deleteSpecialPageById失败");
            }
        } catch (Exception e) {
            log.error("deleteSpecialPageById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateSpecialPageById(SpecialPage specialPage) throws Exception {
        try {
            log.debug("updateSpecialPageById  {}", specialPage);
            int result = specialPageMapper.updateSpecialPageById(specialPage);
            if (result < 1) {
                throw new Exception("updateSpecialPageById失败");
            }
        } catch (Exception e) {
            log.error("updateSpecialPageById  {}", specialPage, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<SpecialPage> findSpecialPageList(SpecialPage specialPage) throws Exception {
        try {
            log.debug("findSpecialPageList  {}", specialPage);
            return specialPageMapper.findSpecialPageList(specialPage);
        } catch (Exception e) {
            log.error("findSpecialPageList  {}", specialPage, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<SpecialPage> specialPageList) throws Exception {
        try {
            log.debug("batchUpdateSpecialPage  {}", specialPageList);
            int result = specialPageMapper.batchUpdate(specialPageList);
            if (result < 1) {
                throw new Exception("batchUpdateSpecialPage失败");
            }
        } catch (Exception e) {
            log.error("batchUpdateSpecialPage  {}", specialPageList, e);
            throw e;
        }
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(SpecialPage specialPage) throws Exception {
        try {
            log.debug("shiftUp {}", specialPage);
            SpecialPage obj = specialPageMapper.shiftUp(specialPage);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = specialPage.getOrderBy();
            obj.setOrderBy(-999999999);
            specialPageMapper.updateSpecialPageById(obj);
            specialPage.setOrderBy(nextOrderBy);
            specialPageMapper.updateSpecialPageById(specialPage);
            obj.setOrderBy(currOrderBy);
            specialPageMapper.updateSpecialPageById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", specialPage, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(SpecialPage specialPage) throws Exception {
        try {
            log.debug("shiftDown {}", specialPage);
            SpecialPage obj = specialPageMapper.shiftDown(specialPage);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = specialPage.getOrderBy();
            obj.setOrderBy(-999999999);
            specialPageMapper.updateSpecialPageById(obj);
            specialPage.setOrderBy(nextOrderBy);
            specialPageMapper.updateSpecialPageById(specialPage);
            obj.setOrderBy(currOrderBy);
            specialPageMapper.updateSpecialPageById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", specialPage, e);
            throw e;
        }
    }
    
}
