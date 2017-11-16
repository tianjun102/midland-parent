package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.LoanType;
import com.midland.web.service.LoanTypeService;
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
@RequestMapping("/loanType/")
public class LoanTypeRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(LoanTypeRestController.class);
	@Autowired
	private LoanTypeService loanTypeServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addLoanType(@RequestBody LoanType obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addLoanType {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			loanTypeServiceImpl.insertLoanType(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addLoanType异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getLoanTypeById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getLoanTypeById  {}",id);
			LoanType loanType = loanTypeServiceImpl.selectLoanTypeById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(loanType);
		} catch(Exception e) {
			log.error("getLoanType异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateLoanTypeById(@RequestBody LoanType obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateLoanTypeById  {}",obj);
			loanTypeServiceImpl.updateLoanTypeById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLoanTypeById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLoanTypeList(@RequestBody LoanType  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {

			log.info("findLoanTypeList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<LoanType> list = (Page<LoanType>)loanTypeServiceImpl.findLoanTypeList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findLoanTypeList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}


	/**
	 * 计算器说明
	 **/
	@RequestMapping("calculator")
	public Object findLoanType(@RequestBody LoanType  loanType, HttpServletRequest request) {
		Result result=new Result();
		try {
			log.info("findLoanTypeList  {}",loanType);
			MidlandHelper.doPage(request);
			loanType = loanTypeServiceImpl.findLoanType(loanType);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(loanType);
		} catch(Exception e) {
			log.error("findLoanTypeList  {}",loanType,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}



}
