package com.midland.web.service;

import com.midland.web.model.PageConf;
import java.util.List;
public interface PageConfService {

	/**
	 * 主键查询
	 **/
	PageConf selectPageConfById(Integer id);

	/**
	 * 主键删除
	 **/
	void deletePageConfById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updatePageConfById(PageConf pageConf) throws Exception;

	/**
	 * 插入
	 **/
	void insertPageConf(PageConf pageConf) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<PageConf> findPageConfList(PageConf pageConf) throws Exception;

}
