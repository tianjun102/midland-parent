package com.midland.web.service.impl;

import com.midland.web.model.TradeFair;
import com.midland.web.dao.TradeFairMapper;
import com.midland.web.service.TradeFairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TradeFairServiceImpl implements TradeFairService {

	private Logger log = LoggerFactory.getLogger(TradeFairServiceImpl.class);
	@Autowired
	private TradeFairMapper tradeFairMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertTradeFair(TradeFair tradeFair) throws Exception {
		try {
			log.debug("insert {}",tradeFair);
			tradeFairMapper.insertTradeFair(tradeFair);
		} catch(Exception e) {
			log.error("insertTradeFair异常 {}",tradeFair,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public TradeFair selectTradeFairById(Integer id) {
		log.debug("selectTradeFairById  {}",id);
		return tradeFairMapper.selectTradeFairById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteTradeFairById(Integer id)throws Exception {
		try {
			log.debug("deleteTradeFairById  {}",id);
			int result = tradeFairMapper.deleteTradeFairById(id);
			if (result < 1) {
				throw new Exception("deleteTradeFairById失败");
			}
		} catch(Exception e) {
			log.error("deleteTradeFairById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateTradeFairById(TradeFair tradeFair) throws Exception {
		try {
			log.debug("updateTradeFairById  {}",tradeFair);
			int result = tradeFairMapper.updateTradeFairById(tradeFair);
			if (result < 1) {
				throw new Exception("updateTradeFairById失败");
			}
		} catch(Exception e) {
			log.error("updateTradeFairById  {}",tradeFair,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<TradeFair> findTradeFairList(TradeFair tradeFair) throws Exception {
		try {
			log.debug("findTradeFairList  {}",tradeFair);
			return tradeFairMapper.findTradeFairList(tradeFair);
		} catch(Exception e) {
			log.error("findTradeFairList  {}",tradeFair,e);
			throw e;
		}
	}
}
