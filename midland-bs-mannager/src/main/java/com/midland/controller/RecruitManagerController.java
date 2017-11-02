package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.core.util.DateUtils;
import com.midland.web.model.Area;
import com.midland.web.model.RecruitManager;
import com.midland.web.model.user.User;
import com.midland.web.service.RecruitManagerService;
import com.midland.web.service.SettingService;
import org.slf4j.Logger;

import java.util.ArrayList;
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
/**
 * 招聘管理控制层
 */
@RequestMapping("/recruitManager/")
public class RecruitManagerController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(RecruitManagerController.class);
	@Autowired
	private RecruitManagerService recruitManagerServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String recruitManagerIndex(RecruitManager recruitManager,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		if(user.getIsSuper()==null){
			model.addAttribute("cityId",user.getCityId());
		}
		model.addAttribute("isSuper",user.getIsSuper());
		return "recruitManager/recruitManagerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddRecruitManager(RecruitManager recruitManager,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "recruitManager/addRecruitManager";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addRecruitManager(RecruitManager recruitManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addRecruitManager {}",recruitManager);
			recruitManagerServiceImpl.insertRecruitManager(recruitManager);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addRecruitManager异常 {}",recruitManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_recruitManager")
	public String getRecruitManagerById(Integer id,Model model) {
		log.debug("getRecruitManagerById  {}",id);
		RecruitManager result = recruitManagerServiceImpl.selectRecruitManagerById(id);
		model.addAttribute("item",result);
		return "recruitManager/updateRecruitManager";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteRecruitManagerById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteRecruitManagerById  {}",id);
			recruitManagerServiceImpl.deleteRecruitManagerById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteRecruitManagerById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateRecruitManager(Integer id,Model model) throws Exception {
		RecruitManager result = recruitManagerServiceImpl.selectRecruitManagerById(id);
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "recruitManager/updateRecruitManager";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateRecruitManagerById(RecruitManager recruitManager) throws Exception {
		Map<String,Object> map = new HashMap<>();
		if(recruitManager.getReleaseStatus()!=null&&recruitManager.getReleaseStatus()==0){
			recruitManager.setReleaseTime(DateUtils.nowDateToStringYYMMDDHHmmss());
		}
		try {
			log.debug("updateRecruitManagerById  {}",recruitManager);
			recruitManagerServiceImpl.updateRecruitManagerById(recruitManager);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateRecruitManagerById  {}",recruitManager,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findRecruitManagerList(RecruitManager recruitManager,Model model, HttpServletRequest request) {
		try {
			log.debug("findRecruitManagerList  {}",recruitManager);
			MidlandHelper.doPage(request);
			Page<RecruitManager> result = (Page<RecruitManager>)recruitManagerServiceImpl.findRecruitManagerList(recruitManager);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findRecruitManagerList  {}",recruitManager,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "recruitManager/recruitManagerList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,RecruitManager recruitManager) throws Exception {
		List<RecruitManager> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			RecruitManager comment1 = new RecruitManager();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(recruitManager.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			recruitManagerServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
