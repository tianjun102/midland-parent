package com.midland.web.service.impl;

import com.midland.web.model.EliteClub;
import com.midland.web.dao.EliteClubMapper;
import com.midland.web.service.EliteClubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EliteClubServiceImpl implements EliteClubService {

	private Logger log = LoggerFactory.getLogger(EliteClubServiceImpl.class);
	@Autowired
	private EliteClubMapper eliteClubMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertEliteClub(EliteClub eliteClub) throws Exception {
		try {
			log.info("insert {}",eliteClub);
			eliteClubMapper.insertEliteClub(eliteClub);
		} catch(Exception e) {
			log.error("insertEliteClub异常 {}",eliteClub,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public EliteClub selectEliteClubById(Integer id) {
		log.info("selectEliteClubById  {}",id);
		return eliteClubMapper.selectEliteClubById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteEliteClubById(Integer id)throws Exception {
		try {
			log.info("deleteEliteClubById  {}",id);
			int result = eliteClubMapper.deleteEliteClubById(id);
			if (result < 1) {
				throw new Exception("deleteEliteClubById失败");
			}
		} catch(Exception e) {
			log.error("deleteEliteClubById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateEliteClubById(EliteClub eliteClub) throws Exception {
		try {
			log.info("updateEliteClubById  {}",eliteClub);
			int result = eliteClubMapper.updateEliteClubById(eliteClub);
			if (result < 1) {
				throw new Exception("updateEliteClubById失败");
			}
		} catch(Exception e) {
			log.error("updateEliteClubById  {}",eliteClub,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<EliteClub> findEliteClubList(EliteClub eliteClub) throws Exception {
		try {
			log.info("findEliteClubList  {}",eliteClub);
			return eliteClubMapper.findEliteClubList(eliteClub);
		} catch(Exception e) {
			log.error("findEliteClubList  {}",eliteClub,e);
			throw e;
		}
	}
}
