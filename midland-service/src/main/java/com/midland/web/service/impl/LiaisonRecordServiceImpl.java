package com.midland.web.service.impl;

import com.midland.web.dao.LiaisonRecordMapper;
import com.midland.web.model.LiaisonRecord;
import com.midland.web.service.LiaisonRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiaisonRecordServiceImpl implements LiaisonRecordService {

    private Logger log = LoggerFactory.getLogger(LiaisonRecordServiceImpl.class);
    @Autowired
    private LiaisonRecordMapper liaisonRecordMapper;

    /**
     * 插入
     **/
    @Override
    public void insertLiaisonRecord(LiaisonRecord liaisonRecord) throws Exception {
        try {
            log.debug("insert {}", liaisonRecord);
            liaisonRecordMapper.insertLiaisonRecord(liaisonRecord);
        } catch (Exception e) {
            log.error("insertLiaisonRecord异常 {}", liaisonRecord, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public LiaisonRecord selectLiaisonRecordById(Integer id) {
        log.debug("selectLiaisonRecordById  {}", id);
        return liaisonRecordMapper.selectLiaisonRecordById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteLiaisonRecordById(Integer id) throws Exception {
        try {
            log.debug("deleteLiaisonRecordById  {}", id);
            int result = liaisonRecordMapper.deleteLiaisonRecordById(id);
            if (result < 1) {
                throw new Exception("deleteLiaisonRecordById失败");
            }
        } catch (Exception e) {
            log.error("deleteLiaisonRecordById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateLiaisonRecordById(LiaisonRecord liaisonRecord) throws Exception {
        try {
            log.debug("updateLiaisonRecordById  {}", liaisonRecord);
            int result = liaisonRecordMapper.updateLiaisonRecordById(liaisonRecord);
            if (result < 1) {
                throw new Exception("updateLiaisonRecordById失败");
            }
        } catch (Exception e) {
            log.error("updateLiaisonRecordById  {}", liaisonRecord, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<LiaisonRecord> findLiaisonRecordList(LiaisonRecord liaisonRecord) {
        try {
            log.debug("findLiaisonRecordList  {}", liaisonRecord);
            return liaisonRecordMapper.findLiaisonRecordList(liaisonRecord);
        } catch (Exception e) {
            log.error("findLiaisonRecordList  {}", liaisonRecord, e);
            return null;
        }
    }

    @Override
    public void batchUpdate(List<LiaisonRecord> liaisonRecordList) throws Exception {
        try {
            log.debug("updateLiaisonRecordById  {}", liaisonRecordList);
            int result = liaisonRecordMapper.batchUpdate(liaisonRecordList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateLiaisonRecordById  {}", liaisonRecordList, e);
            throw e;
        }
    }
}
