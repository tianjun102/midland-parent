package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.LoanType;
import com.midland.web.model.user.User;
import com.midland.web.service.LoanTypeService;
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
@RequestMapping("/loanType/")
public class LoanTypeController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(LoanTypeController.class);
	@Autowired
	private LoanTypeService loanTypeServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String loanTypeIndex(LoanType loanType,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "loanType/loanTypeIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLoanType(LoanType loanType,Model model,HttpServletRequest request) throws Exception {
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "loanType/addLoanType";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLoanType(LoanType loanType) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addLoanType {}",loanType);
			loanTypeServiceImpl.insertLoanType(loanType);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addLoanType异常 {}",loanType,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_loanType")
	public String getLoanTypeById(Integer id,Model model) {
		log.info("getLoanTypeById  {}",id);
		LoanType result = loanTypeServiceImpl.selectLoanTypeById(id);
		model.addAttribute("item",result);
		return "loanType/updateLoanType";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteLoanTypeById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteLoanTypeById  {}",id);
			loanTypeServiceImpl.deleteLoanTypeById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteLoanTypeById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLoanType(Integer id,Model model,HttpServletRequest request) throws Exception {
		LoanType result = loanTypeServiceImpl.selectLoanTypeById(id);
		model.addAttribute("item",result);
		List<Area> list = settingService.queryAllCityByRedis();
		model.addAttribute("citys",list);
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
			model.addAttribute("cityName",user.getCityName());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "loanType/updateLoanType";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLoanTypeById(LoanType loanType) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateLoanTypeById  {}",loanType);
			loanTypeServiceImpl.updateLoanTypeById(loanType);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateLoanTypeById  {}",loanType,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLoanTypeList(LoanType loanType,Model model, HttpServletRequest request) {
		try {
			log.info("findLoanTypeList  {}",loanType);
			MidlandHelper.doPage(request);
			Page<LoanType> result = (Page<LoanType>)loanTypeServiceImpl.findLoanTypeList(loanType);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findLoanTypeList  {}",loanType,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "loanType/loanTypeList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,LoanType loanType) throws Exception {
		List<LoanType> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			LoanType comment1 = new LoanType();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(loanType.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			loanTypeServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 新增
	 **/
	@RequestMapping("addDesc")
	@ResponseBody
	public Object addDesc(LoanType loanType) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addIntoMidland {}",loanType);
			if(loanType.getId()!=null){
				loanTypeServiceImpl.updateLoanTypeById(loanType);
			}else {
				loanTypeServiceImpl.insertLoanType(loanType);
			}
			map.put("state",0);
		} catch(Exception e) {
			log.error("addIntoMidland异常 {}",loanType,e);
			map.put("state",-1);
		}
		return map;
	}

	@RequestMapping("descIndex")
	public String descIndex(LoanType loanType,Model model,HttpServletRequest request) throws Exception {
		loanType = loanTypeServiceImpl.findLoanType(loanType);
		model.addAttribute("items",loanType);
		return "loanType/descIndex";
	}
}
