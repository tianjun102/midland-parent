package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.LiaisonRecordEmail;
import com.midland.web.service.LiaisonRecordEmailService;
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
@RequestMapping("/liaisonRecordEmail/")
public class LiaisonRecordEmailRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(LiaisonRecordEmailRestController.class);
	@Autowired
	private LiaisonRecordEmailService liaisonRecordEmailServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addLiaisonRecordEmail(@RequestBody LiaisonRecordEmail obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addLiaisonRecordEmail {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			liaisonRecordEmailServiceImpl.insertLiaisonRecordEmail(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addLiaisonRecordEmail异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getLiaisonRecordEmailById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getLiaisonRecordEmailById  {}",id);
			LiaisonRecordEmail liaisonRecordEmail = liaisonRecordEmailServiceImpl.selectLiaisonRecordEmailById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(liaisonRecordEmail);
		} catch(Exception e) {
			log.error("getLiaisonRecordEmail异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateLiaisonRecordEmailById(@RequestBody LiaisonRecordEmail obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateLiaisonRecordEmailById  {}",obj);
			liaisonRecordEmailServiceImpl.updateLiaisonRecordEmailById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLiaisonRecordEmailById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLiaisonRecordEmailList(@RequestBody LiaisonRecordEmail  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findLiaisonRecordEmailList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<LiaisonRecordEmail> list = (Page<LiaisonRecordEmail>)liaisonRecordEmailServiceImpl.findLiaisonRecordEmailList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findLiaisonRecordEmailList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
