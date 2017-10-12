package com.midland.web.service.impl;

import com.midland.web.model.PopularCate;
import com.midland.web.dao.PopularCateMapper;
import com.midland.web.service.PopularCateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PopularCateServiceImpl implements PopularCateService {

	private Logger log = LoggerFactory.getLogger(PopularCateServiceImpl.class);
	@Autowired
	private PopularCateMapper popularCateMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertPopularCate(PopularCate popularCate) throws Exception {
		try {
			log.debug("insert {}",popularCate);
			popularCateMapper.insertPopularCate(popularCate);
		} catch(Exception e) {
			log.error("insertPopularCate异常 {}",popularCate,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public PopularCate selectPopularCateById(Integer id) {
		log.debug("selectPopularCateById  {}",id);
		return popularCateMapper.selectPopularCateById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deletePopularCateById(Integer id)throws Exception {
		try {
			log.debug("deletePopularCateById  {}",id);
			int result = popularCateMapper.deletePopularCateById(id);
			if (result < 1) {
				throw new Exception("deletePopularCateById失败");
			}
		} catch(Exception e) {
			log.error("deletePopularCateById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updatePopularCateById(PopularCate popularCate) throws Exception {
		try {
			log.debug("updatePopularCateById  {}",popularCate);
			int result = popularCateMapper.updatePopularCateById(popularCate);
			if (result < 1) {
				throw new Exception("updatePopularCateById失败");
			}
		} catch(Exception e) {
			log.error("updatePopularCateById  {}",popularCate,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<PopularCate> findPopularCateList(PopularCate popularCate) throws Exception {
		try {
			log.debug("findPopularCateList  {}",popularCate);
			return popularCateMapper.findPopularCateList(popularCate);
		} catch(Exception e) {
			log.error("findPopularCateList  {}",popularCate,e);
			throw e;
		}
	}
}
