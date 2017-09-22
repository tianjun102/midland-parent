package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.Quotation;
import com.midland.web.model.QuotationSecondHandView;
import com.midland.web.model.QuotationView;
import com.midland.web.service.QuotationService;
import com.midland.web.service.QuotationViewService;
import com.midland.web.service.SettingService;
import com.midland.web.util.Calculate;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SuppressWarnings("all")
@RequestMapping("/quotation/")
public class QuotationController extends BaseFilter {
	
	private Logger log = LoggerFactory.getLogger(QuotationController.class);
	@Autowired
	private QuotationService quotationServiceImpl;
	@Autowired
	private QuotationViewService quotationViewServiceImpl;
	@Autowired
	private SettingService settingService;
	
	/**
	 *
	 **/
	@RequestMapping("index")
	public String quotationIndex(Quotation quotation, Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("isNew", quotation.getIsNew());
		List<Area> list = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		
		model.addAttribute("citys",list);
		return "quotation/quotationIndex";
	}
	
	/**
	 *
	 **/
	@RequestMapping("to_add")
	public String toAddQuotation(Quotation quotation, Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		return "quotation/addQuotation";
	}
	
	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQuotation(Quotation quotation) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("addQuotation {}", quotation);
			quotationServiceImpl.insertQuotation(quotation);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("addQuotation异常 {}", quotation, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 * 查询
	 **/
	@RequestMapping("get_quotation")
	public String getQuotationById(Integer id, Model model) {
		log.info("getQuotationById  {}", id);
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		model.addAttribute("item", result);
		return "quotation/updateQuotation";
	}
	
	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQuotationById(Integer id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("deleteQuotationById  {}", id);
			quotationServiceImpl.deleteQuotationById(id);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("deleteQuotationById  {}", id, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 *
	 **/
	@RequestMapping("to_update")
	public String toUpdateQuotation(Integer id, Model model) throws Exception {
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		Area province = settingService.getCityByCityId(result.getCityId());
		model.addAttribute("area", province);
		model.addAttribute("types", paramObjects);
		settingService.getAllProvinceList(model);
		model.addAttribute("item", result);
		return "quotation/updateQuotation";
	}
	
	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQuotationById(Quotation quotation) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("updateQuotationById  {}", quotation);
			quotationServiceImpl.updateQuotationById(quotation);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("updateQuotationById  {}", quotation, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findQuotationList(Quotation quotation, Model model, HttpServletRequest request) {
		try {
			log.info("findQuotationList  {}", quotation);
			MidlandHelper.doPage(request);
			Page<Quotation> result = (Page<Quotation>) quotationServiceImpl.findQuotationList(quotation);
			Paginator paginator = result.getPaginator();
			List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
			model.addAttribute("types", paramObjects);
			model.addAttribute("paginator", paginator);
			model.addAttribute("items", result);
		} catch (Exception e) {
			log.error("findQuotationList  {}", quotation, e);
			model.addAttribute("paginator", null);
			model.addAttribute("items", null);
		}
		return "quotation/quotationList";
	}
	
	@RequestMapping("showTooltip")
	public String showTooltip(Integer id, QuotationView obj, Model model) throws Exception {
		
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
		
		List<Object> numList = new ArrayList<>();
		List<Object> numRatioList = new ArrayList<>();
		List<Object> acreageList = new ArrayList<>();
		List<Object> acreageRatioList = new ArrayList<>();
		List<Object> priceList = new ArrayList<>();
		List<Object> priceRatioList = new ArrayList<>();
		List<Object> soldNumList = new ArrayList<>();
		List<Object> soldNumRatioList = new ArrayList<>();
		List<Object> soldAcreageList = new ArrayList<>();
		List<Object> soldAcreageRatioList = new ArrayList<>();
		
		List<QuotationView> result = quotationViewServiceImpl.findQuotationViewList(obj);
		for (QuotationView view : result){
			month.add(view.getDataTime());
			//（当前月数据-上个月数据)/上个月数据=当月环比
			numList.add(view.getDealNum());
			getRatio(numRatioList, Double.valueOf(view.getDealNum()),view.getPreDealNum());
			acreageList.add(view.getDealAcreage());
			getRatio(acreageRatioList,Double.valueOf(view.getDealAcreage()),view.getPreDealAcreage());
			priceList.add(view.getPrice());
			getRatio(priceRatioList,Double.valueOf(view.getPrice()),view.getPrePrice());
			soldNumList.add(view.getPrice());
			getRatio(soldNumRatioList,Double.valueOf(view.getSoldNum()),view.getPreSoldNum());
			soldAcreageList.add(view.getSoldArea());
			getRatio(soldAcreageRatioList,Double.valueOf(view.getSoldArea()),view.getPreSoldArea());
			
		
		
		}
		model.addAttribute("months", JSONArray.toJSONString(month));
		model.addAttribute("numList",numList);
		model.addAttribute("numRatioList",numRatioList);
		model.addAttribute("acreageList",acreageList);
		model.addAttribute("acreageRatioList",acreageRatioList);
		model.addAttribute("priceList",acreageList);
		model.addAttribute("priceRatioList",acreageRatioList);
		model.addAttribute("soldNumList",acreageList);
		model.addAttribute("soldNumRatioList",acreageRatioList);
		model.addAttribute("soldAcreageList",acreageList);
		model.addAttribute("soldAcreageRatioList",acreageRatioList);
		return "quotation/contentIndex";
		
	}
	
	private void getRatio(List<Object> numRatioList, Double dealNum,Double preDealNum) {
		double minus = preDealNum==null?dealNum:preDealNum;
		double numRes= Calculate.minus(Double.valueOf(dealNum),minus);
		double numRatio=Calculate.divide(numRes,minus);
		numRatioList.add(Calculate.multiply(numRatio,100.00));
	}
}