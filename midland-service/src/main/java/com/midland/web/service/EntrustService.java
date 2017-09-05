package com.midland.web.service;

import com.midland.web.model.Entrust;
import java.util.List;
public interface EntrustService {

	/**
	 * 主键查询
	 **/
	Entrust selectEntrustById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteEntrustById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateEntrustById(Entrust entrust) throws Exception;

	/**
	 * 插入
	 **/
	void insertEntrust(Entrust entrust) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Entrust> findEntrustList(Entrust entrust) throws Exception;

}
