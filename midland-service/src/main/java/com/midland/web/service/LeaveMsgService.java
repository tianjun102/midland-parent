package com.midland.web.service;

import com.midland.web.model.LeaveMsg;
import java.util.List;
public interface LeaveMsgService {

	/**
	 * 主键查询
	 **/
	LeaveMsg selectLeaveMsgById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteLeaveMsgById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateLeaveMsgById(LeaveMsg leaveMsg) throws Exception;

	/**
	 * 插入
	 **/
	void insertLeaveMsg(LeaveMsg leaveMsg) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<LeaveMsg> findLeaveMsgList(LeaveMsg leaveMsg) throws Exception;

	void batchUpdate(List<LeaveMsg> leaveMsgList) throws Exception;

}
