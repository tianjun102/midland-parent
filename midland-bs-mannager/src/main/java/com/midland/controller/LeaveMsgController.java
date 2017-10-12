package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.LeaveMsg;
import com.midland.web.service.LeaveMsgService;
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
@RequestMapping("/leaveMsg/")
public class LeaveMsgController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(LeaveMsgController.class);
	@Autowired
	private LeaveMsgService leaveMsgServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String leaveMsgIndex(LeaveMsg leaveMsg, Model model) throws Exception {
		return "leaveMsg/leaveMsgIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLeaveMsg(LeaveMsg leaveMsg, Model model) throws Exception {
		return "leaveMsg/addLeaveMsg";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLeaveMsg(LeaveMsg leaveMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addLeaveMsg {}",leaveMsg);
			leaveMsg.setAddTime(MidlandHelper.getCurrentTime());
			leaveMsgServiceImpl.insertLeaveMsg(leaveMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addLeaveMsg异常 {}",leaveMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_leaveMsg")
	public String getLeaveMsgById(Integer id,Model model) {
		log.debug("getLeaveMsgById  {}",id);
		LeaveMsg result = leaveMsgServiceImpl.selectLeaveMsgById(id);
		model.addAttribute("item",result);
		return "leaveMsg/updateLeaveMsg";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteLeaveMsgById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteLeaveMsgById  {}",id);
			leaveMsgServiceImpl.deleteLeaveMsgById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteLeaveMsgById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLeaveMsg(Integer id,Model model) throws Exception {
		LeaveMsg result = leaveMsgServiceImpl.selectLeaveMsgById(id);
		model.addAttribute("item",result);
		return "leaveMsg/updateLeaveMsg";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLeaveMsgById(LeaveMsg leaveMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateLeaveMsgById  {}",leaveMsg);
			leaveMsgServiceImpl.updateLeaveMsgById(leaveMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateLeaveMsgById  {}",leaveMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLeaveMsgList(LeaveMsg leaveMsg, Model model, HttpServletRequest request) {
		try {
			log.debug("findLeaveMsgList  {}",leaveMsg);
			MidlandHelper.doPage(request);
			Page<LeaveMsg> result = (Page<LeaveMsg>)leaveMsgServiceImpl.findLeaveMsgList(leaveMsg);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findLeaveMsgList  {}",leaveMsg,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "leaveMsg/leaveMsgList";
	}
}
