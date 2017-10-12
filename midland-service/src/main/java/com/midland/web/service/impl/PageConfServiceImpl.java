package com.midland.web.service.impl;

import com.midland.web.model.PageConf;
import com.midland.web.dao.PageConfMapper;
import com.midland.web.service.PageConfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PageConfServiceImpl implements PageConfService {

	private Logger log = LoggerFactory.getLogger(PageConfServiceImpl.class);
	@Autowired
	private PageConfMapper pageConfMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertPageConf(PageConf pageConf) throws Exception {
		try {
			log.debug("insert {}",pageConf);
			pageConfMapper.insertPageConf(pageConf);
		} catch(Exception e) {
			log.error("insertPageConf异常 {}",pageConf,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public PageConf selectPageConfById(Integer id) {
		log.debug("selectPageConfById  {}",id);
		return pageConfMapper.selectPageConfById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deletePageConfById(Integer id)throws Exception {
		try {
			log.debug("deletePageConfById  {}",id);
			int result = pageConfMapper.deletePageConfById(id);
			if (result < 1) {
				throw new Exception("deletePageConfById失败");
			}
		} catch(Exception e) {
			log.error("deletePageConfById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updatePageConfById(PageConf pageConf) throws Exception {
		try {
			log.debug("updatePageConfById  {}",pageConf);
			int result = pageConfMapper.updatePageConfById(pageConf);
			if (result < 1) {
				throw new Exception("updatePageConfById失败");
			}
		} catch(Exception e) {
			log.error("updatePageConfById  {}",pageConf,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<PageConf> findPageConfList(PageConf pageConf) throws Exception {
		try {
			log.debug("findPageConfList  {}",pageConf);
			return pageConfMapper.findPageConfList(pageConf);
		} catch(Exception e) {
			log.error("findPageConfList  {}",pageConf,e);
			throw e;
		}
	}
}
