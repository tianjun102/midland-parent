package com.midland.web.dao;

import com.midland.web.model.HeadMsg;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HeadMsgMapper {

    HeadMsg selectHeadMsgById(Integer headMsg);

    int deleteHeadMsgById(Integer headMsg);

    int updateHeadMsgById(HeadMsg headMsg);

    int insertHeadMsg(HeadMsg headMsg);

    List<HeadMsg> findHeadMsgList(HeadMsg headMsg);

}
