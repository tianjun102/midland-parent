package com.midland.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.ResumeManager;
import com.midland.web.service.ResumeManagerService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Controller
@SuppressWarnings("all")
@RequestMapping("/resumeManager/")
public class ResumeManagerController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(ResumeManagerController.class);
	@Autowired
	private ResumeManagerService resumeManagerServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String resumeManagerIndex(ResumeManager resumeManager,Model model) throws Exception {
		return "resumeManager/resumeManagerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddResumeManager(ResumeManager resumeManager,Model model) throws Exception {
		return "resumeManager/addResumeManager";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addResumeManager(ResumeManager resumeManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addResumeManager {}",resumeManager);
			resumeManagerServiceImpl.insertResumeManager(resumeManager);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addResumeManager异常 {}",resumeManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_resumeManager")
	public String getResumeManagerById(Integer id,Model model) {
		log.info("getResumeManagerById  {}",id);
		ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
		model.addAttribute("item",result);
		return "resumeManager/updateResumeManager";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteResumeManagerById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteResumeManagerById  {}",id);
			resumeManagerServiceImpl.deleteResumeManagerById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteResumeManagerById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateResumeManager(Integer id,Model model) throws Exception {
		ResumeManager result = resumeManagerServiceImpl.selectResumeManagerById(id);
		model.addAttribute("item",result);
		return "resumeManager/updateResumeManager";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateResumeManagerById(ResumeManager resumeManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateResumeManagerById  {}",resumeManager);
			resumeManagerServiceImpl.updateResumeManagerById(resumeManager);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateResumeManagerById  {}",resumeManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findResumeManagerList(ResumeManager resumeManager,Model model, HttpServletRequest request) {
		try {
			log.info("findResumeManagerList  {}",resumeManager);
			MidlandHelper.doPage(request);
			Page<ResumeManager> result = (Page<ResumeManager>)resumeManagerServiceImpl.findResumeManagerList(resumeManager);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findResumeManagerList  {}",resumeManager,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "resumeManager/resumeManagerList";
	}
}
