package com.midland.web.service.impl;

import com.midland.web.dao.AnswerMapper;
import com.midland.web.model.Answer;
import com.midland.web.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);
    @Autowired
    private AnswerMapper answerMapper;

    /**
     * 插入
     **/
    @Override
    public void insertAnswer(Answer answer) throws Exception {
        try {
            log.debug("insert {}", answer);
            answerMapper.insertAnswer(answer);
        } catch (Exception e) {
            log.error("insertAnswer异常 {}", answer, e);
            throw e;
        }
    }

    /**
     * 查询
     **/
    @Override
    public Answer selectAnswerById(Integer id) {
        log.debug("selectAnswerById  {}", id);
        return answerMapper.selectAnswerById(id);
    }

    /**
     * 删除
     **/
    @Override
    public void deleteAnswerById(Integer id) throws Exception {
        try {
            log.debug("deleteAnswerById  {}", id);
            int result = answerMapper.deleteAnswerById(id);
            if (result < 1) {
                throw new Exception("deleteAnswerById失败");
            }
        } catch (Exception e) {
            log.error("deleteAnswerById  {}", id, e);
            throw e;
        }
    }

    /**
     * 更新
     **/
    @Override
    public void updateAnswerById(Answer answer) throws Exception {
        try {
            log.debug("updateAnswerById  {}", answer);
            int result = answerMapper.updateAnswerById(answer);
            if (result < 1) {
                throw new Exception("updateAnswerById失败");
            }
        } catch (Exception e) {
            log.error("updateAnswerById  {}", answer, e);
            throw e;
        }
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @Override
    public List<Answer> findAnswerList(Answer answer) throws Exception {
        try {
            log.debug("findAnswerList  {}", answer);
            return answerMapper.findAnswerList(answer);
        } catch (Exception e) {
            log.error("findAnswerList  {}", answer, e);
            throw e;
        }
    }

    @Override
    public void thumb_up(int id) throws Exception {
        try {
            log.debug("thumb_up  id={}", id);
            int result = answerMapper.thumb_up(id);
            if (result < 1) {
                throw new Exception("赞成失败");
            }
        } catch (Exception e) {
            log.error("thumb_up  id={}", id, e);
            throw e;
        }
    }

    @Override
    public void thumb_down(int id) throws Exception {
        try {
            log.debug("thumb_down  id={}", id);
            int result = answerMapper.thumb_down(id);
            if (result < 1) {
                throw new Exception("反对失败");
            }
        } catch (Exception e) {
            log.error("thumb_down  id={}", id, e);
            throw e;
        }
    }

    @Override
    public void batchUpdate(List<Answer> answerList) throws Exception {
        try {
            log.debug("updateAnswerById  {}", answerList);
            int result = answerMapper.batchUpdate(answerList);
            if (result < 1) {
                throw new Exception("updateCommentById失败");
            }
        } catch (Exception e) {
            log.error("updateAnswerById  {}", answerList, e);
            throw e;
        }
    }
}
