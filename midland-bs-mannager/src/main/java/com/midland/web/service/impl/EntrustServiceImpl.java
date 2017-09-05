package com.midland.web.service.impl;

import com.midland.web.model.Entrust;
import com.midland.web.dao.EntrustMapper;
import com.midland.web.service.EntrustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EntrustServiceImpl implements EntrustService {

	private Logger log = LoggerFactory.getLogger(EntrustServiceImpl.class);
	@Autowired
	private EntrustMapper entrustMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertEntrust(Entrust entrust) throws Exception {
		try {
			log.info("insert {}",entrust);
			entrustMapper.insertEntrust(entrust);
		} catch(Exception e) {
			log.error("insertEntrust异常 {}",entrust,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Entrust selectEntrustById(Integer id) {
		log.info("selectEntrustById  {}",id);
		return entrustMapper.selectEntrustById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteEntrustById(Integer id)throws Exception {
		try {
			log.info("deleteEntrustById  {}",id);
			int result = entrustMapper.deleteEntrustById(id);
			if (result < 1) {
				throw new Exception("deleteEntrustById失败");
			}
		} catch(Exception e) {
			log.error("deleteEntrustById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateEntrustById(Entrust entrust) throws Exception {
		try {
			log.info("updateEntrustById  {}",entrust);
			int result = entrustMapper.updateEntrustById(entrust);
			if (result < 1) {
				throw new Exception("updateEntrustById失败");
			}
		} catch(Exception e) {
			log.error("updateEntrustById  {}",entrust,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Entrust> findEntrustList(Entrust entrust) throws Exception {
		try {
			log.info("findEntrustList  {}",entrust);
			return entrustMapper.findEntrustList(entrust);
		} catch(Exception e) {
			log.error("findEntrustList  {}",entrust,e);
			throw e;
		}
	}
}
