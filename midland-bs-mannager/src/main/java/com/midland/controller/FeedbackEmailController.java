package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.ExportModel;
import com.midland.web.model.FeedbackEmail;
import com.midland.web.model.user.User;
import com.midland.web.service.FeedbackEmailService;
import com.midland.web.service.SettingService;
import com.midland.web.service.impl.EntrustServiceImpl;
import com.midland.web.util.PoiExcelExport;
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
import javax.servlet.http.HttpServletResponse;

@Controller
@SuppressWarnings("all")
@RequestMapping("/feedbackEmail/")
public class FeedbackEmailController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(FeedbackEmailController.class);
	@Autowired
	private FeedbackEmailService feedbackEmailServiceImpl;
	@Autowired
	private SettingService settingService;
	@Autowired
	private EntrustServiceImpl entrustServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String feedbackEmailIndex(FeedbackEmail feedbackEmail,Model model,HttpServletRequest request) throws Exception {
		/*Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);*/
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("emailType",feedbackEmail.getEmailType());
		model.addAttribute("cityId",user.getCityId());
		return "feedbackEmail/feedbackEmailIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddFeedbackEmail(FeedbackEmail feedbackEmail,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("emailType",feedbackEmail.getEmailType());
		model.addAttribute("cityList",cityList);
		return "feedbackEmail/addFeedbackEmail";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addFeedbackEmail(FeedbackEmail feedbackEmail) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("addFeedbackEmail {}",feedbackEmail);
			feedbackEmailServiceImpl.insertFeedbackEmail(feedbackEmail);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addFeedbackEmail异常 {}",feedbackEmail,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_feedbackEmail")
	public String getFeedbackEmailById(Integer id,Model model) {
		log.debug("getFeedbackEmailById  {}",id);
		FeedbackEmail result = feedbackEmailServiceImpl.selectFeedbackEmailById(id);
		model.addAttribute("item",result);
		return "feedbackEmail/updateFeedbackEmail";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteFeedbackEmailById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("deleteFeedbackEmailById  {}",id);
			feedbackEmailServiceImpl.deleteFeedbackEmailById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteFeedbackEmailById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateFeedbackEmail(Integer id,Model model) throws Exception {
		FeedbackEmail result = feedbackEmailServiceImpl.selectFeedbackEmailById(id);
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "feedbackEmail/updateFeedbackEmail";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateFeedbackEmailById(FeedbackEmail feedbackEmail) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateFeedbackEmailById  {}",feedbackEmail);
			feedbackEmailServiceImpl.updateFeedbackEmailById(feedbackEmail);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateFeedbackEmailById  {}",feedbackEmail,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findFeedbackEmailList(FeedbackEmail feedbackEmail,Model model, HttpServletRequest request) {
		try {
			log.debug("findFeedbackEmailList  {}",feedbackEmail);
			MidlandHelper.doPage(request);
			Page<FeedbackEmail> result = (Page<FeedbackEmail>)feedbackEmailServiceImpl.findFeedbackEmailList(feedbackEmail);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findFeedbackEmailList  {}",feedbackEmail,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "feedbackEmail/feedbackEmailList";
	}


	@RequestMapping("/export")
	public void userInfoExportExcel(FeedbackEmail feedbackEmail, HttpServletResponse response,HttpServletRequest request) throws Exception {
		List<FeedbackEmail> dataList = feedbackEmailServiceImpl.findFeedbackEmailList(feedbackEmail);
		PoiExcelExport pee = new PoiExcelExport(response,"反馈邮箱","sheet1");
		//调用
		List<ExportModel> exportModels=new ArrayList<>();
		for (FeedbackEmail feedbackEmail1:dataList){
			ExportModel exportModel = new ExportModel();
			exportModel.setModelName1(feedbackEmail1.getId().toString());
			exportModel.setModelName2(feedbackEmail1.getEmail());
			exportModel.setModelName3("0".equals(feedbackEmail1.getType())?"总部":"分部");
			exportModel.setModelName4(feedbackEmail1.getCityName());
			exportModels.add(exportModel);
		}
		String titleColumn[] = {"modelName1","modelName2","modelName3","modelName4"};
		String titleName[] = {"编号","邮箱","类型","城市"};
		int titleSize[] = {13,13,13,13};
		//其他设置 set方法可全不调用
		pee.wirteExcel(titleColumn, titleName, titleSize, exportModels,request);
	}

}
