package com.midland.web.service.impl;

import com.midland.web.dao.EliteVipMapper;
import com.midland.web.model.EliteVip;
import com.midland.web.service.EliteVipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EliteVipServiceImpl implements EliteVipService {

    private Logger log = LoggerFactory.getLogger(EliteVipServiceImpl.class);
    @Autowired
    private EliteVipMapper eliteVipMapper;

    /**
     * 插入
     **/
    @Override
    public void insertEliteVip(EliteVip eliteVip) throws Exception {
        try {
            log.debug("insert {}", eliteVip);
            eliteVipMapper.insertEliteVip(eliteVip);
        } catch (Exception e) {
            log.error("insertEliteVip异常 {}", eliteVip, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public EliteVip selectEliteVipById(Integer id) {
        log.debug("selectEliteVipById  {}", id);
        return eliteVipMapper.selectEliteVipById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteEliteVipById(Integer id) throws Exception {
        try {
            log.debug("deleteEliteVipById  {}", id);
            int result = eliteVipMapper.deleteEliteVipById(id);
            if (result < 1) {
                throw new Exception("deleteEliteVipById失败");
            }
        } catch (Exception e) {
            log.error("deleteEliteVipById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateEliteVipById(EliteVip eliteVip) throws Exception {
        try {
            log.debug("updateEliteVipById  {}", eliteVip);
            int result = eliteVipMapper.updateEliteVipById(eliteVip);
            if (result < 1) {
                throw new Exception("updateEliteVipById失败");
            }
        } catch (Exception e) {
            log.error("updateEliteVipById  {}", eliteVip, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<EliteVip> findEliteVipList(EliteVip eliteVip) throws Exception {
        try {
            log.debug("findEliteVipList  {}", eliteVip);
            return eliteVipMapper.findEliteVipList(eliteVip);
        } catch (Exception e) {
            log.error("findEliteVipList  {}", eliteVip, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<EliteVip> eliteVipList) throws Exception {
        try {
            log.debug("updateEliteVipById  {}", eliteVipList);
            int result = eliteVipMapper.batchUpdate(eliteVipList);
            if (result < 1) {
                throw new Exception("updateAppointLogById失败");
            }
        } catch (Exception e) {
            log.error("updateEliteVipById  {}", eliteVipList, e);
            throw e;
        }
    }
}
