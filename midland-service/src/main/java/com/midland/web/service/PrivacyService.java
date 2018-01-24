package com.midland.web.service;

import com.midland.web.model.Privacy;
import java.util.List;
public interface PrivacyService {

	/**
	 * 主键查询
	 **/
	Privacy selectPrivacyById(Integer id);

	/**
	 * 主键删除
	 **/
	void deletePrivacyById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updatePrivacyById(Privacy privacy) throws Exception;

	/**
	 * 插入
	 **/
	void insertPrivacy(Privacy privacy) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Privacy> findPrivacyList(Privacy privacy) throws Exception;

}
