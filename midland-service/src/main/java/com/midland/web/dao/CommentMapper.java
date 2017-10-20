package com.midland.web.dao;

import com.midland.web.model.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentMapper {

	Comment selectCommentById(Integer comment);

	int deleteCommentById(Integer comment);

	int updateCommentById(Comment comment);

	int insertComment(Comment comment);

	List<Comment> findCommentList(Comment comment);

	int batchUpdate(@Param("commentList") List<Comment> commentList);

}
