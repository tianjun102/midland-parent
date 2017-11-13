package com.midland.web.service;

import com.midland.web.model.Attention;
import java.util.List;
public interface AttentionService {

	/**
	 * 主键查询
	 **/
	Attention selectAttentionById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteAttentionById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateAttentionById(Attention attention) throws Exception;

	/**
	 * 插入
	 **/
	void insertAttention(Attention attention) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<Attention> findAttentionList(Attention attention) throws Exception;

}
