package com.midland.web.service;

import com.midland.web.model.ResumeManager;
import java.util.List;
public interface ResumeManagerService {

	/**
	 * 主键查询
	 **/
	ResumeManager selectResumeManagerById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteResumeManagerById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateResumeManagerById(ResumeManager resumeManager) throws Exception;

	/**
	 * 插入
	 **/
	void insertResumeManager(ResumeManager resumeManager) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<ResumeManager> findResumeManagerList(ResumeManager resumeManager) throws Exception;

}
