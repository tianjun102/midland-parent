package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.LiaisonRecord;
import com.midland.web.service.LiaisonRecordService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/liaisonRecord/")
public class LiaisonRecordController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(LiaisonRecordController.class);
	@Autowired
	private LiaisonRecordService liaisonRecordServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String liaisonRecordIndex(LiaisonRecord liaisonRecord, Model model) throws Exception {
		List<ParamObject> list = JsonMapReader.getMap("liaisonRecord_category");
		model.addAttribute("categorys",list);
		return "liaisonRecord/liaisonRecordIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLiaisonRecord(LiaisonRecord liaisonRecord, Model model) throws Exception {
		return "liaisonRecord/addLiaisonRecord";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLiaisonRecord(LiaisonRecord liaisonRecord) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addLiaisonRecord {}",liaisonRecord);
			liaisonRecordServiceImpl.insertLiaisonRecord(liaisonRecord);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addLiaisonRecord异常 {}",liaisonRecord,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_liaisonRecord")
	public String getLiaisonRecordById(Integer id,Model model) {
		log.info("getLiaisonRecordById  {}",id);
		LiaisonRecord result = liaisonRecordServiceImpl.selectLiaisonRecordById(id);
		model.addAttribute("item",result);
		return "liaisonRecord/updateLiaisonRecord";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteLiaisonRecordById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteLiaisonRecordById  {}",id);
			liaisonRecordServiceImpl.deleteLiaisonRecordById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteLiaisonRecordById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLiaisonRecord(Integer id,Model model) throws Exception {
		LiaisonRecord result = liaisonRecordServiceImpl.selectLiaisonRecordById(id);
		model.addAttribute("item",result);
		return "liaisonRecord/updateLiaisonRecord";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLiaisonRecordById(LiaisonRecord liaisonRecord) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateLiaisonRecordById  {}",liaisonRecord);
			liaisonRecordServiceImpl.updateLiaisonRecordById(liaisonRecord);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateLiaisonRecordById  {}",liaisonRecord,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLiaisonRecordList(LiaisonRecord liaisonRecord, Model model, HttpServletRequest request) {
		try {
			log.info("findLiaisonRecordList  {}",liaisonRecord);
			MidlandHelper.doPage(request);
			Page<LiaisonRecord> result = (Page<LiaisonRecord>)liaisonRecordServiceImpl.findLiaisonRecordList(liaisonRecord);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findLiaisonRecordList  {}",liaisonRecord,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "liaisonRecord/liaisonRecordList";
	}
}
