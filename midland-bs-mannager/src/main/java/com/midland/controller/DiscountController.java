package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.Discount;
import com.midland.web.model.user.User;
import com.midland.web.service.DiscountService;
import com.midland.web.service.SettingService;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
@RequestMapping("/discount/")
public class DiscountController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(DiscountController.class);
	@Autowired
	private DiscountService discountServiceImpl;
	@Autowired
	private SettingService settingService;
	/**
	 * 
	 **/
	@RequestMapping("index")
	public String discountIndex(Discount discount,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("cityId",user.getCityId());
		return "discount/discountIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddDiscount(Discount discount,Model model) throws Exception {
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);
		return "discount/addDiscount";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addDiscount(Discount discount) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addDiscount {}",discount);
			discountServiceImpl.insertDiscount(discount);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addDiscount异常 {}",discount,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_discount")
	public String getDiscountById(Integer id,Model model) {
		log.info("getDiscountById  {}",id);
		Discount result = discountServiceImpl.selectDiscountById(id);
		model.addAttribute("item",result);
		return "discount/updateDiscount";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteDiscountById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteDiscountById  {}",id);
			discountServiceImpl.deleteDiscountById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteDiscountById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateDiscount(Integer id,Model model) throws Exception {
		Discount result = discountServiceImpl.selectDiscountById(id);
		model.addAttribute("item",result);
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);
		return "discount/updateDiscount";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateDiscountById(Discount discount) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateDiscountById  {}",discount);
			discountServiceImpl.updateDiscountById(discount);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateDiscountById  {}",discount,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findDiscountList(Discount discount,Model model, HttpServletRequest request) {
		try {
			log.info("findDiscountList  {}",discount);
			MidlandHelper.doPage(request);
			Page<Discount> result = (Page<Discount>)discountServiceImpl.findDiscountList(discount);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findDiscountList  {}",discount,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "discount/discountList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,Discount discount) throws Exception {
		List<Discount> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			Discount comment1 = new Discount();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(discount.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			discountServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
