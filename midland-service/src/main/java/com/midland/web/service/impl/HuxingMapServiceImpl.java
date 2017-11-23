package com.midland.web.service.impl;

import com.midland.web.model.HuxingMap;
import com.midland.web.dao.HuxingMapMapper;
import com.midland.web.service.HuxingMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class HuxingMapServiceImpl implements HuxingMapService {

	private Logger log = LoggerFactory.getLogger(HuxingMapServiceImpl.class);
	@Autowired
	private HuxingMapMapper huxingMapMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertHuxingMap(HuxingMap huxingMap) throws Exception {
		try {
			log.info("insert {}",huxingMap);
			huxingMapMapper.insertHuxingMap(huxingMap);
		} catch(Exception e) {
			log.error("insertHuxingMap异常 {}",huxingMap,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public HuxingMap selectHuxingMapById(Integer id) {
		log.info("selectHuxingMapById  {}",id);
		return huxingMapMapper.selectHuxingMapById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteHuxingMapById(Integer id)throws Exception {
		try {
			log.info("deleteHuxingMapById  {}",id);
			int result = huxingMapMapper.deleteHuxingMapById(id);
			if (result < 1) {
				throw new Exception("deleteHuxingMapById失败");
			}
		} catch(Exception e) {
			log.error("deleteHuxingMapById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateHuxingMapById(HuxingMap huxingMap) throws Exception {
		try {
			log.info("updateHuxingMapById  {}",huxingMap);
			int result = huxingMapMapper.updateHuxingMapById(huxingMap);
			if (result < 1) {
				throw new Exception("updateHuxingMapById失败");
			}
		} catch(Exception e) {
			log.error("updateHuxingMapById  {}",huxingMap,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<HuxingMap> findHuxingMapList(HuxingMap huxingMap) throws Exception {
		try {
			log.info("findHuxingMapList  {}",huxingMap);
			return huxingMapMapper.findHuxingMapList(huxingMap);
		} catch(Exception e) {
			log.error("findHuxingMapList  {}",huxingMap,e);
			throw e;
		}
	}
}
