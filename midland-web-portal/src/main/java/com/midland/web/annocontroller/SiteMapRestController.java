package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.SiteMap;
import com.midland.web.service.SiteMapService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@RestController
@SuppressWarnings("all")
@RequestMapping("/SiteMap/")
public class SiteMapRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(SiteMapRestController.class);
	@Autowired
	private SiteMapService siteMapServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addSitemap(@RequestBody SiteMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addSitemap {}",obj);
			siteMapServiceImpl.insertSiteMap(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addSitemap异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getSitemapById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getSitemapById  {}",id);
			SiteMap SiteMap = siteMapServiceImpl.selectSiteMapById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(SiteMap);
		} catch(Exception e) {
			log.error("getSitemap异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateSitemapById(@RequestBody SiteMap obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateSitemapById  {}",obj);
			siteMapServiceImpl.updateSiteMapById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateSitemapById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findSitemapList(@RequestBody SiteMap  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findSitemapList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<SiteMap> list = (Page<SiteMap>) siteMapServiceImpl.findSiteMapList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findSitemapList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
