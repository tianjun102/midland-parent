package com.midland.web.service;

import com.midland.web.model.CornerFile;
import java.util.List;
public interface CornerFileService {

	/**
	 * 主键查询
	 **/
	CornerFile selectCornerFileById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteCornerFileById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateCornerFileById(CornerFile cornerFile) throws Exception;

	/**
	 * 插入
	 **/
	void insertCornerFile(CornerFile cornerFile) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<CornerFile> findCornerFileList(CornerFile cornerFile) throws Exception;

}
