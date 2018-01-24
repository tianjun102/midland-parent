package com.midland.web.service.impl;

import com.midland.web.model.CornerFile;
import com.midland.web.dao.CornerFileMapper;
import com.midland.web.service.CornerFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CornerFileServiceImpl implements CornerFileService {

	private Logger log = LoggerFactory.getLogger(CornerFileServiceImpl.class);
	@Autowired
	private CornerFileMapper cornerFileMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertCornerFile(CornerFile cornerFile) throws Exception {
		try {
			log.info("insert {}",cornerFile);
			cornerFileMapper.insertCornerFile(cornerFile);
		} catch(Exception e) {
			log.error("insertCornerFile异常 {}",cornerFile,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public CornerFile selectCornerFileById(Integer id) {
		log.info("selectCornerFileById  {}",id);
		return cornerFileMapper.selectCornerFileById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteCornerFileById(Integer id)throws Exception {
		try {
			log.info("deleteCornerFileById  {}",id);
			int result = cornerFileMapper.deleteCornerFileById(id);
			if (result < 1) {
				throw new Exception("deleteCornerFileById失败");
			}
		} catch(Exception e) {
			log.error("deleteCornerFileById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateCornerFileById(CornerFile cornerFile) throws Exception {
		try {
			log.info("updateCornerFileById  {}",cornerFile);
			int result = cornerFileMapper.updateCornerFileById(cornerFile);
			if (result < 1) {
				throw new Exception("updateCornerFileById失败");
			}
		} catch(Exception e) {
			log.error("updateCornerFileById  {}",cornerFile,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<CornerFile> findCornerFileList(CornerFile cornerFile) throws Exception {
		try {
			log.info("findCornerFileList  {}",cornerFile);
			return cornerFileMapper.findCornerFileList(cornerFile);
		} catch(Exception e) {
			log.error("findCornerFileList  {}",cornerFile,e);
			throw e;
		}
	}
}
