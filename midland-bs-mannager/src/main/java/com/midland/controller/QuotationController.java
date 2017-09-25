package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.Quotation;
import com.midland.web.model.QuotationView;
import com.midland.web.service.QuotationService;
import com.midland.web.service.QuotationViewService;
import com.midland.web.service.SettingService;
import com.midland.web.util.Calculate;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@SuppressWarnings("all")
@RequestMapping("/quotation/")
public class QuotationController extends BaseFilter {
	
	private Logger log = LoggerFactory.getLogger(QuotationController.class);
	@Autowired
	private QuotationService quotationServiceImpl;
	@Autowired
	private QuotationViewService quotationViewServiceImpl;
	@Autowired
	private SettingService settingService;
	
	/**
	 *
	 **/
	@RequestMapping("index")
	public String quotationIndex(Quotation quotation, Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("isNew", quotation.getIsNew());
		List<Area> list = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		
		model.addAttribute("citys",list);
		return "quotation/quotationIndex";
	}
	
	
	/**
	 *
	 **/
	@RequestMapping("to_add")
	public String toAddQuotation(Quotation quotation, Model model) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		return "quotation/addQuotation";
	}
	
	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQuotation(Quotation quotation) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("addQuotation {}", quotation);
			quotationServiceImpl.insertQuotation(quotation);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("addQuotation异常 {}", quotation, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 * 查询
	 **/
	@RequestMapping("get_quotation")
	public String getQuotationById(Integer id, Model model) {
		log.info("getQuotationById  {}", id);
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		model.addAttribute("item", result);
		return "quotation/updateQuotation";
	}
	
	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQuotationById(Integer id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("deleteQuotationById  {}", id);
			quotationServiceImpl.deleteQuotationById(id);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("deleteQuotationById  {}", id, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 *
	 **/
	@RequestMapping("to_update")
	public String toUpdateQuotation(Integer id, Model model) throws Exception {
		Quotation result = quotationServiceImpl.selectQuotationById(id);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		Area province = settingService.getCityByCityId(result.getCityId());
		model.addAttribute("area", province);
		model.addAttribute("types", paramObjects);
		settingService.getAllProvinceList(model);
		model.addAttribute("item", result);
		return "quotation/updateQuotation";
	}
	
	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQuotationById(Quotation quotation) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try {
			log.info("updateQuotationById  {}", quotation);
			quotationServiceImpl.updateQuotationById(quotation);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("updateQuotationById  {}", quotation, e);
			map.put("state", -1);
		}
		return map;
	}
	
	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findQuotationList(Quotation quotation, Model model, HttpServletRequest request) {
		try {
			log.info("findQuotationList  {}", quotation);
			MidlandHelper.doPage(request);
			Page<Quotation> result = (Page<Quotation>) quotationServiceImpl.findQuotationList(quotation);
			Paginator paginator = result.getPaginator();
			List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
			model.addAttribute("types", paramObjects);
			model.addAttribute("paginator", paginator);
			model.addAttribute("items", result);
		} catch (Exception e) {
			log.error("findQuotationList  {}", quotation, e);
			model.addAttribute("paginator", null);
			model.addAttribute("items", null);
		}
		return "quotation/quotationList";
	}
	
	
	
	@RequestMapping("toolsTip_index")
	public String toolsTipIndex(Model model){
		List<Area> list1 = settingService.queryAllCityByRedis();
		settingService.getAllProvinceList(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		List<ParamObject> paramObjects1 = JsonMapReader.getMap("quotation_houseType_acreage_range");
		model.addAttribute("acreageRange", paramObjects1);
		model.addAttribute("citys",list1);
		model.addAttribute("showType",0);//优先展示成交套数
		return "quotation/contentIndex";
	}
	
	
	@RequestMapping("toolsTip")
	public String showTooltip(Integer id, QuotationView obj,String distName,String distId,String url,String showType, Model model) throws Exception {
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
		
		List<Object> numList = new ArrayList<>();
		List<Object> numRatioList = new ArrayList<>();
		List<Object> acreageList = new ArrayList<>();
		List<Object> acreageRatioList = new ArrayList<>();
		List<Object> dealAvgPriceList = new ArrayList<>();
		List<Object> dealAvgPriceRatioList = new ArrayList<>();
		List<Object> turnVolumeList = new ArrayList<>();
		List<Object> turnVolumeRatioList = new ArrayList<>();
		List<Object> soldNumList = new ArrayList<>();
		List<Object> soldNumRatioList = new ArrayList<>();
		List<Object> soldAcreageList = new ArrayList<>();
		List<Object> soldAcreageRatioList = new ArrayList<>();
		double listMax=0;
		double listMin=0;
		double ratioMax=0;
		double ratioMin=0;
		List<QuotationView> result = quotationViewServiceImpl.findQuotationViewList(obj);
		for (QuotationView view : result){
			month.add(view.getDataTime());
			//（当前月数据-上个月数据)/上个月数据=当月环比
			
			if ("0".equals(showType)){
				numList.add(view.getDealNum());
				Double ratio = getRatio(Double.valueOf(view.getDealNum()),view.getPreDealNum());
				numRatioList.add(ratio);
				listMax=getMax(listMax,view.getDealNum());
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMax,ratio);
				model.addAttribute("numList",numList);
				model.addAttribute("numRatioList",numRatioList);
			}else if ("1".equals(showType)){
				acreageList.add(view.getDealAcreage());
				Double ratio =getRatio(Double.valueOf(view.getDealAcreage()),view.getPreDealAcreage());
				acreageRatioList.add(ratio);
				listMax=getMax(listMax,Double.valueOf(view.getDealAcreage()));
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMin,ratio);
				model.addAttribute("acreageList",acreageList);
				model.addAttribute("acreageRatioList",acreageRatioList);
			}else if ("2".equals(showType)){
				dealAvgPriceList.add(view.getPrice());
				Double ratio =getRatio(Double.valueOf(view.getPrice()),view.getPrePrice());
				dealAvgPriceRatioList.add(ratio);
				listMax=getMax(listMax,Double.valueOf(view.getPrice()));
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMax,ratio);
				model.addAttribute("dealAvgPriceList",dealAvgPriceList);
				model.addAttribute("dealAvgPriceRatioList",dealAvgPriceRatioList);
			}else if ("3".equals(showType)&&view.getDealPrice()!=null){
				Double ratio =getRatio(Double.valueOf(view.getDealPrice()),view.getPreDealPrice());
				turnVolumeList.add(view.getDealPrice());
				turnVolumeRatioList.add(ratio);
				listMax=getMax(listMax,Double.valueOf(view.getDealPrice()));
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMax,ratio);
				model.addAttribute("turnVolumeList",turnVolumeList);
				model.addAttribute("turnVolumeRatioList",turnVolumeRatioList);
			}else if ("4".equals(showType)&&view.getSoldNum()!=null) {
				soldNumList.add(view.getSoldNum());
				Double ratio =getRatio(Double.valueOf(view.getSoldNum()), view.getPreSoldNum());
				soldNumRatioList.add(ratio);
				listMax=getMax(listMax,view.getSoldNum());
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMax,ratio);
				model.addAttribute("soldNumList",soldNumList);
				model.addAttribute("soldNumRatioList",soldNumRatioList);
			}else if ("5".equals(showType)&&view.getSoldArea()!=null) {
				Double ratio =getRatio(Double.valueOf(view.getSoldArea()),view.getPreSoldArea());
				soldAcreageList.add(view.getSoldArea());
				soldAcreageRatioList.add(ratio);
				listMax=getMax(listMax,Double.valueOf(view.getSoldArea()));
				ratioMax=getMax(ratioMax,ratio);
				ratioMin=getMin(ratioMax,ratio);
				model.addAttribute("soldAcreageList",soldAcreageList);
				model.addAttribute("soldAcreageRatioList",soldAcreageRatioList);
			}
		}
