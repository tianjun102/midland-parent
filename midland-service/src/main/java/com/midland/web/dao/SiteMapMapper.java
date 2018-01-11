package com.midland.web.dao;

import com.midland.web.model.ResumeManager;
import com.midland.web.model.SiteMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SiteMapMapper {

	SiteMap selectSiteMapById(Integer siteMap);

	int deleteSiteMapById(Integer siteMap);

	int updateSiteMapById(SiteMap siteMap);

	int insertSiteMap(SiteMap siteMap);
	List<SiteMap> findSiteMapByList(@Param("list") List<Integer> list);
	List<SiteMap> findSiteMapByModeId(@Param("list") List<Integer> list);

	List<SiteMap> findSiteMapList(SiteMap siteMap);

	int batchUpdate(@Param("siteMapList") List<SiteMap> siteMapList);

}
