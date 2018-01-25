package com.midland.web.service.impl;

import com.midland.web.dao.RegistrationProtocolMapper;
import com.midland.web.model.RegistrationProtocol;
import com.midland.web.service.RegistrationProtocolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationProtocolServiceImpl implements RegistrationProtocolService {

    private Logger log = LoggerFactory.getLogger(RegistrationProtocolServiceImpl.class);
    @Autowired
    private RegistrationProtocolMapper registrationProtocolMapper;

    /**
     * 插入
     **/
    @Override
    public void insertRegistrationProtocol(RegistrationProtocol registrationProtocol) throws Exception {
        try {
            log.info("insert {}", registrationProtocol);
            registrationProtocolMapper.insertRegistrationProtocol(registrationProtocol);
        } catch (Exception e) {
            log.error("insertRegistrationProtocol异常 {}", registrationProtocol, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public RegistrationProtocol selectRegistrationProtocolById(Integer id) {
        log.info("selectRegistrationProtocolById  {}", id);
        return registrationProtocolMapper.selectRegistrationProtocolById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteRegistrationProtocolById(Integer id) throws Exception {
        try {
            log.info("deleteRegistrationProtocolById  {}", id);
            int result = registrationProtocolMapper.deleteRegistrationProtocolById(id);
            if (result < 1) {
                throw new Exception("deleteRegistrationProtocolById失败");
            }
        } catch (Exception e) {
            log.error("deleteRegistrationProtocolById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateRegistrationProtocolById(RegistrationProtocol registrationProtocol) throws Exception {
        try {
            log.info("updateRegistrationProtocolById  {}", registrationProtocol);
            int result = registrationProtocolMapper.updateRegistrationProtocolById(registrationProtocol);
            if (result < 1) {
                throw new Exception("updateRegistrationProtocolById失败");
            }
        } catch (Exception e) {
            log.error("updateRegistrationProtocolById  {}", registrationProtocol, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<RegistrationProtocol> findRegistrationProtocolList(RegistrationProtocol registrationProtocol) throws Exception {
        try {
            log.info("findRegistrationProtocolList  {}", registrationProtocol);
            return registrationProtocolMapper.findRegistrationProtocolList(registrationProtocol);
        } catch (Exception e) {
            log.error("findRegistrationProtocolList  {}", registrationProtocol, e);
            throw e;
        }
    }
}
