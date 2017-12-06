package com.midland.web.service;

import com.midland.web.model.CenterMsg;
import java.util.List;
public interface CenterMsgService {

	/**
	 * 主键查询
	 **/
	CenterMsg selectCenterMsgById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteCenterMsgById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateCenterMsgById(CenterMsg centerMsg) throws Exception;

	/**
	 * 插入
	 **/
	void insertCenterMsg(CenterMsg centerMsg) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<CenterMsg> findCenterMsgList(CenterMsg centerMsg) throws Exception;

}
