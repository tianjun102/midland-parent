package com.midland.web.dao;

import com.midland.web.model.Comment;
import java.util.List;

public interface CommentMapper {

	Comment selectCommentById(Integer comment);

	int deleteCommentById(Integer comment);

	int updateCommentById(Comment comment);

	int insertComment(Comment comment);

	List<Comment> findCommentList(Comment comment);

}
