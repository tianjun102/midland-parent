package com.midland.web.service;

import com.midland.web.model.LayoutMap;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LayoutMapService {


    /**
     * 主键查询
     **/
    LayoutMap selectLayoutMapById(Integer id);

    /**
     * 主键删除
     **/
    void deleteLayoutMapById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateLayoutMapById(LayoutMap layoutMap) throws Exception;

    /**
     * 插入
     **/
    void insertLayoutMap(LayoutMap layoutMap) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<LayoutMap> findLayoutMapList(LayoutMap layoutMap) throws Exception;

    @Transactional
    void shiftUp(LayoutMap layoutMap) throws Exception;

    @Transactional
    void shiftDown(LayoutMap category) throws Exception;
}
