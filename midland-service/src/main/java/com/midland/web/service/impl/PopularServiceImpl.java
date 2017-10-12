package com.midland.web.service.impl;

import com.midland.web.model.Popular;
import com.midland.web.dao.PopularMapper;
import com.midland.web.service.PopularService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PopularServiceImpl implements PopularService {

	private Logger log = LoggerFactory.getLogger(PopularServiceImpl.class);
	@Autowired
	private PopularMapper popularMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertPopular(Popular popular) throws Exception {
		try {
			log.debug("insert {}",popular);
			popularMapper.insertPopular(popular);
		} catch(Exception e) {
			log.error("insertPopular异常 {}",popular,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Popular selectById(Integer id) {
		log.debug("selectById  {}",id);
		return popularMapper.selectById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteById(Integer id)throws Exception {
		try {
			log.debug("deleteById  {}",id);
			int result = popularMapper.deleteById(id);
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
	public void updateById(Popular popular) throws Exception {
		try {
			log.debug("updateById  {}",popular);
			int result = popularMapper.updateById(popular);
			if (result < 1) {
				throw new Exception("updateById失败");
			}
		} catch(Exception e) {
			log.error("updateById  {}",popular,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Popular> findPopularList(Popular popular) throws Exception {
		try {
			log.debug("findPopularList  {}",popular);
			return popularMapper.findPopularList(popular);
		} catch(Exception e) {
			log.error("findPopularList  {}",popular,e);
			throw e;
		}
	}
}
