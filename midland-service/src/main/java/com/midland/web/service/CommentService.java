package com.midland.web.service;

import com.midland.web.model.Comment;
import java.util.List;
public interface CommentService {

	/**
	 * 主键查询
	 **/
	Comment selectCommentById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteCommentById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateCommentById(Comment comment) throws Exception;

	/**
	 * 插入
	 **/
	void insertComment(Comment comment) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Comment> findCommentList(Comment comment) throws Exception;

	/**
	 * 主键更新
	 **/
	void batchUpdate(List<Comment> commentList) throws Exception;

}
