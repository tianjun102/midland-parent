package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.SiteProtocol;
import com.midland.web.service.SiteProtocolService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/siteProtocol/")
public class SiteProtocolRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(SiteProtocolRestController.class);
	@Autowired
	private SiteProtocolService siteProtocolServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addSiteProtocol(@RequestBody SiteProtocol obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addSiteProtocol {}",obj);
			siteProtocolServiceImpl.insertSiteProtocol(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addSiteProtocol异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getSiteProtocolById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getSiteProtocolById  {}",id);
			SiteProtocol siteProtocol = siteProtocolServiceImpl.selectSiteProtocolById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(siteProtocol);
		} catch(Exception e) {
			log.error("getSiteProtocol异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateSiteProtocolById(@RequestBody SiteProtocol obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateSiteProtocolById  {}",obj);
			siteProtocolServiceImpl.updateSiteProtocolById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateSiteProtocolById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findSiteProtocolList(@RequestBody SiteProtocol  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findSiteProtocolList  {}",obj);
			MidlandHelper.doPage(request);
			if (StringUtils.isEmpty(obj.getCityId()) || obj.getSource()==null){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("cityId或source不能为空");
				return result;
			}
			obj.setIsDelete(Contant.isNotDelete);
			Page<SiteProtocol> list = (Page<SiteProtocol>)siteProtocolServiceImpl.findSiteProtocolList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findSiteProtocolList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
