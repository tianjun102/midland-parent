package com.midland.web.service.impl;

import com.midland.web.model.LinkUrlManager;
import com.midland.web.dao.LinkUrlManagerMapper;
import com.midland.web.service.LinkUrlManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LinkUrlManagerServiceImpl implements LinkUrlManagerService {

	private Logger log = LoggerFactory.getLogger(LinkUrlManagerServiceImpl.class);
	@Autowired
	private LinkUrlManagerMapper linkUrlManagerMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertLinkUrlManager(LinkUrlManager linkUrlManager) throws Exception {
		try {
			log.info("insert {}",linkUrlManager);
			linkUrlManagerMapper.insertLinkUrlManager(linkUrlManager);
		} catch(Exception e) {
			log.error("insertLinkUrlManager异常 {}",linkUrlManager,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public LinkUrlManager selectById(Integer id) {
		log.info("selectById  {}",id);
		return linkUrlManagerMapper.selectById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteById(Integer id)throws Exception {
		try {
			log.info("deleteById  {}",id);
			int result = linkUrlManagerMapper.deleteById(id);
			if (result < 1) {
				throw new Exception("deleteById失败");
			}
		} catch(Exception e) {
			log.error("deleteById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateById(LinkUrlManager linkUrlManager) throws Exception {
		try {
			log.info("updateById  {}",linkUrlManager);
			int result = linkUrlManagerMapper.updateById(linkUrlManager);
			if (result < 1) {
				throw new Exception("updateById失败");
			}
		} catch(Exception e) {
			log.error("updateById  {}",linkUrlManager,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<LinkUrlManager> findLinkUrlManagerList(LinkUrlManager linkUrlManager) throws Exception {
		try {
			log.info("findLinkUrlManagerList  {}",linkUrlManager);
			return linkUrlManagerMapper.findLinkUrlManagerList(linkUrlManager);
		} catch(Exception e) {
			log.error("findLinkUrlManagerList  {}",linkUrlManager,e);
			throw e;
		}
	}
}
