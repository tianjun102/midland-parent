package com.midland.web.service.impl;

import com.midland.web.dao.LiaisonRecordEmailMapper;
import com.midland.web.model.LiaisonRecordEmail;
import com.midland.web.service.LiaisonRecordEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiaisonRecordEmailServiceImpl implements LiaisonRecordEmailService {

    private Logger log = LoggerFactory.getLogger(LiaisonRecordEmailServiceImpl.class);
    @Autowired
    private LiaisonRecordEmailMapper liaisonRecordEmailMapper;

    /**
     * 插入
     **/
    @Override
    public void insertLiaisonRecordEmail(LiaisonRecordEmail liaisonRecordEmail) throws Exception {
        try {
            log.info("insert {}", liaisonRecordEmail);
            liaisonRecordEmailMapper.insertLiaisonRecordEmail(liaisonRecordEmail);
        } catch (Exception e) {
            log.error("insertLiaisonRecordEmail异常 {}", liaisonRecordEmail, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public LiaisonRecordEmail selectLiaisonRecordEmailById(Integer id) {
        log.info("selectLiaisonRecordEmailById  {}", id);
        return liaisonRecordEmailMapper.selectLiaisonRecordEmailById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteLiaisonRecordEmailById(Integer id) throws Exception {
        try {
            log.info("deleteLiaisonRecordEmailById  {}", id);
            int result = liaisonRecordEmailMapper.deleteLiaisonRecordEmailById(id);
            if (result < 1) {
                throw new Exception("deleteLiaisonRecordEmailById失败");
            }
        } catch (Exception e) {
            log.error("deleteLiaisonRecordEmailById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateLiaisonRecordEmailById(LiaisonRecordEmail liaisonRecordEmail) throws Exception {
        try {
            log.info("updateLiaisonRecordEmailById  {}", liaisonRecordEmail);
            int result = liaisonRecordEmailMapper.updateLiaisonRecordEmailById(liaisonRecordEmail);
            if (result < 1) {
                throw new Exception("updateLiaisonRecordEmailById失败");
            }
        } catch (Exception e) {
            log.error("updateLiaisonRecordEmailById  {}", liaisonRecordEmail, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<LiaisonRecordEmail> findLiaisonRecordEmailList(LiaisonRecordEmail liaisonRecordEmail) throws Exception {
        try {
            log.info("findLiaisonRecordEmailList  {}", liaisonRecordEmail);
            return liaisonRecordEmailMapper.findLiaisonRecordEmailList(liaisonRecordEmail);
        } catch (Exception e) {
            log.error("findLiaisonRecordEmailList  {}", liaisonRecordEmail, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<LiaisonRecordEmail> liaisonRecordEmailList) throws Exception {
        try {
            log.debug("updateLiaisonRecordEmailById  {}", liaisonRecordEmailList);
            int result = liaisonRecordEmailMapper.batchUpdate(liaisonRecordEmailList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateLiaisonRecordEmailById  {}", liaisonRecordEmailList, e);
            throw e;
        }
    }
}
