package com.midland.web.service;

import com.midland.web.model.FilmLibrary;
import java.util.List;
public interface FilmLibraryService {

	/**
	 * 主键查询
	 **/
	FilmLibrary selectFilmLibraryById(Integer id);

	/**
	 * 主键删除
	 **/
	void deleteFilmLibraryById(Integer id) throws Exception;

	/**
	 * 主键更新
	 **/
	void updateFilmLibraryById(FilmLibrary filmLibrary) throws Exception;

	/**
	 * 插入
	 **/
	void insertFilmLibrary(FilmLibrary filmLibrary) throws Exception;

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	List<FilmLibrary> findFilmLibraryList(FilmLibrary filmLibrary) throws Exception;

	void batchUpdate(List<FilmLibrary> filmLibraryList) throws Exception;

}