//
		listMax=getDoubleUp(listMax);
		listMin=0;
		ratioMax=getRatioDoubleUp(ratioMax);
		ratioMin=getRatioDoubleUp(ratioMin);
//
		model.addAttribute("months", JSONArray.toJSONString(month));
		model.addAttribute("listMax", listMax);
		model.addAttribute("listStep", listMax/10);
		model.addAttribute("listMin", listMin);
		
		
		model.addAttribute("ratioMax", ratioMax);
		model.addAttribute("ratioStep", (ratioMax-ratioMin)/10);
		model.addAttribute("ratioMin", ratioMin);
		
		if (url!=null){
			return "quotation/"+url;
		}
		
		return "quotation/dealNumContent";
		
	}
	
	public double getDoubleUp(double value){
		String val = String.valueOf(value);
		if (val.contains("E")){
			val=MidlandHelper.scientificNotation(val);
		}
		int length;
		int len = val.indexOf(".");
		if (len==-1) {
			length = val.length();
		}else {
			length=len;
		}
		int intVal = Integer.valueOf(val.substring(0,1))+1;
		return intVal*Math.pow(10, length-1);
	}
	
	
	public double getRatioDoubleUp(double value){
		String val = String.valueOf(value);
		
		int length;
		int len = val.indexOf(".");
		if (len==-1) {
			length = val.length();
		}else {
			length=len;
		}
		int intVal;
		if (val.contains("-")){
			intVal= Integer.valueOf(val.substring(0,2))-1;
			return intVal*Math.pow(10, length-2);
		}else{
			intVal = Integer.valueOf(val.substring(0,1))+1;
			return intVal*Math.pow(10, length-1);
		}
	}
	
	private Double getRatio(Double dealNum, Double preDealNum) {
		double minus = preDealNum==null?dealNum:preDealNum;
		double numRes= Calculate.minus(Double.valueOf(dealNum),minus);
		double numRatio=Calculate.divide(numRes,minus);
		return Calculate.multiply(numRatio,100.00);
	}
	
	public double getMin(double x ,double y){
		return x>y?y:x;
	}
	public double getMax(double x ,double y){
		return x>y?x:y;
	}

}