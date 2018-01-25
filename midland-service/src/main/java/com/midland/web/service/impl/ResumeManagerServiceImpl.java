package com.midland.web.service.impl;

import com.midland.web.dao.ResumeManagerMapper;
import com.midland.web.model.ResumeManager;
import com.midland.web.service.ResumeManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManagerServiceImpl implements ResumeManagerService {

    private Logger log = LoggerFactory.getLogger(ResumeManagerServiceImpl.class);
    @Autowired
    private ResumeManagerMapper resumeManagerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertResumeManager(ResumeManager resumeManager) throws Exception {
        try {
            log.debug("insert {}", resumeManager);
            resumeManagerMapper.insertResumeManager(resumeManager);
        } catch (Exception e) {
            log.error("insertResumeManager异常 {}", resumeManager, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public ResumeManager selectResumeManagerById(Integer id) {
        log.debug("selectResumeManagerById  {}", id);
        return resumeManagerMapper.selectResumeManagerById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteResumeManagerById(Integer id) throws Exception {
        try {
            log.debug("deleteResumeManagerById  {}", id);
            int result = resumeManagerMapper.deleteResumeManagerById(id);
            if (result < 1) {
                throw new Exception("deleteResumeManagerById失败");
            }
        } catch (Exception e) {
            log.error("deleteResumeManagerById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateResumeManagerById(ResumeManager resumeManager) throws Exception {
        try {
            log.debug("updateResumeManagerById  {}", resumeManager);
            int result = resumeManagerMapper.updateResumeManagerById(resumeManager);
            if (result < 1) {
                throw new Exception("updateResumeManagerById失败");
            }
        } catch (Exception e) {
            log.error("updateResumeManagerById  {}", resumeManager, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<ResumeManager> findResumeManagerList(ResumeManager resumeManager) throws Exception {
        try {
            log.debug("findResumeManagerList  {}", resumeManager);
            return resumeManagerMapper.findResumeManagerList(resumeManager);
        } catch (Exception e) {
            log.error("findResumeManagerList  {}", resumeManager, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<ResumeManager> resumeManagerList) throws Exception {
        try {
            log.debug("ResumeManagerbatchUpdate  {}", resumeManagerList);
            int result = resumeManagerMapper.batchUpdate(resumeManagerList);
            if (result < 1) {
                throw new Exception("RecruitManagerbatchUpdate失败");
            }
        } catch (Exception e) {
            log.error("ResumeManagerbatchUpdate  {}", resumeManagerList, e);
            throw e;
        }
    }
}
