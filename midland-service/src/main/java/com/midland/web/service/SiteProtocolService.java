package com.midland.web.service;

import com.midland.web.model.SiteProtocol;
import java.util.List;
public interface SiteProtocolService {

	/**
	 * 主键查询
	 **/
	SiteProtocol selectSiteProtocolById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteSiteProtocolById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateSiteProtocolById(SiteProtocol siteProtocol) throws Exception;

	/**
	 * 插入
	 **/
	void insertSiteProtocol(SiteProtocol siteProtocol) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<SiteProtocol> findSiteProtocolList(SiteProtocol siteProtocol) throws Exception;

}
