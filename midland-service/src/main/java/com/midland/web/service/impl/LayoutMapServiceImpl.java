package com.midland.web.service.impl;

import com.midland.web.dao.LayoutMapMapper;
import com.midland.web.model.LayoutMap;
import com.midland.web.service.LayoutMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int getMaxOrderBy(Integer hotHandId) {
        LayoutMap layoutMap = new LayoutMap();
        layoutMap.setHotHandId(hotHandId);
        Integer result = layoutMapMapper.getMaxOrderBy(layoutMap);
        return result == null ? 0 : result + 1;
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
}
