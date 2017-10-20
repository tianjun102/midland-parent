package com.midland.web.service;

import com.midland.web.model.EliteClub;
import java.util.List;
public interface EliteClubService {

	/**
	 * 主键查询
	 **/
	EliteClub selectEliteClubById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteEliteClubById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateEliteClubById(EliteClub eliteClub) throws Exception;

	/**
	 * 插入
	 **/
	void insertEliteClub(EliteClub eliteClub) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<EliteClub> findEliteClubList(EliteClub eliteClub) throws Exception;

	void batchUpdate(List<EliteClub> eliteClubList) throws Exception;

}
