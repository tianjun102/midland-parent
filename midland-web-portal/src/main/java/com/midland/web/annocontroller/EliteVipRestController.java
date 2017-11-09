package com.midland.web.annocontroller;

import com.midland.web.model.EliteVip;
import com.midland.web.service.EliteVipService;
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
@RequestMapping("/eliteVip/")
public class EliteVipRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(EliteVipRestController.class);
	@Autowired
	private EliteVipService eliteVipServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addEliteVip(@RequestBody EliteVip obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addEliteVip {}",obj);
			eliteVipServiceImpl.insertEliteVip(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addEliteVip异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getEliteVipById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getEliteVipById  {}",id);
			EliteVip eliteVip = eliteVipServiceImpl.selectEliteVipById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(eliteVip);
		} catch(Exception e) {
			log.error("getEliteVip异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateEliteVipById(@RequestBody EliteVip obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateEliteVipById  {}",obj);
			eliteVipServiceImpl.updateEliteVipById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateEliteVipById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findEliteVipList(@RequestBody EliteVip  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findEliteVipList  {}",obj);
			MidlandHelper.doPage(request);
			Page<EliteVip> list = (Page<EliteVip>)eliteVipServiceImpl.findEliteVipList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findEliteVipList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
