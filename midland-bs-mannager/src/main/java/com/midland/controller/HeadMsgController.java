package com.midland.controller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.HeadMsg;
import com.midland.web.model.user.User;
import com.midland.web.service.HeadMsgService;
import com.midland.base.BaseFilter;
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
@RequestMapping("/headMsg/")
public class HeadMsgController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(HeadMsgController.class);
	@Autowired
	private HeadMsgService headMsgServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String headMsgIndex(HeadMsg headMsg,Model model,HttpServletRequest request) throws Exception {
		List<ParamObject> obj1 = JsonMapReader.getMap("is_delete");
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		model.addAttribute("isDeletes",obj1);
		return "headMsg/headMsgIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddHeadMsg(HeadMsg headMsg,Model model) throws Exception {
		return "headMsg/addHeadMsg";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addHeadMsg(HeadMsg headMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addHeadMsg {}",headMsg);
			headMsg.setCreateTime(MidlandHelper.getCurrentTime());
			headMsgServiceImpl.insertHeadMsg(headMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addHeadMsg异常 {}",headMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_headMsg")
	public String getHeadMsgById(Integer id,Model model) {
		log.info("getHeadMsgById  {}",id);
		HeadMsg result = headMsgServiceImpl.selectHeadMsgById(id);
		model.addAttribute("item",result);
		return "headMsg/updateHeadMsg";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteHeadMsgById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteHeadMsgById  {}",id);
			headMsgServiceImpl.deleteHeadMsgById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteHeadMsgById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateHeadMsg(Integer id,Model model) throws Exception {
		HeadMsg result = headMsgServiceImpl.selectHeadMsgById(id);
		model.addAttribute("item",result);
		return "headMsg/updateHeadMsg";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateHeadMsgById(HeadMsg headMsg) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateHeadMsgById  {}",headMsg);
			headMsgServiceImpl.updateHeadMsgById(headMsg);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateHeadMsgById  {}",headMsg,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findHeadMsgList(HeadMsg headMsg,Model model, HttpServletRequest request) {
		try {
			log.info("findHeadMsgList  {}",headMsg);
			MidlandHelper.doPage(request);
			Page<HeadMsg> result = (Page<HeadMsg>)headMsgServiceImpl.findHeadMsgList(headMsg);
			Paginator paginator=result.getPaginator();
			List<ParamObject> obj1 = JsonMapReader.getMap("is_delete");
			model.addAttribute("isDeletes",obj1);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findHeadMsgList  {}",headMsg,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "headMsg/headMsgList";
	}
}
