package com.midland.web.service.impl;

import com.midland.web.model.RecruitManager;
import com.midland.web.dao.RecruitManagerMapper;
import com.midland.web.service.RecruitManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RecruitManagerServiceImpl implements RecruitManagerService {

	private Logger log = LoggerFactory.getLogger(RecruitManagerServiceImpl.class);
	@Autowired
	private RecruitManagerMapper recruitManagerMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertRecruitManager(RecruitManager recruitManager) throws Exception {
		try {
			log.info("insert {}",recruitManager);
			recruitManagerMapper.insertRecruitManager(recruitManager);
		} catch(Exception e) {
			log.error("insertRecruitManager异常 {}",recruitManager,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public RecruitManager selectRecruitManagerById(Integer id) {
		log.info("selectRecruitManagerById  {}",id);
		return recruitManagerMapper.selectRecruitManagerById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteRecruitManagerById(Integer id)throws Exception {
		try {
			log.info("deleteRecruitManagerById  {}",id);
			int result = recruitManagerMapper.deleteRecruitManagerById(id);
			if (result < 1) {
				throw new Exception("deleteRecruitManagerById失败");
			}
		} catch(Exception e) {
			log.error("deleteRecruitManagerById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateRecruitManagerById(RecruitManager recruitManager) throws Exception {
		try {
			log.info("updateRecruitManagerById  {}",recruitManager);
			int result = recruitManagerMapper.updateRecruitManagerById(recruitManager);
			if (result < 1) {
				throw new Exception("updateRecruitManagerById失败");
			}
		} catch(Exception e) {
			log.error("updateRecruitManagerById  {}",recruitManager,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<RecruitManager> findRecruitManagerList(RecruitManager recruitManager) throws Exception {
		try {
			log.info("findRecruitManagerList  {}",recruitManager);
			return recruitManagerMapper.findRecruitManagerList(recruitManager);
		} catch(Exception e) {
			log.error("findRecruitManagerList  {}",recruitManager,e);
			throw e;
		}
	}
}
