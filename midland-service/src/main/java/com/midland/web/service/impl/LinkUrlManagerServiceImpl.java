package com.midland.web.service.impl;

import com.midland.web.dao.LinkUrlManagerMapper;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.service.LinkUrlManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkUrlManagerServiceImpl implements LinkUrlManagerService {

    private Logger log = LoggerFactory.getLogger(LinkUrlManagerServiceImpl.class);
    @Autowired
    private LinkUrlManagerMapper linkUrlManagerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertLinkUrlManager(LinkUrlManager linkUrlManager) throws Exception {
        try {
            log.debug("insert {}", linkUrlManager);
            linkUrlManagerMapper.insertLinkUrlManager(linkUrlManager);
        } catch (Exception e) {
            log.error("insertLinkUrlManager异常 {}", linkUrlManager, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public LinkUrlManager selectById(Integer id) {
        log.debug("selectById  {}", id);
        return linkUrlManagerMapper.selectById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteById(Integer id) throws Exception {
        try {
            log.debug("deleteById  {}", id);
            int result = linkUrlManagerMapper.deleteById(id);
            if (result < 1) {
                throw new Exception("deleteById失败");
            }
        } catch (Exception e) {
            log.error("deleteById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateById(LinkUrlManager linkUrlManager) throws Exception {
        try {
            log.debug("updateById  {}", linkUrlManager);
            int result = linkUrlManagerMapper.updateById(linkUrlManager);
            if (result < 1) {
                throw new Exception("updateById失败");
            }
        } catch (Exception e) {
            log.error("updateById  {}", linkUrlManager, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<LinkUrlManager> findLinkUrlManagerList(LinkUrlManager linkUrlManager) throws Exception {
        try {
            log.debug("findLinkUrlManagerList  {}", linkUrlManager);
            return linkUrlManagerMapper.findLinkUrlManagerList(linkUrlManager);
        } catch (Exception e) {
            log.error("findLinkUrlManagerList  {}", linkUrlManager, e);
            throw e;
        }
    }
    
    
    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(LinkUrlManager category) throws Exception {
        try {
            log.debug("shiftUp {}", category);
            LinkUrlManager obj = linkUrlManagerMapper.shiftUp(category);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = category.getOrderBy();
            obj.setOrderBy(-999999999);
            linkUrlManagerMapper.updateById(obj);
            category.setOrderBy(nextOrderBy);
            linkUrlManagerMapper.updateById(category);
            obj.setOrderBy(currOrderBy);
            linkUrlManagerMapper.updateById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", category, e);
            throw e;
        }
    }
    
    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(LinkUrlManager linkUrlManager) throws Exception {
        try {
            log.debug("shiftDown {}", linkUrlManager);
            LinkUrlManager obj = linkUrlManagerMapper.shiftDown(linkUrlManager);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = linkUrlManager.getOrderBy();
            obj.setOrderBy(-999999999);
            linkUrlManagerMapper.updateById(obj);
            linkUrlManager.setOrderBy(nextOrderBy);
            linkUrlManagerMapper.updateById(linkUrlManager);
            obj.setOrderBy(currOrderBy);
            linkUrlManagerMapper.updateById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", linkUrlManager, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<LinkUrlManager> linkUrlManagerList) throws Exception {
        try {
            log.debug("updateLinkUrlManagerById  {}", linkUrlManagerList);
            int result = linkUrlManagerMapper.batchUpdate(linkUrlManagerList);
            if (result < 1) {
                throw new Exception("updateLinkUrlManagerById失败");
            }
        } catch (Exception e) {
            log.error("updateLinkUrlManagerById  {}", linkUrlManagerList, e);
            throw e;
        }
    }
}
