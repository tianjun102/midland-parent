package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.TradeFair;
import com.midland.web.service.TradeFairService;
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
@RequestMapping("/tradeFair/")
public class TradeFairRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(TradeFairRestController.class);
	@Autowired
	private TradeFairService tradeFairServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addTradeFair(@RequestBody TradeFair obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addTradeFair {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			tradeFairServiceImpl.insertTradeFair(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addTradeFair异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getTradeFairById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getTradeFairById  {}",id);
			TradeFair tradeFair = tradeFairServiceImpl.selectTradeFairById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(tradeFair);
		} catch(Exception e) {
			log.error("getTradeFair异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateTradeFairById(@RequestBody TradeFair obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateTradeFairById  {}",obj);
			tradeFairServiceImpl.updateTradeFairById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateTradeFairById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findTradeFairList(@RequestBody TradeFair  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findTradeFairList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<TradeFair> list = (Page<TradeFair>)tradeFairServiceImpl.findTradeFairList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findTradeFairList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
