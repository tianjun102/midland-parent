package com.midland.web.service.impl;

import com.midland.web.model.Meta;
import com.midland.web.dao.MetaMapper;
import com.midland.web.service.MetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MetaServiceImpl implements MetaService {

	private Logger log = LoggerFactory.getLogger(MetaServiceImpl.class);
	@Autowired
	private MetaMapper metaMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertMeta(Meta meta) throws Exception {
		try {
			log.debug("insert {}",meta);
			metaMapper.insertMeta(meta);
		} catch(Exception e) {
			log.error("insertMeta异常 {}",meta,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Meta selectMetaById(Integer id) {
		log.debug("selectMetaById  {}",id);
		return metaMapper.selectMetaById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteMetaById(Integer id)throws Exception {
		try {
			log.debug("deleteMetaById  {}",id);
			int result = metaMapper.deleteMetaById(id);
			if (result < 1) {
				throw new Exception("deleteMetaById失败");
			}
		} catch(Exception e) {
			log.error("deleteMetaById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateMetaById(Meta meta) throws Exception {
		try {
			log.debug("updateMetaById  {}",meta);
			int result = metaMapper.updateMetaById(meta);
			if (result < 1) {
				throw new Exception("updateMetaById失败");
			}
		} catch(Exception e) {
			log.error("updateMetaById  {}",meta,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Meta> findMetaList(Meta meta) throws Exception {
		try {
			log.debug("findMetaList  {}",meta);
			return metaMapper.findMetaList(meta);
		} catch(Exception e) {
			log.error("findMetaList  {}",meta,e);
			throw e;
		}
	}
}
