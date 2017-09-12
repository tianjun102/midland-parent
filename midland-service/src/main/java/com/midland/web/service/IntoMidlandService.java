package com.midland.web.service;

import com.midland.web.model.IntoMidland;
import java.util.List;
public interface IntoMidlandService {

	/**
	 * 主键查询
	 **/
	IntoMidland selectIntoMidlandById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteIntoMidlandById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateIntoMidlandById(IntoMidland intoMidland) throws Exception;

	/**
	 * 插入
	 **/
	void insertIntoMidland(IntoMidland intoMidland) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<IntoMidland> findIntoMidlandList(IntoMidland intoMidland) throws Exception;

}
