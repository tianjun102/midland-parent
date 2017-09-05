package com.midland.web.controller;

import com.midland.web.model.Quotation;
import com.midland.web.service.QuotationService;
import com.midland.web.controller.base.BaseController;
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
@RequestMapping("/quotation/")
public class QuotationController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(QuotationController.class);
	@Autowired
	private QuotationService quotationServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String quotationIndex(Quotation quotation,Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types",paramObjects);
		model.addAttribute("isNew",quotation.getIsNew());
		return "quotation/quotationIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddQuotation(Quotation quotation,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types",paramObjects);
		return "quotation/addQuotation";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQuotation(Quotation quotation) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addQuotation {}",quotation);
			quotationServiceImpl.insertQuotation(quotation);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addQuotation异常 {}",quotation,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_quotation")
	public String getQuotationById(Integer id,Model model) {
		log.info("getQuotationById  {}",id);
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		model.addAttribute("item",result);
		return "quotation/updateQuotation";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQuotationById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteQuotationById  {}",id);
			quotationServiceImpl.deleteQuotationById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteQuotationById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateQuotation(Integer id,Model model) throws Exception {
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types",paramObjects);
		settingService.getAllProvinceList(model);
		model.addAttribute("item",result);
		return "quotation/updateQuotation";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQuotationById(Quotation quotation) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateQuotationById  {}",quotation);
			quotationServiceImpl.updateQuotationById(quotation);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateQuotationById  {}",quotation,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findQuotationList(Quotation quotation,Model model, HttpServletRequest request) {
		try {
			log.info("findQuotationList  {}",quotation);
			MidlandHelper.doPage(request);
			Page<Quotation> result = (Page<Quotation>)quotationServiceImpl.findQuotationList(quotation);
			Paginator paginator=result.getPaginator();
			List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
			model.addAttribute("types",paramObjects);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findQuotationList  {}",quotation,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "quotation/quotationList";
	}
}
