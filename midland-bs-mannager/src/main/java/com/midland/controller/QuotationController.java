package com.midland.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.PublicUtils.QuotationUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.model.*;
import com.midland.web.model.user.User;
import com.midland.web.service.QuotationService;
import com.midland.web.service.QuotationViewService;
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
	public String quotationIndex(Quotation quotation, Model model,HttpServletRequest request) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		List<ParamObject> ojb = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes",ojb);
		return "quotation/quotationIndex";
	}


	@RequestMapping("to_import")
	public String toImport(HttpServletRequest request,Model model) throws Exception {
		List<ParamObject> paramObjects = JsonMapReader.getMap("quotation_type");
		model.addAttribute("types", paramObjects);
		model.addAttribute("isNew", "0");
		settingService.getAllProvinceList(model);
		return "quotation/toImport";
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
			log.debug("addQuotation {}", quotation);
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
		log.debug("getQuotationById  {}", id);
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
			log.debug("deleteQuotationById  {}", id);
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
		result.setDataTime(result.getDataTime()+"-01");
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
			log.debug("updateQuotationById  {}", quotation);
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
			log.debug("findQuotationList  {}", quotation);
			User user = MidlandHelper.getCurrentUser(request);
			model.addAttribute("isSuper",user.getIsSuper());
			if(!Contant.isSuper.equals(user.getIsSuper())){//不是超级管理员，只能看属性城市的相关信息
				quotation.setCityId(user.getCityId());
			}
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
	public String showTooltip(Integer id, Quotation obj,String distName,String distId,String url,String showType, Model model) throws Exception {
		if (StringUtils.isEmpty(obj.getAreaId())&&StringUtils.isEmpty(obj.getAreaName())){
			obj.setAreaId("0");
		}else{
			obj.setAreaId(obj.getAreaId());
			obj.setAreaName(obj.getAreaName());
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
		List<Quotation> result = quotationServiceImpl.findQuotationList(obj);
		obj.setStartTime(MidlandHelper.getFormatPreMonth(obj.getStartTime(),-1));
		obj.setEndTime(MidlandHelper.getFormatPreMonth(obj.getEndTime(),-1));
		List<Quotation> listTemp = quotationServiceImpl.findQuotationList(obj);
		for (Quotation view : result){
			month.add(view.getDataTime());
			//（当前月数据-上个月数据)/上个月数据=当月环比
			
			if ("0".equals(showType)){
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double preNum = null;
				if (res != null&& res.getDealNum()!=null) {
					preNum = Double.valueOf(res.getDealNum());
				}
				numList.add(view.getDealNum());
				Double ratio = QuotationUtil.getRatio(Double.valueOf(view.getDealNum()),preNum);
				numRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,view.getDealNum());
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else if ("1".equals(showType)){
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double preDealAcreage = null;
				if (res != null&& res.getDealAcreage()!=null) {
					preDealAcreage = Double.valueOf(res.getDealAcreage());
				}
				acreageList.add(view.getDealAcreage());
				Double ratio =QuotationUtil.getRatio(Double.valueOf(view.getDealAcreage()),preDealAcreage);
				acreageRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,Double.valueOf(view.getDealAcreage()));
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else if ("2".equals(showType)){
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double prePrice = null;
				if (res != null&& res.getPrice()!=null) {
					prePrice = Double.valueOf(res.getPrice());
				}
				dealAvgPriceList.add(view.getPrice());
				Double ratio =QuotationUtil.getRatio(Double.valueOf(view.getPrice()),prePrice);
				dealAvgPriceRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,Double.valueOf(view.getPrice()));
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else if ("3".equals(showType)&&view.getDealPrice()!=null){
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double preDealPrice = null;
				if (res != null&& res.getDealPrice()!=null) {
					preDealPrice = Double.valueOf(res.getDealPrice());
				}
				turnVolumeList.add(view.getDealPrice());
				Double ratio =QuotationUtil.getRatio(Double.valueOf(view.getDealPrice()),preDealPrice);

				turnVolumeRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,Double.valueOf(view.getDealPrice()));
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else if ("4".equals(showType)&&view.getSoldNum()!=null) {
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double preSoldNum = null;
				if (res != null&& res.getSoldNum()!=null) {
					preSoldNum = Double.valueOf(res.getSoldNum());
				}
				soldNumList.add(view.getSoldNum());
				Double ratio =QuotationUtil.getRatio(Double.valueOf(view.getSoldNum()), preSoldNum);
				soldNumRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,view.getSoldNum());
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}else if ("5".equals(showType)&&view.getSoldArea()!=null) {
				Quotation res = null;
				for (Quotation quoTemp:listTemp){
					if (view.getDataTime().equals(MidlandHelper.getFormatyyMMToMonth(quoTemp.getDataTime(),+1))){
						res=quoTemp;
					}
				}
				Double preSoldArea = null;
				if (res != null&& res.getSoldArea()!=null) {
					preSoldArea = Double.valueOf(res.getSoldArea());
				}
				soldAcreageList.add(view.getSoldArea());
				Double ratio =QuotationUtil.getRatio(Double.valueOf(view.getSoldArea()),preSoldArea);

				soldAcreageRatioList.add(ratio);
				listMax=QuotationUtil.getMax(listMax,Double.valueOf(view.getSoldArea()));
				ratioMax=QuotationUtil.getMax(ratioMax,ratio);
				ratioMin=QuotationUtil.getMin(ratioMin,ratio);
				
			}
		}
		model.addAttribute("numList",numList);
		model.addAttribute("numRatioList",numRatioList);
		model.addAttribute("acreageList",acreageList);
		model.addAttribute("acreageRatioList",acreageRatioList);
		model.addAttribute("dealAvgPriceList",dealAvgPriceList);
		model.addAttribute("dealAvgPriceRatioList",dealAvgPriceRatioList);
		model.addAttribute("soldNumList",soldNumList);
		model.addAttribute("soldNumRatioList",soldNumRatioList);
		model.addAttribute("soldAcreageList",soldAcreageList);
		model.addAttribute("soldAcreageRatioList",soldAcreageRatioList);
		model.addAttribute("turnVolumeList",turnVolumeList);
		model.addAttribute("turnVolumeRatioList",turnVolumeRatioList);
		listMax=QuotationUtil.getDoubleUp(listMax);
		listMin=0;
		ratioMax=QuotationUtil.getRatioDoubleUp(ratioMax);
		ratioMin= QuotationUtil.getRatioDoubleUp(ratioMin);
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

	@RequestMapping("/export")
	public void quotationExportExcel(QuotationView view1, HttpServletResponse response, HttpServletRequest request) throws Exception {
		List<QuotationView> dataList = quotationViewServiceImpl.findQuotationViewList(view1);
		PoiExcelExport pee = new PoiExcelExport(response,"新房信息","sheet1");
		//调用
		List<ExportModel> exportModels=new ArrayList<>();
		for (QuotationView view:dataList){
			ExportModel exportModel = new ExportModel();
			exportModel.setModelName1(view.getCityName());
			exportModel.setModelName2(view.getAreaName());
			List<ParamObject> quotationType = JsonMapReader.getMap("quotation_type");

			exportModel.setModelName3(MidlandHelper.getNameById(view.getType(), quotationType));
			exportModel.setModelName4(String.valueOf(view.getDealNum()));
			exportModel.setModelName5(String.valueOf(view.getDealAcreage()));

			exportModel.setModelName6(String.valueOf(view.getPrice()));
			exportModel.setModelName7(String.valueOf(view.getDealPrice()));
			exportModel.setModelName8(String.valueOf(view.getSoldNum()));
			exportModel.setModelName9(String.valueOf(view.getSoldArea()));
			exportModel.setModelName10(view.getDataTime());
			exportModel.setModelName11(view.getUpdateTime());
			exportModels.add(exportModel);
		}
		String titleColumn[] = {"modelName1","modelName2","modelName3","modelName4","modelName5","modelName6","modelName7","modelName8","modelName9","modelName10","modelName11"};
		String titleName[] = {"城市","区域","类型","成交套数","成交面积","成交均价","成交金额","可售套数","可售面积","数据时间","更新时间"};
		int titleSize[] = {13,13,13,13,13,13,13,13,13,13,13};
		//其他设置 set方法可全不调用
		pee.wirteExcel(titleColumn, titleName, titleSize, exportModels,request);
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,Quotation quotation) throws Exception {
		List<Quotation> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			Quotation comment1 = new Quotation();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(quotation.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			quotationServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}

}