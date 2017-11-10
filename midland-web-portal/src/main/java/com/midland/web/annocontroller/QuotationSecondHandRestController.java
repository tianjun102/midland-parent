package com.midland.web.annocontroller;

import com.midland.web.model.QuotationSecondHand;
import com.midland.web.service.QuotationSecondHandService;
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
@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(QuotationSecondHandRestController.class);
	@Autowired
	private QuotationSecondHandService quotationSecondHandServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addQuotationSecondHand(@RequestBody QuotationSecondHand obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addQuotationSecondHand {}",obj);
			quotationSecondHandServiceImpl.insertQuotationSecondHand(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addQuotationSecondHand异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getQuotationSecondHandById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getQuotationSecondHandById  {}",id);
			QuotationSecondHand quotationSecondHand = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(quotationSecondHand);
		} catch(Exception e) {
			log.error("getQuotationSecondHand异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateQuotationSecondHandById(@RequestBody QuotationSecondHand obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateQuotationSecondHandById  {}",obj);
			quotationSecondHandServiceImpl.updateQuotationSecondHandById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateQuotationSecondHandById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findQuotationSecondHandList(@RequestBody QuotationSecondHand  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findQuotationSecondHandList  {}",obj);
			MidlandHelper.doPage(request);
			Page<QuotationSecondHand> list = (Page<QuotationSecondHand>)quotationSecondHandServiceImpl.findQuotationSecondHandList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findQuotationSecondHandList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
