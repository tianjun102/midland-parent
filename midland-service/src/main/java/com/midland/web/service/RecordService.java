package com.midland.web.service;

import com.midland.web.model.Record;
import java.util.List;
public interface RecordService {

	/**
	 * 主键查询
	 **/
	Record selectRecordById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteRecordById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateRecordById(Record record) throws Exception;

	/**
	 * 插入
	 **/
	void insertRecord(Record record) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Record> findRecordList(Record record) throws Exception;

}
