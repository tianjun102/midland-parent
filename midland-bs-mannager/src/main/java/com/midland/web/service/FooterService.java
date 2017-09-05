package com.midland.web.service;

import com.midland.web.model.Footer;
import java.util.List;
public interface FooterService {

	/**
	 * 主键查询
	 **/
	Footer selectFooterById(Integer id);
	
	Footer getFooter();
	
	/**
	 * 主键删除
	 **/
	void deleteFooterById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateFooterById(Footer footer) throws Exception;

	/**
	 * 插入
	 **/
	void insertFooter(Footer footer) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Footer> findFooterList(Footer footer) throws Exception;

}
