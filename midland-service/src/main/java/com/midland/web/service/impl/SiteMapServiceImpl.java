package com.midland.web.service.impl;

import com.midland.web.dao.SiteMapMapper;
import com.midland.web.model.SiteMap;
import com.midland.web.service.SiteMapService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.midland.web.Contants.Contant.isShow;

@Service
public class SiteMapServiceImpl implements SiteMapService {

    private Logger log = LoggerFactory.getLogger(SiteMapServiceImpl.class);
    @Autowired
    private SiteMapMapper siteMapMapper;

    /**
     * 插入
     **/
    @Override
    public void insertSiteMap(SiteMap siteMap) throws Exception {
        try {
            log.debug("insert {}", siteMap);
            siteMapMapper.insertSiteMap(siteMap);
        } catch (Exception e) {
            log.error("insertSiteMap异常 {}", siteMap, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public SiteMap selectSiteMapById(Integer id) {
        log.debug("selectSiteMapById  {}", id);
        return siteMapMapper.selectSiteMapById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteSiteMapById(Integer id) throws Exception {
        try {
            log.debug("deleteSiteMapById  {}", id);
            int result = siteMapMapper.deleteSiteMapById(id);
            if (result < 1) {
                throw new Exception("deleteSiteMapById失败");
            }
        } catch (Exception e) {
            log.error("deleteSiteMapById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateSiteMapById(SiteMap siteMap) throws Exception {
        try {
            log.debug("updateSiteMapById  {}", siteMap);
            int result = siteMapMapper.updateSiteMapById(siteMap);
            if (result < 1) {
                throw new Exception("updateSiteMapById失败");
            }
        } catch (Exception e) {
            log.error("updateSiteMapById  {}", siteMap, e);
            throw e;
        }
    }

    @Override
    public void updateSiteMapSelectiveById(SiteMap siteMap) throws Exception {
        try {
            log.debug("updateSiteMapSelectiveById  {}", siteMap);

            int result = siteMapMapper.updateSiteMapSelectiveById(siteMap);
            if (result < 1) {
                throw new Exception("updateSiteMapSelectiveById");
            }
        } catch (Exception e) {
            log.error("updateSiteMapSelectiveById  {}", siteMap, e);
            throw e;
        }
    }
  @Override
    public void showAndHide(SiteMap siteMap) throws Exception {
        try {
            log.debug("showAndHide  {}", siteMap);
            int result = siteMapMapper.showAndHide(siteMap);
            if (result < 1) {
                throw new Exception("showAndHide");
            }
        } catch (Exception e) {
            log.error("showAndHide  {}", siteMap, e);
            throw e;
        }
    }
 @Override
    public void updateIsDelete(SiteMap siteMap) throws Exception {
        try {
            log.debug("updateIsDelete  {}", siteMap);
            int result = siteMapMapper.updateIsDelete(siteMap);
            if (result < 1) {
                throw new Exception("updateIsDelete");
            }
        } catch (Exception e) {
            log.error("updateIsDelete  {}", siteMap, e);
            throw e;
        }
    }

    /**
     * 上移
     **/
    @Override
    @Transactional
    public void shiftUp(SiteMap siteMap) throws Exception {
        try {
            log.debug("shiftUp {}", siteMap);
            SiteMap obj = siteMapMapper.shiftUp(siteMap);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = siteMap.getOrderBy();
            obj.setOrderBy(-999999999);
            updateSiteMapSelectiveById(obj);
            siteMap.setOrderBy(nextOrderBy);
            updateSiteMapSelectiveById(siteMap);
            obj.setOrderBy(currOrderBy);
            updateSiteMapSelectiveById(obj);
        } catch (Exception e) {
            log.error("shiftUp {}", siteMap, e);
            throw e;
        }
    }

    /**
     * 下移
     **/
    @Override
    @Transactional
    public void shiftDown(SiteMap siteMap) throws Exception {
        try {
            log.debug("shiftDown {}", siteMap);
            SiteMap obj = siteMapMapper.shiftDown(siteMap);
            if (obj == null){
                return;
            }
            int nextOrderBy = obj.getOrderBy();
            int currOrderBy = siteMap.getOrderBy();
            obj.setOrderBy(-999999999);
            updateSiteMapSelectiveById(obj);
            siteMap.setOrderBy(nextOrderBy);
            updateSiteMapSelectiveById(siteMap);
            obj.setOrderBy(currOrderBy);
            updateSiteMapSelectiveById(obj);
        } catch (Exception e) {
            log.error("shiftDown异常 {}", siteMap, e);
            throw e;
        }
    }
    
    
    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<SiteMap> findSiteMapList(SiteMap siteMap) throws Exception {
        try {
            log.debug("findSiteMapList  {}", siteMap);
            return siteMapMapper.findSiteMapList(siteMap);
        } catch (Exception e) {
            log.error("findSiteMapList  {}", siteMap, e);
            throw e;
        }
    }
 @Override
    public List<SiteMap> findCateGory(SiteMap siteMap) throws Exception {
        try {
            log.debug("findCateGory  {}", siteMap);
            return siteMapMapper.findCateGory(siteMap);
        } catch (Exception e) {
            log.error("findCateGory  {}", siteMap, e);
            throw e;
        }
    }

    @Override
    public int getCountByCateId(int cateId) throws Exception {
        try {
            log.debug("getCountByCateId {}", cateId);
            return siteMapMapper.getCountByCateId(cateId);

        } catch (Exception e) {
            log.error("getCountByCateId {}", cateId, e);
            throw e;
        }
    }

@Override
    public List<SiteMap> findModes(SiteMap siteMap) throws Exception {
        try {
            log.debug("findModes  {}", siteMap);
            return siteMapMapper.findModes(siteMap);
        } catch (Exception e) {
            log.error("findModes  {}", siteMap, e);
            throw e;
        }
    }

    @Override
    public List<SiteMap> findSiteMapByList(List<Integer> cateId) throws Exception {
        try {
            log.debug("findSiteMapByList  {}", cateId);
            return findSiteMapByListAndIsShow(cateId,null);
        } catch (Exception e) {
            log.error("findSiteMapByList  {}", cateId, e);
            throw e;
        }
    }
    @Override
    public List<SiteMap> findSiteMapByListAndIsShow(List<Integer> cateId, Integer isShow) throws Exception {
        try {
            log.debug("findSiteMapByListAndIsShow  {}", cateId);
            return siteMapMapper.findSiteMapByList(cateId,isShow);
        } catch (Exception e) {
            log.error("findSiteMapByListAndIsShow  {}", cateId, e);
            throw e;
        }
    }

    @Override
    public List<SiteMap> findSiteMapByModeId(List<Integer> modeIds) throws Exception {
        try {
            log.debug("findSiteMapByModeId  {}", modeIds);
            return siteMapMapper.findSiteMapByModeId(modeIds);
        } catch (Exception e) {
            log.error("findSiteMapByModeId  {}", modeIds, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<SiteMap> siteMapList) throws Exception {
        try {
            log.debug("batchUpdateSiteMap  {}", siteMapList);
            int result = siteMapMapper.batchUpdate(siteMapList);
            if (result < 1) {
                throw new Exception("batchUpdateSiteMap失败");
            }
        } catch (Exception e) {
            log.error("batchUpdateSiteMap  {}", siteMapList, e);
            throw e;
        }
    }
}
