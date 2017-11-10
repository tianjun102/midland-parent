package com.midland.web.annocontroller;

import com.midland.web.model.LiaisonRecord;
import com.midland.web.service.LiaisonRecordService;
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
@RequestMapping("/liaisonRecord/")
public class LiaisonRecordRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(LiaisonRecordRestController.class);
	@Autowired
	private LiaisonRecordService liaisonRecordServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addLiaisonRecord(@RequestBody LiaisonRecord obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addLiaisonRecord {}",obj);
			liaisonRecordServiceImpl.insertLiaisonRecord(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addLiaisonRecord异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getLiaisonRecordById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getLiaisonRecordById  {}",id);
			LiaisonRecord liaisonRecord = liaisonRecordServiceImpl.selectLiaisonRecordById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(liaisonRecord);
		} catch(Exception e) {
			log.error("getLiaisonRecord异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateLiaisonRecordById(@RequestBody LiaisonRecord obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateLiaisonRecordById  {}",obj);
			liaisonRecordServiceImpl.updateLiaisonRecordById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLiaisonRecordById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLiaisonRecordList(@RequestBody LiaisonRecord  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findLiaisonRecordList  {}",obj);
			MidlandHelper.doPage(request);
			Page<LiaisonRecord> list = (Page<LiaisonRecord>)liaisonRecordServiceImpl.findLiaisonRecordList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findLiaisonRecordList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
