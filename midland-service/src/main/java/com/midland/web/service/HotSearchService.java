package com.midland.web.service;

import com.midland.web.model.HotSearch;

import java.util.List;

public interface HotSearchService {

    /**
     * 主键查询
     **/
    HotSearch selectHotSearchById(Integer id);

    /**
     * 主键删除
     **/
    void deleteHotSearchById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateHotSearchById(HotSearch hotSearch) throws Exception;

    /**
     * 插入
     **/
    void insertHotSearch(HotSearch hotSearch) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<HotSearch> findHotSearchList(HotSearch hotSearch) throws Exception;

    void batchUpdate(List<HotSearch> hotSearchList) throws Exception;

}
