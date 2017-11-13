package com.midland.web.annocontroller;

import com.midland.web.model.Discount;
import com.midland.web.service.DiscountService;
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
@RequestMapping("/discount/")
public class DiscountRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(DiscountRestController.class);
	@Autowired
	private DiscountService discountServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addDiscount(@RequestBody Discount obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addDiscount {}",obj);
			discountServiceImpl.insertDiscount(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addDiscount异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getDiscountById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getDiscountById  {}",id);
			Discount discount = discountServiceImpl.selectDiscountById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(discount);
		} catch(Exception e) {
			log.error("getDiscount异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateDiscountById(@RequestBody Discount obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateDiscountById  {}",obj);
			discountServiceImpl.updateDiscountById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateDiscountById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findDiscountList(@RequestBody Discount  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findDiscountList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Discount> list = (Page<Discount>)discountServiceImpl.findDiscountList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findDiscountList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
