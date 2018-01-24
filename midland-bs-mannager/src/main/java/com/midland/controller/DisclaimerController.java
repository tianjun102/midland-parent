package com.midland.controller;

import com.midland.web.model.Disclaimer;
import com.midland.web.model.user.User;
import com.midland.web.service.DisclaimerService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/disclaimer/")
public class DisclaimerController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(DisclaimerController.class);
	@Autowired
	private DisclaimerService disclaimerServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index1")
	public String disclaimerIndex(Disclaimer disclaimer,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "disclaimer/disclaimerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddDisclaimer(Disclaimer disclaimer,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "disclaimer/addDisclaimer";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addDisclaimer(Disclaimer disclaimer,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addDisclaimer {}",disclaimer);
			if (StringUtils.isEmpty(disclaimer.getCityId())) {
				User user = MidlandHelper.getCurrentUser(request);
				disclaimer.setCityId(user.getCityId());
				disclaimer.setCityName(user.getCityName());
			}
			disclaimer.setCreateTime(MidlandHelper.getCurrentTime());
			disclaimerServiceImpl.insertDisclaimer(disclaimer);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addDisclaimer异常 {}",disclaimer,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_disclaimer")
	public String getDisclaimerById(Integer id,Model model) {
		log.info("getDisclaimerById  {}",id);
		Disclaimer result = disclaimerServiceImpl.selectDisclaimerById(id);
		model.addAttribute("item",result);
		return "disclaimer/updateDisclaimer";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteDisclaimerById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteDisclaimerById  {}",id);
			disclaimerServiceImpl.deleteDisclaimerById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteDisclaimerById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateDisclaimer(Integer id,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		Disclaimer result = disclaimerServiceImpl.selectDisclaimerById(id);
		model.addAttribute("item",result);
		return "disclaimer/updateDisclaimer";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateDisclaimerById(Disclaimer disclaimer) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateDisclaimerById  {}",disclaimer);
			disclaimerServiceImpl.updateDisclaimerById(disclaimer);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateDisclaimerById  {}",disclaimer,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findDisclaimerList(Disclaimer disclaimer,Model model, HttpServletRequest request) {
		try {
			log.info("findDisclaimerList  {}",disclaimer);
			MidlandHelper.doPage(request);
			Page<Disclaimer> result = (Page<Disclaimer>)disclaimerServiceImpl.findDisclaimerList(disclaimer);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findDisclaimerList  {}",disclaimer,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "disclaimer/disclaimerList";
	}
}
