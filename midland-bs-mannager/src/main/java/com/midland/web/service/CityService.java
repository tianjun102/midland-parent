package com.midland.web.service;

import com.midland.web.model.City;
import java.util.List;
public interface CityService {

	/**
	 * 主键查询
	 **/
	City selectById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateById(City city) throws Exception;

	/**
	 * 插入
	 **/
	void insertCity(City city) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<City> findCityList(City city) throws Exception;

}
