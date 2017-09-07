package com.midland.web.dao;

import com.midland.web.model.SiteMap;
import java.util.List;

public interface SiteMapMapper {

	SiteMap selectSiteMapById(Integer siteMap);

	int deleteSiteMapById(Integer siteMap);

	int updateSiteMapById(SiteMap siteMap);

	int insertSiteMap(SiteMap siteMap);

	List<SiteMap> findSiteMapList(SiteMap siteMap);

}
