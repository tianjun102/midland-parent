package com.midland.web.annocontroller;

import com.midland.web.model.HotHand;
import com.midland.web.service.HotHandService;
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
@RequestMapping("/hotHand/")
public class HotHandRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(HotHandRestController.class);
	@Autowired
	private HotHandService hotHandServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addHotHand(@RequestBody HotHand obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addHotHand {}",obj);
			hotHandServiceImpl.insertHotHand(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addHotHand异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getHotHandById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getHotHandById  {}",id);
			HotHand hotHand = hotHandServiceImpl.selectHotHandById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(hotHand);
		} catch(Exception e) {
			log.error("getHotHand异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateHotHandById(@RequestBody HotHand obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateHotHandById  {}",obj);
			hotHandServiceImpl.updateHotHandById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateHotHandById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findHotHandList(@RequestBody HotHand  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findHotHandList  {}",obj);
			MidlandHelper.doPage(request);
			Page<HotHand> list = (Page<HotHand>)hotHandServiceImpl.findHotHandList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findHotHandList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
