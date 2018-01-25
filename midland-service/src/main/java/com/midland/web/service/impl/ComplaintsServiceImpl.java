package com.midland.web.service.impl;

import com.midland.web.dao.ComplaintsMapper;
import com.midland.web.model.Complaints;
import com.midland.web.service.ComplaintsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintsServiceImpl implements ComplaintsService {

    private Logger log = LoggerFactory.getLogger(ComplaintsServiceImpl.class);
    @Autowired
    private ComplaintsMapper complaintsMapper;

    /**
     * 插入
     **/
    @Override
    public void insertComplaints(Complaints complaints) throws Exception {
        try {
            log.info("insert {}", complaints);
            complaintsMapper.insertComplaints(complaints);
        } catch (Exception e) {
            log.error("insertComplaints异常 {}", complaints, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Complaints selectComplaintsById(Integer id) {
        log.info("selectComplaintsById  {}", id);
        return complaintsMapper.selectComplaintsById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteComplaintsById(Integer id) throws Exception {
        try {
            log.info("deleteComplaintsById  {}", id);
            int result = complaintsMapper.deleteComplaintsById(id);
            if (result < 1) {
                throw new Exception("deleteComplaintsById失败");
            }
        } catch (Exception e) {
            log.error("deleteComplaintsById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateComplaintsById(Complaints complaints) throws Exception {
        try {
            log.info("updateComplaintsById  {}", complaints);
            int result = complaintsMapper.updateComplaintsById(complaints);
            if (result < 1) {
                throw new Exception("updateComplaintsById失败");
            }
        } catch (Exception e) {
            log.error("updateComplaintsById  {}", complaints, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Complaints> findComplaintsList(Complaints complaints) throws Exception {
        try {
            log.info("findComplaintsList  {}", complaints);
            return complaintsMapper.findComplaintsList(complaints);
        } catch (Exception e) {
            log.error("findComplaintsList  {}", complaints, e);
            throw e;
        }
    }
}
