package com.midland.web.service;

import com.midland.web.model.EliteVip;

import java.util.List;

public interface EliteVipService {

    /**
     * 主键查询
     **/
    EliteVip selectEliteVipById(Integer id);

    /**
     * 主键删除
     **/
    void deleteEliteVipById(Integer id) throws Exception;

    int getCountByCateId(int cateId) throws Exception;

    /**
     * 主键更新
     **/
    void updateEliteVipById(EliteVip eliteVip) throws Exception;

    /**
     * 插入
     **/
    void insertEliteVip(EliteVip eliteVip) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<EliteVip> findEliteVipList(EliteVip eliteVip) throws Exception;

    void batchUpdate(List<EliteVip> eliteVipList) throws Exception;

}
