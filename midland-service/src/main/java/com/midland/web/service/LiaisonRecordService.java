package com.midland.web.service;

import com.midland.web.model.LiaisonRecord;

import java.util.List;

public interface LiaisonRecordService {

    /**
     * 主键查询
     **/
    LiaisonRecord selectLiaisonRecordById(Integer id);

    /**
     * 主键删除
     **/
    void deleteLiaisonRecordById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateLiaisonRecordById(LiaisonRecord liaisonRecord) throws Exception;

    /**
     * 插入
     **/
    void insertLiaisonRecord(LiaisonRecord liaisonRecord) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<LiaisonRecord> findLiaisonRecordList(LiaisonRecord liaisonRecord);

    void batchUpdate(List<LiaisonRecord> liaisonRecordList) throws Exception;

}
