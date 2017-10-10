package com.midland.web.service;

import com.midland.web.model.Menu;
import java.util.List;
public interface MenuService {

	/**
	 * 主键查询
	 **/
	Menu selectMenuById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteMenuById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateMenuById(Menu menu) throws Exception;

	/**
	 * 插入
	 **/
	void insertMenu(Menu menu) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Menu> findMenuList(Menu menu) throws Exception;

	int getMaxOrderBy();



}
