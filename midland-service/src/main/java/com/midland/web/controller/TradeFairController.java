package com.midland.web.controller;

import com.midland.web.model.Area;
import com.midland.web.model.TradeFair;
import com.midland.web.model.user.User;
import com.midland.web.service.SettingService;
import com.midland.web.service.TradeFairService;
import com.midland.web.controller.base.BaseController;
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
@RequestMapping("/tradeFair/")
public class TradeFairController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(TradeFairController.class);
	@Autowired
	private TradeFairService tradeFairServiceImpl;
	

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String tradeFairIndex(TradeFair tradeFair,Model model) throws Exception {
		return "tradeFair/tradeFairIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddTradeFair(TradeFair tradeFair,Model model) throws Exception {
		
		return "tradeFair/addTradeFair";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addTradeFair(TradeFair tradeFair,HttpServletRequest request) throws Exception {
		Map map = new HashMap<>();
		try {
			log.info("addTradeFair {}",tradeFair);
			User user = (User)request.getSession().getAttribute("userInfo");
			tradeFair.setOperatorId(user.getId());
			tradeFair.setOperatorName(user.getUserCnName());
			tradeFairServiceImpl.insertTradeFair(tradeFair);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addTradeFair异常 {}",tradeFair,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_tradeFair")
	public String getTradeFairById(Integer id,Model model) {
		log.info("getTradeFairById  {}",id);
		TradeFair result = tradeFairServiceImpl.selectTradeFairById(id);
		model.addAttribute("item",result);
		return "tradeFair/updateTradeFair";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteTradeFairById(Integer id)throws Exception {
		Map map = new HashMap<>();
		try {
			log.info("deleteTradeFairById  {}",id);
			tradeFairServiceImpl.deleteTradeFairById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteTradeFairById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateTradeFair(Integer id,Model model) throws Exception {
		TradeFair result = tradeFairServiceImpl.selectTradeFairById(id);
		model.addAttribute("item",result);
		return "tradeFair/updateTradeFair";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateTradeFairById(TradeFair tradeFair) throws Exception {
		Map map = new HashMap<>();
		try {
			log.info("updateTradeFairById  {}",tradeFair);
			tradeFairServiceImpl.updateTradeFairById(tradeFair);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateTradeFairById  {}",tradeFair,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findTradeFairList(TradeFair tradeFair,Model model, HttpServletRequest request) {
		try {
			log.info("findTradeFairList  {}",tradeFair);
			MidlandHelper.doPage(request);
			Page<TradeFair> result = (Page<TradeFair>)tradeFairServiceImpl.findTradeFairList(tradeFair);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findTradeFairList  {}",tradeFair,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "tradeFair/tradeFairList";
	}
}
