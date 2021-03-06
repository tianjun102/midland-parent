package com.midland.web.service;

import com.midland.web.model.Popular;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PopularService {


    int getCountByCateId(int cateId) throws Exception;

    @Transactional
    void shiftUp(Popular popular) throws Exception;

    @Transactional
    void shiftDown(Popular popular) throws Exception;

    /**
     * 主键查询
     **/
    Popular selectById(Integer id);

    /**
     * 主键删除
     **/
    void deleteById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateById(Popular popular) throws Exception;

    /**
     * 插入
     **/
    void insertPopular(Popular popular) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Popular> findPopularList(Popular popular) throws Exception;

    List<Popular> findCateGory(Popular popular) throws Exception;

    void batchUpdate(List<Popular> popularList) throws Exception;

}
