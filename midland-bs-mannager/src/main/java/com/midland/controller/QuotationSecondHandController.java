package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.controller.PublicUtils.QuotationUtil;
import com.midland.web.model.Area;
import com.midland.web.model.ExportModel;
import com.midland.web.model.QuotationSecondHand;
import com.midland.web.model.QuotationSecondHandView;
import com.midland.web.model.user.User;
import com.midland.web.service.QuotationSecondHandService;
import com.midland.web.service.QuotationSecondHandViewService;
import com.midland.web.service.SettingService;
import com.midland.web.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@Controller
@SuppressWarnings("all")
@RequestMapping("/quotationSecondHand/")
public class QuotationSecondHandController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(QuotationSecondHandController.class);
	@Autowired
	private QuotationSecondHandService quotationSecondHandServiceImpl;
	@Autowired
	private QuotationSecondHandViewService quotationSecondHandViewService;

	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String quotationSecondHandIndex(QuotationSecondHand quotationSecondHand,Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("isNew", "0");
		List<Area> list = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		
		model.addAttribute("citys",list);
		return "quotationSecondHand/quotationSecondHandIndex";
	}
	
	
	@RequestMapping("toolsTip_index")
	public String toolsTipIndex(Model model){
		List<Area> list1 = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("citys",list1);
		model.addAttribute("showType","0");
		return "quotationSecondHand/contentIndex";
	}
	
	/**
	 *
	 **/
	@RequestMapping("toolsTip")
	public String toolsTip(QuotationSecondHandView obj,String distName,String distId,String url,String showType, Model model) throws Exception {
		if (StringUtils.isEmpty(distId)&&StringUtils.isEmpty(distName)){
			obj.setAreaId("0");
		}else{
			obj.setAreaId(distId);
			obj.setAreaName(distName);
		}
		if (StringUtils.isEmpty(obj.getCityId())){
			obj.setCityId("085");
		}
		if (obj.getType()==null){
			obj.setType(0);
		}
		if (obj.getStartTime()==null){
			Date date= new Date();
			obj.setStartTime(MidlandHelper.getMonth(date,-12));
		}
		if (obj.getEndTime() == null){
			obj.setEndTime(MidlandHelper.getCurrentTime());
		}
		List<String> month = new ArrayList<>();
		List<Object> numRatioList = new ArrayList<>();
		List<Object> acreageRatioList = new ArrayList<>();
		List<Object> numList = new ArrayList<>();
		List<Object> acreageList = new ArrayList<>();
		double listMax=0;
		double listMin=0;
		double ratioMax=0;
		double ratioMin=0;
		List<QuotationSecondHandView> list = quotationSecondHandViewService.toolTip(obj);
		for (QuotationSecondHandView view : list){
			month.add(view.getDataTime());
			//（当前月数据-上个月数据)/上个月数据=当月环比
			
			if ("0".equals(showType)) {
				double minus = view.getPreNum() == null ? view.getDealNum() : view.getPreNum();
				numList.add(view.getDealNum());
				Double ratio = QuotationUtil.getRatio(Double.valueOf(view.getDealNum()),view.getPreNum());
				
				numRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,view.getDealNum());
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else{
				Double minus1 = view.getPreAcreage()==null?view.getDealAcreage():view.getPreAcreage();
				double acreageRes=Calculate.minus(Double.valueOf(view.getDealAcreage()),Double.valueOf(minus1));
				double acreageRatio=Calculate.divide(acreageRes,Double.valueOf(minus1));
				acreageList.add(view.getDealAcreage());
				Double ratio = QuotationUtil.getRatio(Double.valueOf(view.getDealAcreage()),view.getPreAcreage());
				listMax=QuotationUtil.getMax(listMax,view.getDealAcreage());
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				acreageRatioList.add(ratio);
				
			}
			
			
		}
		model.addAttribute("numList",numList);
		model.addAttribute("numRatioList",numRatioList);
		model.addAttribute("acreageList",acreageList);
		model.addAttribute("acreageRatioList",acreageRatioList);
		listMax=QuotationUtil.getDoubleUp(listMax);
		listMin=0;
		ratioMax=QuotationUtil.getRatioDoubleUp(ratioMax);
		ratioMin= QuotationUtil.getRatioDoubleUp(ratioMin);
//
		model.addAttribute("months", JSONArray.toJSONString(month));
		model.addAttribute("listMax", listMax);
		model.addAttribute("listMin", listMin);
		model.addAttribute("listStep", (listMax-listMin)/10);
		model.addAttribute("ratioMax", ratioMax);
		model.addAttribute("ratioMin", ratioMin);
		model.addAttribute("ratioStep", (ratioMax-ratioMin)/10);
		
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		
		if (url!=null){
			return "quotationSecondHand/"+url;
		}
		return "quotationSecondHand/dealNumContent";
	}
	
	@RequestMapping("list")
	public String list(QuotationSecondHandView obj, Model model,HttpServletRequest request) throws Exception {
		if (StringUtils.isEmpty(obj.getAreaId())){
			obj.setAreaId("0");
		}
		if (StringUtils.isEmpty(obj.getCityId())){
			obj.setCityId("085");
		}
		if (obj.getType()==null){
			obj.setType(0);
		}
		if (obj.getStartTime()==null){
			Date date= new Date();
			obj.setStartTime(MidlandHelper.getMonth(date,-12));
		}
		if (obj.getEndTime() == null){
			obj.setEndTime(MidlandHelper.getCurrentTime());
		}
		User user = MidlandHelper.getCurrentUser(request);
		//只展示登录用户当前城市的信息
		obj.setCityId(user.getCityId());
		List<QuotationSecondHandView> resultList = new ArrayList<>();
		MidlandHelper.doPage(request);
		Page<QuotationSecondHandView> list = (Page<QuotationSecondHandView>)quotationSecondHandViewService.toolTip(obj);
		//计算环比，（当月套数-上月套数）/上月套数
		for (QuotationSecondHandView view : list){
			//（当前月数据-上个月数据)/上个月数据=当月环比
			double minus = view.getPreNum()==null?view.getDealNum():view.getPreNum();
			double numRes=Calculate.minus(Double.valueOf(view.getDealNum()),minus);
			double numRatio=Calculate.divide(numRes,minus);
			view.setRingRatio(String.valueOf(Calculate.multiply(numRatio,100.00)));
			resultList.add(view);
		}
		Paginator paginator=list.getPaginator();
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("paginator",paginator);
		model.addAttribute("items",resultList);
		return "quotationSecondHand/quotationSecondHandList";
	}
	
	
	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddQuotationSecondHand(QuotationSecondHand quotationSecondHand,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		return "quotationSecondHand/addQuotationSecondHand";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQuotationSecondHand(QuotationSecondHand quotationSecondHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addQuotationSecondHand {}",quotationSecondHand);
			quotationSecondHandServiceImpl.insertQuotationSecondHand(quotationSecondHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addQuotationSecondHand异常 {}",quotationSecondHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_quotationSecondHand")
	public String getQuotationSecondHandById(Integer id,Model model) {
		log.info("getQuotationSecondHandById  {}",id);
		QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
		model.addAttribute("item",result);
		return "quotationSecondHand/updateQuotationSecondHand";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQuotationSecondHandById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteQuotationSecondHandById  {}",id);
			quotationSecondHandServiceImpl.deleteQuotationSecondHandById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteQuotationSecondHandById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateQuotationSecondHand(Integer id,Model model) throws Exception {
		QuotationSecondHand result = quotationSecondHandServiceImpl.selectQuotationSecondHandById(id);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		Area province = settingService.getCityByCityId(result.getCityId());
		model.addAttribute("area", province);
		model.addAttribute("types", paramObjects);
		settingService.getAllProvinceList(model);
		model.addAttribute("item",result);
		return "quotationSecondHand/updateQuotationSecondHand";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQuotationSecondHandById(QuotationSecondHand quotationSecondHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateQuotationSecondHandById  {}",quotationSecondHand);
			quotationSecondHandServiceImpl.updateQuotationSecondHandById(quotationSecondHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateQuotationSecondHandById  {}",quotationSecondHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 弃用
	 **/
	//@RequestMapping("list")
	public String findQuotationSecondHandList(QuotationSecondHand quotationSecondHand,Model model, HttpServletRequest request) {
		try {
			log.info("findQuotationSecondHandList  {}",quotationSecondHand);
			User user = MidlandHelper.getCurrentUser(request);
			//只展示登录用户当前城市的信息
			quotationSecondHand.setCityId(user.getCityId());
			MidlandHelper.doPage(request);
			Page<QuotationSecondHand> result = (Page<QuotationSecondHand>)quotationSecondHandServiceImpl.findQuotationSecondHandList(quotationSecondHand);
			Paginator paginator=result.getPaginator();
			List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
			model.addAttribute("types", paramObjects);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findQuotationSecondHandList  {}",quotationSecondHand,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "quotationSecondHand/quotationSecondHandList";
	}
	
	@RequestMapping("/export")
	public void quotationSecondHandExportExcel(QuotationSecondHandView view1, HttpServletResponse response) throws Exception {
		List<QuotationSecondHandView> dataList = quotationSecondHandViewService.toolTip(view1);
		PoiExcelExport pee = new PoiExcelExport(response,"二手房信息","sheet1");
		//调用
		List<ExportModel> exportModels=new ArrayList<>();
		for (QuotationSecondHandView view:dataList){
			ExportModel exportModel = new ExportModel();
			exportModel.setModelName1(view.getCityName());
			exportModel.setModelName2(view.getAreaName());
			List<ParamObject> quotationType = JsonMapReader.getMap("quotation_type");
			
			exportModel.setModelName3(MidlandHelper.getNameById(view.getType(), quotationType));
			exportModel.setModelName4(String.valueOf(view.getDealNum()));
			exportModel.setModelName5(String.valueOf(view.getDealAcreage()));
			//（当前月数据-上个月数据)/上个月数据=当月环比
			double minus = view.getPreNum()==null?view.getDealNum():view.getPreNum();
			double numRes=Calculate.minus(Double.valueOf(view.getDealNum()),minus);
			double numRatio=Calculate.divide(numRes,minus);
			exportModel.setModelName6(String.valueOf(Calculate.multiply(numRatio,100.00)));
			exportModel.setModelName7(view.getDataTime());
			exportModel.setModelName8(view.getUpdateTime());
			exportModels.add(exportModel);
		}
		String titleColumn[] = {"modelName1","modelName2","modelName3","modelName4","modelName5","modelName6","modelName7","modelName8"};
		String titleName[] = {"城市","区域","类型","成交套数","成交面积","环比","数据时间","更新时间"};
		int titleSize[] = {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13};
		//其他设置 set方法可全不调用
		pee.wirteExcel(titleColumn, titleName, titleSize, exportModels);
	}
	
}
