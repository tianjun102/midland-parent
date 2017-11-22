package com.midland.web.service.impl;

import com.midland.web.model.Attention;
import com.midland.web.dao.AttentionMapper;
import com.midland.web.service.AttentionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class AttentionServiceImpl implements AttentionService {

	private Logger log = LoggerFactory.getLogger(AttentionServiceImpl.class);
	@Autowired
	private AttentionMapper attentionMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertAttention(Attention attention) throws Exception {
		try {
			log.info("insert {}",attention);
			attentionMapper.insertAttention(attention);
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException){
				log.error("insertAttention,不能重复关注 {}",attention);
			}else{
				log.error("insertAttention异常 {}",attention,e);
			}

			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public Attention selectAttentionById(Integer id) {
		log.info("selectAttentionById  {}",id);
		return attentionMapper.selectAttentionById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteAttentionById(Integer id)throws Exception {
		try {
			log.info("deleteAttentionById  {}",id);
			int result = attentionMapper.deleteAttentionById(id);
			if (result < 1) {
				throw new Exception("deleteAttentionById失败");
			}
		} catch(Exception e) {
			log.error("deleteAttentionById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateAttentionById(Attention attention) throws Exception {
		try {
			log.info("updateAttentionById  {}",attention);
			int result = attentionMapper.updateAttentionById(attention);
			if (result < 1) {
				throw new Exception("updateAttentionById失败");
			}
		} catch(Exception e) {
			log.error("updateAttentionById  {}",attention,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<Attention> findAttentionList(Attention attention) throws Exception {
		try {
			log.info("findAttentionList  {}",attention);
			return attentionMapper.findAttentionList(attention);
		} catch(Exception e) {
			log.error("findAttentionList  {}",attention,e);
			throw e;
		}
	}
	@Override
	public List<Attention> findAttentionByList(List<Map> map) throws Exception {
		try {
			log.info("findAttentionByList  {}",map);
			return attentionMapper.findAttentionByList(map);
		} catch(Exception e) {
			log.error("findAttentionByList  {}",map,e);
			throw e;
		}
	}
}
