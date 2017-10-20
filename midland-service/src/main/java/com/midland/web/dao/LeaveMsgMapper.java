package com.midland.web.dao;

import com.midland.web.model.Information;
import com.midland.web.model.LeaveMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaveMsgMapper {

	LeaveMsg selectLeaveMsgById(Integer leaveMsg);

	int deleteLeaveMsgById(Integer leaveMsg);

	int updateLeaveMsgById(LeaveMsg leaveMsg);

	int insertLeaveMsg(LeaveMsg leaveMsg);

	List<LeaveMsg> findLeaveMsgList(LeaveMsg leaveMsg);

	int batchUpdate(@Param("leaveMsgList") List<LeaveMsg> leaveMsgList);

}
