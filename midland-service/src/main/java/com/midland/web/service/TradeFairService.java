package com.midland.web.service;

import com.midland.web.model.TradeFair;

import java.util.List;

public interface TradeFairService {

    /**
     * 主键查询
     **/
    TradeFair selectTradeFairById(Integer id);

    /**
     * 主键删除
     **/
    void deleteTradeFairById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateTradeFairById(TradeFair tradeFair) throws Exception;

    /**
     * 插入
     **/
    void insertTradeFair(TradeFair tradeFair) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<TradeFair> findTradeFairList(TradeFair tradeFair) throws Exception;

    void batchUpdate(List<TradeFair> tradeFairList) throws Exception;

}
