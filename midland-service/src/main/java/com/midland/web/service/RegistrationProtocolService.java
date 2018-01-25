package com.midland.web.service;

import com.midland.web.model.RegistrationProtocol;

import java.util.List;

public interface RegistrationProtocolService {

    /**
     * 主键查询
     **/
    RegistrationProtocol selectRegistrationProtocolById(Integer id);

    /**
     * 主键删除
     **/
    void deleteRegistrationProtocolById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateRegistrationProtocolById(RegistrationProtocol registrationProtocol) throws Exception;

    /**
     * 插入
     **/
    void insertRegistrationProtocol(RegistrationProtocol registrationProtocol) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<RegistrationProtocol> findRegistrationProtocolList(RegistrationProtocol registrationProtocol) throws Exception;

}
