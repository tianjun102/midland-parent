package com.midland.web.service;

import com.midland.web.model.ErrorPage;
import java.util.List;
public interface ErrorPageService {

	/**
	 * 主键查询
	 **/
	ErrorPage selectErrorPageById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteErrorPageById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateErrorPageById(ErrorPage errorPage) throws Exception;

	/**
	 * 插入
	 **/
	void insertErrorPage(ErrorPage errorPage) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<ErrorPage> findErrorPageList(ErrorPage errorPage) throws Exception;

	void batchUpdate(List<ErrorPage> errorPageList) throws Exception;

}
