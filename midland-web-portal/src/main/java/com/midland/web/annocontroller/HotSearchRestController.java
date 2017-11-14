package com.midland.web.annocontroller;

import com.midland.web.model.HotSearch;
import com.midland.web.service.HotSearchService;
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
@RequestMapping("/hotSearch/")
public class HotSearchRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(HotSearchRestController.class);
	@Autowired
	private HotSearchService hotSearchServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addHotSearch(@RequestBody HotSearch obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addHotSearch {}",obj);
			hotSearchServiceImpl.insertHotSearch(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addHotSearch异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getHotSearchById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getHotSearchById  {}",id);
			HotSearch hotSearch = hotSearchServiceImpl.selectHotSearchById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(hotSearch);
		} catch(Exception e) {
			log.error("getHotSearch异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateHotSearchById(@RequestBody HotSearch obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateHotSearchById  {}",obj);
			hotSearchServiceImpl.updateHotSearchById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateHotSearchById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findHotSearchList(@RequestBody HotSearch  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			obj.setIsShow(0);
			log.info("findHotSearchList  {}",obj);
			MidlandHelper.doPage(request);
			Page<HotSearch> list = (Page<HotSearch>)hotSearchServiceImpl.findHotSearchList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findHotSearchList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
