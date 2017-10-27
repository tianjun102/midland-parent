package com.midland.web.service;

import com.midland.web.model.Complaints;
import java.util.List;
public interface ComplaintsService {

	/**
	 * 主键查询
	 **/
	Complaints selectComplaintsById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteComplaintsById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateComplaintsById(Complaints complaints) throws Exception;

	/**
	 * 插入
	 **/
	void insertComplaints(Complaints complaints) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Complaints> findComplaintsList(Complaints complaints) throws Exception;

}
