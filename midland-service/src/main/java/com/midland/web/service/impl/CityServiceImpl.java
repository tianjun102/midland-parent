package com.midland.web.service.impl;

import com.midland.web.model.City;
import com.midland.web.dao.CityMapper;
import com.midland.web.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CityServiceImpl implements CityService {

	private Logger log = LoggerFactory.getLogger(CityServiceImpl.class);
	@Autowired
	private CityMapper cityMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertCity(City city) throws Exception {
		try {
			log.debug("insert {}",city);
			cityMapper.insertCity(city);
		} catch(Exception e) {
			log.error("insertCity异常 {}",city,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public City selectById(Integer id) {
		log.debug("selectById  {}",id);
		return cityMapper.selectById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteById(Integer id)throws Exception {
		try {
			log.debug("deleteById  {}",id);
			int result = cityMapper.deleteById(id);
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
	public void updateById(City city) throws Exception {
		try {
			log.debug("updateById  {}",city);
			int result = cityMapper.updateById(city);
			if (result < 1) {
				throw new Exception("updateById失败");
			}
		} catch(Exception e) {
			log.error("updateById  {}",city,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<City> findCityList(City city) throws Exception {
		try {
			log.debug("findCityList  {}",city);
			return cityMapper.findCityList(city);
		} catch(Exception e) {
			log.error("findCityList  {}",city,e);
			throw e;
		}
	}
}
