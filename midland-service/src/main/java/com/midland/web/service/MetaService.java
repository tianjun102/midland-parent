package com.midland.web.service;

import com.midland.web.model.Meta;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface MetaService {

	/**
	 * 主键查询
	 **/
	Meta selectMetaById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteMetaById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateMetaById(Meta meta) throws Exception;

	/**
	 * 插入
	 **/
	void insertMeta(Meta meta) throws Exception;

    void ifExist_update(Meta meta) throws Exception;

    /**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Meta> findMetaList(Meta meta) throws Exception;

	@Transactional
	void shiftUp(Meta menu) throws Exception;

	@Transactional
    void shiftDown(Meta menu) throws Exception;
}
