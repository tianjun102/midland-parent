package com.midland.web.service.impl;

import com.midland.web.dao.HotSearchMapper;
import com.midland.web.model.HotSearch;
import com.midland.web.service.HotSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotSearchServiceImpl implements HotSearchService {

    private Logger log = LoggerFactory.getLogger(HotSearchServiceImpl.class);
    @Autowired
    private HotSearchMapper hotSearchMapper;

    /**
     * 插入
     **/
    @Override
    public void insertHotSearch(HotSearch hotSearch) throws Exception {
        try {
            log.debug("insert {}", hotSearch);
            hotSearchMapper.insertHotSearch(hotSearch);
        } catch (Exception e) {
            log.error("insertHotSearch异常 {}", hotSearch, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public HotSearch selectHotSearchById(Integer id) {
        log.debug("selectHotSearchById  {}", id);
        return hotSearchMapper.selectHotSearchById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteHotSearchById(Integer id) throws Exception {
        try {
            log.debug("deleteHotSearchById  {}", id);
            int result = hotSearchMapper.deleteHotSearchById(id);
            if (result < 1) {
                throw new Exception("deleteHotSearchById失败");
            }
        } catch (Exception e) {
            log.error("deleteHotSearchById  {}", id, e);
            throw e;
        }
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(HotSearch hotSearch) throws Exception {
        try {
            log.debug("shiftUp {}", hotSearch);
            HotSearch obj = hotSearchMapper.shiftUp(hotSearch);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = hotSearch.getOrderBy();
            obj.setOrderBy(-999999999);
            hotSearchMapper.updateHotSearchById(obj);
            hotSearch.setOrderBy(nextOrderBy);
            hotSearchMapper.updateHotSearchById(hotSearch);
            obj.setOrderBy(currOrderBy);
            hotSearchMapper.updateHotSearchById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", hotSearch, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(HotSearch hotSearch) throws Exception {
        try {
            log.debug("shiftDown {}", hotSearch);
            HotSearch obj = hotSearchMapper.shiftDown(hotSearch);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = hotSearch.getOrderBy();
            obj.setOrderBy(-999999999);
            hotSearchMapper.updateHotSearchById(obj);
            hotSearch.setOrderBy(nextOrderBy);
            hotSearchMapper.updateHotSearchById(hotSearch);
            obj.setOrderBy(currOrderBy);
            hotSearchMapper.updateHotSearchById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", hotSearch, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateHotSearchById(HotSearch hotSearch) throws Exception {
        try {
            log.debug("updateHotSearchById  {}", hotSearch);
            int result = hotSearchMapper.updateHotSearchById(hotSearch);
            if (result < 1) {
                throw new Exception("updateHotSearchById失败");
            }
        } catch (Exception e) {
            log.error("updateHotSearchById  {}", hotSearch, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<HotSearch> findHotSearchList(HotSearch hotSearch) throws Exception {
        try {
            log.debug("findHotSearchList  {}", hotSearch);
            return hotSearchMapper.findHotSearchList(hotSearch);
        } catch (Exception e) {
            log.error("findHotSearchList  {}", hotSearch, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<HotSearch> hotSearchList) throws Exception {
        try {
            log.debug("updateHotSearchById  {}", hotSearchList);
            int result = hotSearchMapper.batchUpdate(hotSearchList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateHotSearchById  {}", hotSearchList, e);
            throw e;
        }
    }
}
