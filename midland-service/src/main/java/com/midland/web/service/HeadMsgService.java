package com.midland.web.service;

import com.midland.web.model.HeadMsg;

import java.util.List;

public interface HeadMsgService {

    /**
     * 主键查询
     **/
    HeadMsg selectHeadMsgById(Integer id);

    /**
     * 主键删除
     **/
    void deleteHeadMsgById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateHeadMsgById(HeadMsg headMsg) throws Exception;

    /**
     * 插入
     **/
    void insertHeadMsg(HeadMsg headMsg) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<HeadMsg> findHeadMsgList(HeadMsg headMsg) throws Exception;

}
