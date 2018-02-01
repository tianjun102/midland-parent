package com.midland.web.dao;

import com.midland.web.model.SiteProtocol;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SiteProtocolMapper {

	SiteProtocol selectSiteProtocolById(Integer siteProtocol);

	int deleteSiteProtocolById(Integer siteProtocol);

	int updateSiteProtocolById(SiteProtocol siteProtocol);

	int insertSiteProtocol(SiteProtocol siteProtocol);

	List<SiteProtocol> findSiteProtocolList(SiteProtocol siteProtocol);

}
