package com.midland.web.annocontroller;

import com.midland.config.MidlandConfig;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.model.Comment;
import com.midland.web.model.Entrust;
import com.midland.web.service.CommentService;
import com.midland.web.service.EntrustService;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;

import java.util.List;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/entrust/")
public class EntrustRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(EntrustRestController.class);
	@Autowired
	private EntrustService entrustServiceImpl;
	@Autowired
	private ApiHelper apiHelper;
	@Autowired
	private PublicService publicServiceImpl;
	@Autowired
	private CommentService commentServiceImpl;
@Autowired
	private MidlandConfig midlandConfig;

	/**
	 * 新增买房委托
	 **/
	@RequestMapping("buy/add")
	public Object addBuyEntrust(@RequestBody Entrust obj) throws Exception {
		obj.setEntrustType(Contant.ENTRUST_BUY);
		return addEntrust(obj);
	}
	@RequestMapping("list")
	public Object findList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}
	@RequestMapping("buy/list")
	public Object findEntrustBuyList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}

	/**
	 * 新增租房委托
	 **/
	@RequestMapping("rentIn/add")
	public Object addRentInEntrust(@RequestBody Entrust obj) throws Exception {
		obj.setEntrustType(Contant.ENTRUST_RENT_IN);
		return addEntrust(obj);
	}
	@RequestMapping("rentIn/list")
	public Object findEntrustRentInList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}
	/**
	 * 新增出租委托
	 **/
	@RequestMapping("rentOut/add")
	public Object addRentOutEntrust(@RequestBody Entrust obj) throws Exception {
		obj.setEntrustType(Contant.ENTRUST_RENT_OUT);
		return addEntrust(obj);
	}
	@RequestMapping("rentOut/list")
	public Object findEntrustRentOutList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}
	/**
	 * 新增卖房委托
	 **/
	@RequestMapping("sale/add")
	public Object addSaleEntrust(@RequestBody Entrust obj) throws Exception {
		obj.setEntrustType(Contant.ENTRUST_SALE);
		return addEntrust(obj);
	}
	@RequestMapping("sale/list")
	public Object findEntrustSaleList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}
	/**
	 * 新增评估委托
	 **/
	@RequestMapping("evaluate/add")
	public Object addEvaluateEntrust(@RequestBody Entrust obj) throws Exception {
		obj.setEntrustType(Contant.ENTRUST_EVALUATE);
		return addEntrust(obj);
	}
	@RequestMapping("evaluate/list")
	public Object findEntrustEvaluateList(@RequestBody Entrust  obj, HttpServletRequest request) {
		return findEntrustList(obj,request);
	}
	private Object addEntrust(Entrust obj) {
		Result result=new Result();
		try {
			log.info("addEntrust {}",obj);
			obj.setEntrustTime(MidlandHelper.getCurrentTime());
			obj.setResetFlag(1);
			obj.setEntrustSn(publicServiceImpl.getCode(Contant.ENTRUST_SN_KEY,"E"));
			if (StringUtils.isNotEmpty(obj.getAgentId())) {
				Comment comment = new Comment();
				comment.setAgentId(obj.getAgentId());
				comment.setType(1);//0=资讯；1=委托；2=预约
				comment.setIsDelete(Contant.isNotDelete);
				Map map = commentServiceImpl.getAvgScore(comment);
				if (map != null){
					Double score = (Double) map.get("score");
					Long count = (Long) map.get("count");
					obj.setAgentScore(score);
					obj.setCommentNum(count.intValue());
				}
			}
			entrustServiceImpl.insertEntrust(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addEntrust异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getEntrustById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getEntrustById  {}",id);
			Entrust entrust = entrustServiceImpl.selectEntrustById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(entrust);
		} catch(Exception e) {
			log.error("getEntrust异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateEntrustById(@RequestBody Entrust obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateEntrustById  {}",obj);
			entrustServiceImpl.updateEntrustById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateEntrustById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	public Object findEntrustList(Entrust  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findEntrustList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Entrust> list = (Page<Entrust>)entrustServiceImpl.findEntrustList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findEntrustList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
