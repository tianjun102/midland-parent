package com.midland.web.annocontroller;

import com.midland.web.model.QrCode;
import com.midland.web.service.QrCodeService;
import com.midland.base.BaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/qrCode/")
public class QrCodeRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(QrCodeRestController.class);
	@Autowired
	private QrCodeService qrCodeServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addQrCode(@RequestBody QrCode obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addQrCode {}",obj);
			qrCodeServiceImpl.insertQrCode(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addQrCode异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getQrCodeById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getQrCodeById  {}",id);
			QrCode qrCode = qrCodeServiceImpl.selectQrCodeById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(qrCode);
		} catch(Exception e) {
			log.error("getQrCode异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateQrCodeById(@RequestBody QrCode obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateQrCodeById  {}",obj);
			qrCodeServiceImpl.updateQrCodeById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateQrCodeById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findQrCodeList(@RequestBody QrCode  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findQrCodeList  {}",obj);
			MidlandHelper.doPage(request);
			Page<QrCode> list = (Page<QrCode>)qrCodeServiceImpl.findQrCodeList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findQrCodeList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
