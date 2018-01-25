package com.midland.web.service;

import com.midland.web.model.HotHand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HotHandService {

    int getMaxOrderBy();

    /**
     * 主键查询
     **/
    HotHand selectHotHandById(Integer id);

    @Transactional
    void shiftUp(HotHand hotHand) throws Exception;

    @Transactional
    void shiftDown(HotHand hotHand) throws Exception;

    /**
     * 主键删除
     **/
    void deleteHotHandById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateHotHandById(HotHand hotHand) throws Exception;

    /**
     * 插入
     **/
    void insertHotHand(HotHand hotHand) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<HotHand> findHotHandList(HotHand hotHand) throws Exception;

}
