package com.midland.web.service;

import com.midland.web.model.SiteMap;
import java.util.List;
public interface SiteMapService {

	/**
	 * 主键查询
	 **/
	SiteMap selectSiteMapById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteSiteMapById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateSiteMapById(SiteMap siteMap) throws Exception;

	/**
	 * 插入
	 **/
	void insertSiteMap(SiteMap siteMap) throws Exception;

    void updateSiteMapSelectiveById(SiteMap siteMap) throws Exception;

    /**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<SiteMap> findSiteMapList(SiteMap siteMap) throws Exception;


	List<SiteMap> findSiteMapByList(List<Integer> cateId) throws Exception;

    List<SiteMap> findSiteMapByModeId(List<Integer> cateId) throws Exception;

    void batchUpdate(List<SiteMap> siteMapList) throws Exception;

}
