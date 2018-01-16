package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Footer;
import com.midland.web.service.FooterService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
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
@RequestMapping("/footer/")
public class FooterRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(FooterRestController.class);
	@Autowired
	private FooterService footerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addFooter(@RequestBody Footer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addFooter {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			footerServiceImpl.insertFooter(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addFooter异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getFooterById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getFooterById  {}",id);
			Footer footer = footerServiceImpl.selectFooterById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(footer);
		} catch(Exception e) {
			log.error("getFooter异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateFooterById(@RequestBody Footer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateFooterById  {}",obj);
			footerServiceImpl.updateFooterById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateFooterById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findFooterList(@RequestBody Footer  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findFooterList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<Footer> list = (Page<Footer>)footerServiceImpl.findFooterList(obj);
			Paginator paginator=list.getPaginator();
			if(list!=null&&list.size()>=0){
				result.setModel(list.get(0));
			}
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findFooterList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
