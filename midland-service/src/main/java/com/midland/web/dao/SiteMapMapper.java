package com.midland.web.dao;

import com.midland.web.model.SiteMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteMapMapper {

    SiteMap selectSiteMapById(Integer siteMap);

    int deleteSiteMapById(Integer siteMap);

    int updateSiteMapById(SiteMap siteMap);

    int updateSiteMapSelectiveById(SiteMap siteMap);

    int showAndHide(SiteMap siteMap);

    int updateIsDelete(SiteMap siteMap);

    int insertSiteMap(SiteMap siteMap);

    SiteMap shiftUp(SiteMap siteMap);

    SiteMap shiftDown(SiteMap siteMap);

    List<SiteMap> findSiteMapByList(@Param("list") List<Integer> list,@Param("isShow") Integer isShow);

    List<SiteMap> findSiteMapByModeId(@Param("list") List<Integer> list);

    List<SiteMap> findSiteMapList(SiteMap siteMap);

    List<SiteMap> findCateGory(SiteMap siteMap);

    int getCountByCateId(int cateId);


    List<SiteMap> findModes(SiteMap siteMap);

    int batchUpdate(@Param("siteMapList") List<SiteMap> siteMapList);

}
