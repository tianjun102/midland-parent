package com.midland.web.dao;

import com.midland.web.model.CenterMsg;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CenterMsgMapper {

	CenterMsg selectCenterMsgById(Integer centerMsg);

	int deleteCenterMsgById(Integer centerMsg);

	int updateCenterMsgById(CenterMsg centerMsg);

	int insertCenterMsg(CenterMsg centerMsg);

	List<CenterMsg> findCenterMsgList(CenterMsg centerMsg);

}
