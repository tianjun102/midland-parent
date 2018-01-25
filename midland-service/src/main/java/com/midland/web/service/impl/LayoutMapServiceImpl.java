package com.midland.web.service.impl;

import com.midland.web.dao.LayoutMapMapper;
import com.midland.web.model.LayoutMap;
import com.midland.web.service.LayoutMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LayoutMapServiceImpl implements LayoutMapService {

    private Logger log = LoggerFactory.getLogger(LayoutMapServiceImpl.class);
    @Autowired
    private LayoutMapMapper layoutMapMapper;

    /**
     * 插入
     **/
    @Override
    public void insertLayoutMap(LayoutMap layoutMap) throws Exception {
        try {
            log.info("insert {}", layoutMap);
            layoutMapMapper.insertLayoutMap(layoutMap);
        } catch (Exception e) {
            log.error("insertLayoutMap异常 {}", layoutMap, e);
            throw e;
        }
    }



    /**
     * 查询
     **/
    @Override
    public LayoutMap selectLayoutMapById(Integer id) {
        log.info("selectLayoutMapById  {}", id);
        return layoutMapMapper.selectLayoutMapById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteLayoutMapById(Integer id) throws Exception {
        try {
            log.info("deleteLayoutMapById  {}", id);
            int result = layoutMapMapper.deleteLayoutMapById(id);
            if (result < 1) {
                throw new Exception("deleteLayoutMapById失败");
            }
        } catch (Exception e) {
            log.error("deleteLayoutMapById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateLayoutMapById(LayoutMap layoutMap) throws Exception {
        try {
            log.info("updateLayoutMapById  {}", layoutMap);
            int result = layoutMapMapper.updateLayoutMapById(layoutMap);
            if (result < 1) {
                throw new Exception("updateLayoutMapById失败");
            }
        } catch (Exception e) {
            log.error("updateLayoutMapById  {}", layoutMap, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<LayoutMap> findLayoutMapList(LayoutMap layoutMap) throws Exception {
        try {
            log.info("findLayoutMapList  {}", layoutMap);
            return layoutMapMapper.findLayoutMapList(layoutMap);
        } catch (Exception e) {
            log.error("findLayoutMapList  {}", layoutMap, e);
            throw e;
        }
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(LayoutMap layoutMap) throws Exception {
        try {
            log.debug("shiftUp {}", layoutMap);
            LayoutMap obj = layoutMapMapper.shiftUp(layoutMap);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = layoutMap.getOrderBy();
            obj.setOrderBy(-999999999);
            layoutMapMapper.updateLayoutMapById(obj);
            layoutMap.setOrderBy(nextOrderBy);
            layoutMapMapper.updateLayoutMapById(layoutMap);
            obj.setOrderBy(currOrderBy);
            layoutMapMapper.updateLayoutMapById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", layoutMap, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(LayoutMap category) throws Exception {
        try {
            log.debug("shiftDown {}", category);
            LayoutMap obj = layoutMapMapper.shiftDown(category);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = category.getOrderBy();
            obj.setOrderBy(-999999999);
            layoutMapMapper.updateLayoutMapById(obj);
            category.setOrderBy(nextOrderBy);
            layoutMapMapper.updateLayoutMapById(category);
            obj.setOrderBy(currOrderBy);
            layoutMapMapper.updateLayoutMapById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", category, e);
            throw e;
        }
    }
    
}
