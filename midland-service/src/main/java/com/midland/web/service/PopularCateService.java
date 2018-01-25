package com.midland.web.service;

import com.midland.web.model.PopularCate;

import java.util.List;

public interface PopularCateService {

    /**
     * 主键查询
     **/
    PopularCate selectPopularCateById(Integer id);

    /**
     * 主键删除
     **/
    void deletePopularCateById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updatePopularCateById(PopularCate popularCate) throws Exception;

    /**
     * 插入
     **/
    void insertPopularCate(PopularCate popularCate) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<PopularCate> findPopularCateList(PopularCate popularCate) throws Exception;

}
