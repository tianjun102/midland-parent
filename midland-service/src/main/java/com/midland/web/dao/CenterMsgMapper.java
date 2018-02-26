package com.midland.web.dao;

import com.midland.web.model.CenterMsg;
import java.util.List;

public interface CenterMsgMapper {

	CenterMsg selectCenterMsgById(Integer centerMsg);

	int deleteCenterMsgById(Integer centerMsg);

	int updateCenterMsgById(CenterMsg centerMsg);

	int insertCenterMsg(CenterMsg centerMsg);

	List<CenterMsg> findCenterMsgList(CenterMsg centerMsg);

}
