package com.midland.web.service.impl;

import com.midland.web.dao.ReplyMapper;
import com.midland.web.model.Reply;
import com.midland.web.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);
    @Autowired
    private ReplyMapper replyMapper;

    /**
     * 插入
     **/
    @Override
    public void insertReply(Reply reply) throws Exception {
        try {
            log.info("insert {}", reply);
            replyMapper.insertReply(reply);
        } catch (Exception e) {
            log.error("insertReply异常 {}", reply, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Reply selectReplyById(Integer id) {
        log.info("selectReplyById  {}", id);
        return replyMapper.selectReplyById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteReplyById(Integer id) throws Exception {
        try {
            log.info("deleteReplyById  {}", id);
            int result = replyMapper.deleteReplyById(id);
            if (result < 1) {
                throw new Exception("deleteReplyById失败");
            }
        } catch (Exception e) {
            log.error("deleteReplyById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateReplyById(Reply reply) throws Exception {
        try {
            log.info("updateReplyById  {}", reply);
            int result = replyMapper.updateReplyById(reply);
            if (result < 1) {
                throw new Exception("updateReplyById失败");
            }
        } catch (Exception e) {
            log.error("updateReplyById  {}", reply, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Reply> findReplyList(Reply reply) throws Exception {
        try {
            log.info("findReplyList  {}", reply);
            return replyMapper.findReplyList(reply);
        } catch (Exception e) {
            log.error("findReplyList  {}", reply, e);
            throw e;
        }
    }
}
