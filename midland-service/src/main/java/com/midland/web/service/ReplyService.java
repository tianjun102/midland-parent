package com.midland.web.service;

import com.midland.web.model.Reply;

import java.util.List;

public interface ReplyService {

    /**
     * 主键查询
     **/
    Reply selectReplyById(Integer id);

    /**
     * 主键删除
     **/
    void deleteReplyById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateReplyById(Reply reply) throws Exception;

    /**
     * 插入
     **/
    void insertReply(Reply reply) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Reply> findReplyList(Reply reply) throws Exception;

}
