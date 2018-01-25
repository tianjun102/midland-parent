package com.midland.web.service.impl;

import com.midland.web.dao.LeaveMsgMapper;
import com.midland.web.model.LeaveMsg;
import com.midland.web.service.LeaveMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveMsgServiceImpl implements LeaveMsgService {

    private Logger log = LoggerFactory.getLogger(LeaveMsgServiceImpl.class);
    @Autowired
    private LeaveMsgMapper leaveMsgMapper;

    /**
     * 插入
     **/
    @Override
    public void insertLeaveMsg(LeaveMsg leaveMsg) throws Exception {
        try {
            log.debug("insert {}", leaveMsg);
            leaveMsgMapper.insertLeaveMsg(leaveMsg);
        } catch (Exception e) {
            log.error("insertLeaveMsg异常 {}", leaveMsg, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public LeaveMsg selectLeaveMsgById(Integer id) {
        log.debug("selectLeaveMsgById  {}", id);
        return leaveMsgMapper.selectLeaveMsgById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteLeaveMsgById(Integer id) throws Exception {
        try {
            log.debug("deleteLeaveMsgById  {}", id);
            int result = leaveMsgMapper.deleteLeaveMsgById(id);
            if (result < 1) {
                throw new Exception("deleteLeaveMsgById失败");
            }
        } catch (Exception e) {
            log.error("deleteLeaveMsgById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateLeaveMsgById(LeaveMsg leaveMsg) throws Exception {
        try {
            log.debug("updateLeaveMsgById  {}", leaveMsg);
            int result = leaveMsgMapper.updateLeaveMsgById(leaveMsg);
            if (result < 1) {
                throw new Exception("updateLeaveMsgById失败");
            }
        } catch (Exception e) {
            log.error("updateLeaveMsgById  {}", leaveMsg, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<LeaveMsg> findLeaveMsgList(LeaveMsg leaveMsg) throws Exception {
        try {
            log.debug("findLeaveMsgList  {}", leaveMsg);
            return leaveMsgMapper.findLeaveMsgList(leaveMsg);
        } catch (Exception e) {
            log.error("findLeaveMsgList  {}", leaveMsg, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<LeaveMsg> leaveMsgList) throws Exception {
        try {
            log.debug("updateLeaveMsgById  {}", leaveMsgList);
            int result = leaveMsgMapper.batchUpdate(leaveMsgList);
            if (result < 1) {
                throw new Exception("updateFeedbackById失败");
            }
        } catch (Exception e) {
            log.error("updateLeaveMsgById  {}", leaveMsgList, e);
            throw e;
        }
    }
}
