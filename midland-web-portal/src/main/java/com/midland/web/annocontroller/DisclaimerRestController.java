package com.midland.web.annocontroller;

import com.midland.web.model.Disclaimer;
import com.midland.web.service.DisclaimerService;
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
@RequestMapping("/disclaimer/")
public class DisclaimerRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(DisclaimerRestController.class);
	@Autowired
	private DisclaimerService disclaimerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addDisclaimer(@RequestBody Disclaimer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addDisclaimer {}",obj);
			disclaimerServiceImpl.insertDisclaimer(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addDisclaimer异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getDisclaimerById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getDisclaimerById  {}",id);
			Disclaimer disclaimer = disclaimerServiceImpl.selectDisclaimerById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(disclaimer);
		} catch(Exception e) {
			log.error("getDisclaimer异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateDisclaimerById(@RequestBody Disclaimer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateDisclaimerById  {}",obj);
			disclaimerServiceImpl.updateDisclaimerById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateDisclaimerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findDisclaimerList(@RequestBody Disclaimer  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findDisclaimerList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Disclaimer> list = (Page<Disclaimer>)disclaimerServiceImpl.findDisclaimerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findDisclaimerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
