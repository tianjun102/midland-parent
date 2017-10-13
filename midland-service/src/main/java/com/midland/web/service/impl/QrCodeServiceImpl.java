package com.midland.web.service.impl;

import com.midland.web.model.QrCode;
import com.midland.web.dao.QrCodeMapper;
import com.midland.web.service.QrCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class QrCodeServiceImpl implements QrCodeService {

	private Logger log = LoggerFactory.getLogger(QrCodeServiceImpl.class);
	@Autowired
	private QrCodeMapper qrCodeMapper;

	/**
	 * 插入
	 **/
	@Override
	public void insertQrCode(QrCode qrCode) throws Exception {
		try {
			log.debug("insert {}",qrCode);
			qrCodeMapper.insertQrCode(qrCode);
		} catch(Exception e) {
			log.error("insertQrCode异常 {}",qrCode,e);
			throw e;
		}
	}

	/**
	 * 查询
	 **/
	@Override
	public QrCode selectQrCodeById(Integer id) {
		log.debug("selectQrCodeById  {}",id);
		return qrCodeMapper.selectQrCodeById(id);
	}

	/**
	 * 删除
	 **/
	@Override
	public void deleteQrCodeById(Integer id)throws Exception {
		try {
			log.debug("deleteQrCodeById  {}",id);
			int result = qrCodeMapper.deleteQrCodeById(id);
			if (result < 1) {
				throw new Exception("deleteQrCodeById失败");
			}
		} catch(Exception e) {
			log.error("deleteQrCodeById  {}",id,e);
			throw e;
		}
	}
	/**
	 * 更新
	 **/
	@Override
	public void updateQrCodeById(QrCode qrCode) throws Exception {
		try {
			log.debug("updateQrCodeById  {}",qrCode);
			int result = qrCodeMapper.updateQrCodeById(qrCode);
			if (result < 1) {
				throw new Exception("updateQrCodeById失败");
			}
		} catch(Exception e) {
			log.error("updateQrCodeById  {}",qrCode,e);
			throw e;
		}
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@Override
	public List<QrCode> findQrCodeList(QrCode qrCode) throws Exception {
		try {
			log.debug("findQrCodeList  {}",qrCode);
			return qrCodeMapper.findQrCodeList(qrCode);
		} catch(Exception e) {
			log.error("findQrCodeList  {}",qrCode,e);
			throw e;
		}
	}
}
