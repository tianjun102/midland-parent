package com.midland.web.dao;

import com.midland.web.model.Reply;
import java.util.List;

public interface ReplyMapper {

	Reply selectReplyById(Integer reply);

	int deleteReplyById(Integer reply);

	int updateReplyById(Reply reply);

	int insertReply(Reply reply);

	List<Reply> findReplyList(Reply reply);

}
