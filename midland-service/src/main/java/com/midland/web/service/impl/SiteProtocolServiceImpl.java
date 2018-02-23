package com.midland.web.service.impl;

import com.midland.web.model.SiteProtocol;
import com.midland.web.dao.SiteProtocolMapper;
import com.midland.web.service.SiteProtocolService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SiteProtocolServiceImpl implements SiteProtocolService {

	private Logger log = LoggerFactory.getLogger(SiteProtocolServiceImpl.class);
	@Autowired
	private SiteProtocolMapper siteProtocolMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertSiteProtocol(SiteProtocol siteProtocol) throws Exception {
		try {
			log.info("insert {}",siteProtocol);
			siteProtocol.setCreateTime(MidlandHelper.getCurrentTime());
			siteProtocolMapper.insertSiteProtocol(siteProtocol);
		} catch(Exception e) {
			log.error("insertSiteProtocol异常 {}",siteProtocol,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public SiteProtocol selectSiteProtocolById(Integer id) {
		log.info("selectSiteProtocolById  {}",id);
		return siteProtocolMapper.selectSiteProtocolById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteSiteProtocolById(Integer id)throws Exception {
		try {
			log.info("deleteSiteProtocolById  {}",id);
			int result = siteProtocolMapper.deleteSiteProtocolById(id);
			if (result < 1) {
				throw new Exception("deleteSiteProtocolById失败");
			}
		} catch(Exception e) {
			log.error("deleteSiteProtocolById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateSiteProtocolById(SiteProtocol siteProtocol) throws Exception {
		try {
			log.info("updateSiteProtocolById  {}",siteProtocol);
			int result = siteProtocolMapper.updateSiteProtocolById(siteProtocol);
			if (result < 1) {
				throw new Exception("updateSiteProtocolById失败");
			}
		} catch(Exception e) {
			log.error("updateSiteProtocolById  {}",siteProtocol,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<SiteProtocol> findSiteProtocolList(SiteProtocol siteProtocol) throws Exception {
		try {
			log.info("findSiteProtocolList  {}",siteProtocol);
			return siteProtocolMapper.findSiteProtocolList(siteProtocol);
		} catch(Exception e) {
			log.error("findSiteProtocolList  {}",siteProtocol,e);
			throw e;
		}
	}
}
