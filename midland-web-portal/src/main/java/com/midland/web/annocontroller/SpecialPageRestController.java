package com.midland.web.annocontroller;

import com.midland.web.model.SpecialPage;
import com.midland.web.service.SpecialPageService;
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
@RequestMapping("/specialPage/")
public class SpecialPageRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(SpecialPageRestController.class);
	@Autowired
	private SpecialPageService specialPageServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addSpecialPage(@RequestBody SpecialPage obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addSpecialPage {}",obj);
			specialPageServiceImpl.insertSpecialPage(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addSpecialPage异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getSpecialPageById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getSpecialPageById  {}",id);
			SpecialPage specialPage = specialPageServiceImpl.selectSpecialPageById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(specialPage);
		} catch(Exception e) {
			log.error("getSpecialPage异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateSpecialPageById(@RequestBody SpecialPage obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateSpecialPageById  {}",obj);
			specialPageServiceImpl.updateSpecialPageById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateSpecialPageById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findSpecialPageList(@RequestBody SpecialPage  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findSpecialPageList  {}",obj);
			obj.setIsShow(0);
			MidlandHelper.doPage(request);
			Page<SpecialPage> list = (Page<SpecialPage>)specialPageServiceImpl.findSpecialPageList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findSpecialPageList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
