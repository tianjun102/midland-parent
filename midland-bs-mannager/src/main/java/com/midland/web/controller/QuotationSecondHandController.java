package com.midland.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.midland.web.model.Area;
import com.midland.web.model.QuotationSecondHand;
import com.midland.web.model.QuotationSecondHandView;
import com.midland.web.service.QuotationSecondHandService;
import com.midland.web.controller.base.BaseController;
import com.midland.web.service.QuotationSecondHandViewService;
import com.midland.web.service.SettingService;
import com.midland.web.util.Calculate;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(QuotationSecondHandController.class);
	@Autowired
	private QuotationSecondHandService quotationSecondHandServiceImpl;
@Autowired
	private QuotationSecondHandViewService quotationSecondHandViewService;

	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String quotationSecondHandIndex(QuotationSecondHand quotationSecondHand,Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("isNew", "0");
		List<Area> list = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		
		model.addAttribute("citys",list);
		return "quotationSecondHand/quotationSecondHandIndex";
	}
	/**
	 *
	 **/
	@RequestMapping("toolsTip")
	public String toolsTip(QuotationSecondHandView obj, Model model) throws Exception {
		if (StringUtils.isEmpty(obj.getAreaId())){
			obj.setAreaId("0");
		}
		if (StringUtils.isEmpty(obj.getCityId())){
			obj.setCityId("085");
		}
		if (obj.getType()==null){
			obj.setType(0);
		}
		if (obj.getStartTime()==null){
			Date date= new Date();
			obj.setStartTime(MidlandHelper.getMonth(date,-12));
		}
		if (obj.getEndTime() == null){
			obj.setEndTime(MidlandHelper.getCurrentTime());
		}
		List<String> month = new ArrayList<>();
		List<Object> numRatioList = new ArrayList<>();
		List<Object> acreageRatioList = new ArrayList<>();
		List<Object> numList = new ArrayList<>();
		List<Object> acreageList = new ArrayList<>();
		List<QuotationSecondHandView> list = quotationSecondHandViewService.toolTip(obj);
		for (QuotationSecondHandView view : list){
			month.add(view.getDataTime());
			//（当前月数据-上个月数据)/上个月数据=当月环比
			double minus = view.getPreNum()==null?view.getDealNum():view.getPreNum();
			double numRes=Calculate.minus(Double.valueOf(view.getDealNum()),minus);
			double numRatio=Calculate.divide(numRes,minus);
			numRatioList.add(Calculate.multiply(numRatio,100.00));
			numList.add(view.getDealNum());
			acreageList.add(view.getDealAcreage());
			
			String minus1 = view.getPreAcreage()==null?view.getDealAcreage():view.getPreAcreage();
			double acreageRes=Calculate.minus(Double.valueOf(view.getDealAcreage()),Double.valueOf(minus1));
			double acreageRatio=Calculate.divide(acreageRes,Double.valueOf(minus1));
			acreageRatioList.add(acreageRatio);
		}
		model.addAttribute("months", JSONArray.toJSONString(month));
		model.addAttribute("numList",numList);
		model.addAttribute("acreageList",acreageList);
		model.addAttribute("numRatioList",numRatioList);
		
		model.addAttribute("acreageRatioList",acreageRatioList);
		return "quotationSecondHand/contentIndex";
	}
	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddQuotationSecondHand(QuotationSecondHand quotationSecondHand,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		return "quotationSecondHand/addQuotationSecondHand";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addQuotationSecondHand {}",quotationSecondHand);
			quotationSecondHandServiceImpl.insertQuotationSecondHand(quotationSecondHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addQuotationSecondHand异常 {}",quotationSecondHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_quotationSecondHand")
	public String getQuotationSecondHandById(Integer id,Model model) {
		log.info("getQuotationSecondHandById  {}",id);
		QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
		model.addAttribute("item",result);
		return "quotationSecondHand/updateQuotationSecondHand";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQuotationSecondHandById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteQuotationSecondHandById  {}",id);
			quotationSecondHandServiceImpl.deleteQuotationSecondHandById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteQuotationSecondHandById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateQuotationSecondHand(Integer id,Model model) throws Exception {
		QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		Area province = settingService.getCityByCityId(result.getCityId());
		model.addAttribute("area", province);
		model.addAttribute("types", paramObjects);
		settingService.getAllProvinceList(model);
		model.addAttribute("item",result);
		return "quotationSecondHand/updateQuotationSecondHand";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateQuotationSecondHandById  {}",quotationSecondHand);
			quotationSecondHandServiceImpl.updateQuotationSecondHandById(quotationSecondHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateQuotationSecondHandById  {}",quotationSecondHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findQuotationSecondHandList(QuotationSecondHand quotationSecondHand,Model model, HttpServletRequest request) {
		try {
			log.info("findQuotationSecondHandList  {}",quotationSecondHand);
			MidlandHelper.doPage(request);
			Page<QuotationSecondHand> result = (Page<QuotationSecondHand>)quotationSecondHandServiceImpl.findQuotationSecondHandList(quotationSecondHand);
			Paginator paginator=result.getPaginator();
			List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
			model.addAttribute("types", paramObjects);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findQuotationSecondHandList  {}",quotationSecondHand,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "quotationSecondHand/quotationSecondHandList";
	}
}
