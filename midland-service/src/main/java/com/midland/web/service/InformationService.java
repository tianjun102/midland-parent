package com.midland.web.service;

import com.midland.web.model.Information;
import java.util.List;
public interface InformationService {

	/**
	 * 主键查询
	 **/
	Information selectInformationById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteInformationById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateInformationById(Information information) throws Exception;

	/**
	 * 插入
	 **/
	void insertInformation(Information information) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Information> findInformationList(Information information) throws Exception;

	void batchUpdate(List<Information> informationList) throws Exception;

}
