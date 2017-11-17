package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.PageConf;
import com.midland.web.service.PageConfService;
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
@RequestMapping("/pageConf/")
public class PageConfRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(PageConfRestController.class);
	@Autowired
	private PageConfService pageConfServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addPageConf(@RequestBody PageConf obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addPageConf {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			pageConfServiceImpl.insertPageConf(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addPageConf异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getPageConfById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getPageConfById  {}",id);
			PageConf pageConf = pageConfServiceImpl.selectPageConfById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(pageConf);
		} catch(Exception e) {
			log.error("getPageConf异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updatePageConfById(@RequestBody PageConf obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updatePageConfById  {}",obj);
			pageConfServiceImpl.updatePageConfById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updatePageConfById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findPageConfList(@RequestBody PageConf  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			obj.setBaiduShow(0);
			obj.setMetaShow(0);
			log.info("findPageConfList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<PageConf> list = (Page<PageConf>)pageConfServiceImpl.findPageConfList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findPageConfList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
