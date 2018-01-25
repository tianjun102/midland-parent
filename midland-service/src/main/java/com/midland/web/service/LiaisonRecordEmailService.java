package com.midland.web.service;

import com.midland.web.model.LiaisonRecordEmail;

import java.util.List;

public interface LiaisonRecordEmailService {

    /**
     * 主键查询
     **/
    LiaisonRecordEmail selectLiaisonRecordEmailById(Integer id);

    /**
     * 主键删除
     **/
    void deleteLiaisonRecordEmailById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateLiaisonRecordEmailById(LiaisonRecordEmail liaisonRecordEmail) throws Exception;

    /**
     * 插入
     **/
    void insertLiaisonRecordEmail(LiaisonRecordEmail liaisonRecordEmail) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<LiaisonRecordEmail> findLiaisonRecordEmailList(LiaisonRecordEmail liaisonRecordEmail) throws Exception;

    void batchUpdate(List<LiaisonRecordEmail> liaisonRecordEmailList) throws Exception;
}
