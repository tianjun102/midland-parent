package com.midland.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.controller.base.BaseController;
import com.midland.web.model.Area;
import com.midland.web.model.Quotation;
import com.midland.web.service.QuotationService;
import com.midland.web.service.SettingService;
import com.midland.web.util.Calculate;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/quotation/")
public class QuotationController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(QuotationController.class);
	@Autowired
	private QuotationService quotationServiceImpl;
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
	public String showTooltip(Integer id, Quotation quotation, Model model) throws Exception {
		List<Map> result = quotationServiceImpl.tooltip(quotation);
		List<String> month = new ArrayList<>();
		List<Double> data = new ArrayList<>();
		List<Double> ratios = new ArrayList<>();
		for (Map map : result) {
			String mon = (String) map.get("months");
			Double cur = (Double) map.get("cur");
			Double pre = (Double) map.get("pre");
			month.add(mon);
			data.add(cur);
			if (pre == null || pre == 0) {
				
				if (cur==null || cur==0){
					cur=0.0;
					pre=1.0;
				}else {
					pre = cur;
				}
			}
			double ratio = Calculate.divide(cur, pre);
			ratio = Calculate.multiply(ratio,100.00);
			ratios.add(ratio);
		}
		model.addAttribute("month", JSONArray.toJSONString(month));
		model.addAttribute("data", data);
		model.addAttribute("ratios", ratios);
		return "quotation/contentIndex";
	}
}