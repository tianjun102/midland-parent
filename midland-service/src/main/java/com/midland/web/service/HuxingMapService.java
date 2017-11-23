package com.midland.web.service;

import com.midland.web.model.HuxingMap;
import java.util.List;
public interface HuxingMapService {

	/**
	 * 主键查询
	 **/
	HuxingMap selectHuxingMapById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteHuxingMapById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateHuxingMapById(HuxingMap huxingMap) throws Exception;

	/**
	 * 插入
	 **/
	void insertHuxingMap(HuxingMap huxingMap) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<HuxingMap> findHuxingMapList(HuxingMap huxingMap) throws Exception;

}
