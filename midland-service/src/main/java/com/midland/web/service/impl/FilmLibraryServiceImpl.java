package com.midland.web.service.impl;

import com.midland.web.model.FilmLibrary;
import com.midland.web.dao.FilmLibraryMapper;
import com.midland.web.service.FilmLibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FilmLibraryServiceImpl implements FilmLibraryService {

	private Logger log = LoggerFactory.getLogger(FilmLibraryServiceImpl.class);
	@Autowired
	private FilmLibraryMapper filmLibraryMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertFilmLibrary(FilmLibrary filmLibrary) throws Exception {
		try {
			log.debug("insert {}",filmLibrary);
			filmLibraryMapper.insertFilmLibrary(filmLibrary);
		} catch(Exception e) {
			log.error("insertFilmLibrary异常 {}",filmLibrary,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public FilmLibrary selectFilmLibraryById(Integer id) {
		log.debug("selectFilmLibraryById  {}",id);
		return filmLibraryMapper.selectFilmLibraryById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteFilmLibraryById(Integer id)throws Exception {
		try {
			log.debug("deleteFilmLibraryById  {}",id);
			int result = filmLibraryMapper.deleteFilmLibraryById(id);
			if (result < 1) {
				throw new Exception("deleteFilmLibraryById失败");
			}
		} catch(Exception e) {
			log.error("deleteFilmLibraryById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateFilmLibraryById(FilmLibrary filmLibrary) throws Exception {
		try {
			log.debug("updateFilmLibraryById  {}",filmLibrary);
			int result = filmLibraryMapper.updateFilmLibraryById(filmLibrary);
			if (result < 1) {
				throw new Exception("updateFilmLibraryById失败");
			}
		} catch(Exception e) {
			log.error("updateFilmLibraryById  {}",filmLibrary,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<FilmLibrary> findFilmLibraryList(FilmLibrary filmLibrary) throws Exception {
		try {
			log.debug("findFilmLibraryList  {}",filmLibrary);
			return filmLibraryMapper.findFilmLibraryList(filmLibrary);
		} catch(Exception e) {
			log.error("findFilmLibraryList  {}",filmLibrary,e);
			throw e;
		}
	}

	@Override
	public void batchUpdate(List<FilmLibrary> filmLibraryList) throws Exception {
		try {
			log.debug("updateFilmLibraryById  {}",filmLibraryList);
			int result = filmLibraryMapper.batchUpdate(filmLibraryList);
			if (result < 1) {
				throw new Exception("updateFeedbackById失败");
			}
		} catch(Exception e) {
			log.error("updateFilmLibraryById  {}",filmLibraryList,e);
			throw e;
		}
	}
}
