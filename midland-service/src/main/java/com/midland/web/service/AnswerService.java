package com.midland.web.service;

import com.midland.web.model.Answer;

import java.util.List;

public interface AnswerService {

    /**
     * 主键查询
     **/
    Answer selectAnswerById(Integer id);

    /**
     * 主键删除
     **/
    void deleteAnswerById(Integer id) throws Exception;

    /**
     * 主键更新
     **/
    void updateAnswerById(Answer answer) throws Exception;

    /**
     * 插入
     **/
    void insertAnswer(Answer answer) throws Exception;

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    List<Answer> findAnswerList(Answer answer) throws Exception;

    void thumb_up(int id) throws Exception;

    void thumb_down(int id) throws Exception;

    void batchUpdate(List<Answer> answerList) throws Exception;

}
