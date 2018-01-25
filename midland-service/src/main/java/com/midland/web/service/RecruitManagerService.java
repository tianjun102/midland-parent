package com.midland.web.service;

import com.midland.web.model.RecruitManager;

import java.util.List;

public interface RecruitManagerService {

    /**
     * 主键查询
     **/
    RecruitManager selectRecruitManagerById(Integer id);

    /**
     * 主键删除
     **/
    void deleteRecruitManagerById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateRecruitManagerById(RecruitManager recruitManager) throws Exception;

    /**
     * 插入
     **/
    void insertRecruitManager(RecruitManager recruitManager) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<RecruitManager> findRecruitManagerList(RecruitManager recruitManager) throws Exception;

    void batchUpdate(List<RecruitManager> recruitManagerList) throws Exception;
}
