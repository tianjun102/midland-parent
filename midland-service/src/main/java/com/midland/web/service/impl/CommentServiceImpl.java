package com.midland.web.service.impl;

import com.midland.web.model.Comment;
import com.midland.web.dao.CommentMapper;
import com.midland.web.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

	private Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	@Autowired
	private CommentMapper commentMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertComment(Comment comment) throws Exception {
		try {
			log.debug("insert {}",comment);
			commentMapper.insertComment(comment);
		} catch(Exception e) {
			log.error("insertComment异常 {}",comment,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Comment selectCommentById(Integer id) {
		log.debug("selectCommentById  {}",id);
		return commentMapper.selectCommentById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteCommentById(Integer id)throws Exception {
		try {
			log.debug("deleteCommentById  {}",id);
			int result = commentMapper.deleteCommentById(id);
			if (result < 1) {
				throw new Exception("deleteCommentById失败");
			}
		} catch(Exception e) {
			log.error("deleteCommentById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateCommentById(Comment comment) throws Exception {
		try {
			log.debug("updateCommentById  {}",comment);
			int result = commentMapper.updateCommentById(comment);
			if (result < 1) {
				throw new Exception("updateCommentById失败");
			}
		} catch(Exception e) {
			log.error("updateCommentById  {}",comment,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Comment> findCommentList(Comment comment) throws Exception {
		try {
			log.debug("findCommentList  {}",comment);
			return commentMapper.findCommentList(comment);
		} catch(Exception e) {
			log.error("findCommentList  {}",comment,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<Comment> commentList) throws Exception {
		try {
			log.debug("updateCommentById  {}",commentList);
			int result = commentMapper.batchUpdate(commentList);
			if (result < 1) {
				throw new Exception("updateCommentById失败");
			}
		} catch(Exception e) {
			log.error("updateCommentById  {}",commentList,e);
			throw e;
		}
	}
}
