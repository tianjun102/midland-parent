package com.midland.web.annocontroller;

import com.midland.web.model.Record;
import com.midland.web.service.RecordService;
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
@RequestMapping("/record/")
public class RecordRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(RecordRestController.class);
	@Autowired
	private RecordService recordServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addRecord(@RequestBody Record obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addRecord {}",obj);
			recordServiceImpl.insertRecord(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addRecord异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getRecordById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getRecordById  {}",id);
			Record record = recordServiceImpl.selectRecordById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(record);
		} catch(Exception e) {
			log.error("getRecord异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateRecordById(@RequestBody Record obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateRecordById  {}",obj);
			recordServiceImpl.updateRecordById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateRecordById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findRecordList(@RequestBody Record  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findRecordList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Record> list = (Page<Record>)recordServiceImpl.findRecordList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findRecordList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
