package com.midland.web.controller;

import com.midland.web.model.Area;
import com.midland.web.model.QuotationSecondHand;
import com.midland.web.service.QuotationSecondHandService;
import com.midland.web.controller.base.BaseController;
import com.midland.web.service.QuotationService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.slf4j.Logger;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
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
