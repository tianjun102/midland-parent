package com.midland.web.dao;

import com.midland.web.model.HeadMsg;
import java.util.List;

public interface HeadMsgMapper {

	HeadMsg selectHeadMsgById(Integer headMsg);

	int deleteHeadMsgById(Integer headMsg);

	int updateHeadMsgById(HeadMsg headMsg);

	int insertHeadMsg(HeadMsg headMsg);

	List<HeadMsg> findHeadMsgList(HeadMsg headMsg);

}
