package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.SmsSender.SmsClient;
import com.midland.web.api.SmsSender.SmsModel;
import com.midland.web.api.SmsSender.SmsResult;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.AppointLog;
import com.midland.web.model.Appointment;
import com.midland.web.model.ExportModel;
import com.midland.web.model.remote.Agent;
import com.midland.web.model.user.User;
import com.midland.web.service.AppointLogService;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.DingJiangService;
import com.midland.web.service.impl.TradeFairServiceImpl;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import com.midland.web.util.PoiExcelExport;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 'ms.x' on 2017/8/1.
 */
@Controller
@RequestMapping("/appoint")
public class AppointmentController extends BaseFilter {
	private Logger log = LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private AppointmentService appointmentServiceImpl;
	@Autowired
	private AppointLogService appointLogServiceImpl;
	@Autowired
	private ApiHelper apiHelper;
	
	Logger logger = LoggerFactory.getLogger(AppointmentController.class);
	
	@RequestMapping("/index")
	public String showAppointIndex(HttpServletRequest request, Model model) {
		getSelectParam(model);
		
		return "/appointment/appointIndex";
	}
	
	private void getSelectParam(Model model) {
		List<ParamObject> paramObjects = JsonMapReader.getMap("appointment_sellRent");
		model.addAttribute("sellRents", paramObjects);
		List<ParamObject> paramObjects1 = JsonMapReader.getMap("appointment_status");
		model.addAttribute("statusList", paramObjects1);
		List<ParamObject> paramObjects2 = JsonMapReader.getMap("source");
		model.addAttribute("sources", paramObjects2);
		List<ParamObject> paramObjects3 = JsonMapReader.getMap("appointment_houseType");
		model.addAttribute("houses", paramObjects3);
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object deleteByPrimaryKey(Integer id) {
		Map map = new HashMap<>();
		try {
			Appointment appointment = new Appointment();
			appointment.setId(id);
			appointment.setIsDelete(1);
			appointmentServiceImpl.updateAppointmentById(appointment);
			map.put("state", 0);
		} catch (Exception e) {
			logger.error("deleteByPrimaryKey : id={}", id, e);
			map.put("state", -1);
		}
		return map;
	}

	@RequestMapping("to_add")
	public String toAdd(HttpServletRequest request) {

		return "appointment/addAppointment";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Object addAppointment(Appointment record) {
		Map map = new HashMap();
		try {
			appointmentServiceImpl.insertAppointment(record);
			if (StringUtils.isNotEmpty(record.getAgentPhone())) {//发送短信
				List<String> list = new ArrayList<>();
				list.add("您好");
				list.add("您好");
				list.add("您好");
				;
				SmsModel smsModel = new SmsModel(record.getAgentPhone(), "2029157", list);
				apiHelper.smsSender("addAppointment", smsModel);
			}
			map.put("state", 0);
			return map;
		} catch (Exception e) {
			logger.error("addAppointment {}", record, e);
			map.put("state", -1);
			return map;
		}
	}
	
	@RequestMapping("/get")
	public Appointment selectByPrimaryKey(Integer id) {
		
		return appointmentServiceImpl.selectAppointmentById(id);
	}
	
	
	@RequestMapping("/page")
	public String appointmentPage(Model model, Appointment record, String pageNo, String pageSize, HttpServletRequest request) throws Exception {
		if (pageNo == null || pageNo.equals("")) {
			pageNo = ContextEnums.PAGENO;
		}
		if (pageSize == null || pageSize.equals("")) {
			pageSize = ContextEnums.PAGESIZE;
		}
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		if(!Contant.isSuper.equals(user.getIsSuper())){//不是超级管理员，只能看属性城市的相关信息
			record.setCityId(user.getCityId());
		}
		//只展示用户所属城市的信息
		PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
		Page<Appointment> result = (Page<Appointment>) appointmentServiceImpl.findAppointmentList(record);
		Paginator paginator = result.getPaginator();
		getSelectParam(model);

		List<ParamObject> paramObjects = JsonMapReader.getMap("sex");
		model.addAttribute("sexs", paramObjects);
		model.addAttribute("paginator", paginator);
		model.addAttribute("appoint", result);
		return "appointment/appointlist";
	}
	
	/**
	 * 重新分配经纪人，把经纪人更新到预约记录里
	 *
	 * @param record
	 * @return
	 */
	@RequestMapping("/reset_agent")
	@ResponseBody
	public Object resetAgent(Appointment record) {
		Map map = new HashMap();
		try {
			record.setResetFlag(0);//重新分配经纪人后，隐藏“重新分配按钮”
			appointmentServiceImpl.updateAppointmentById(record);
			if (StringUtils.isNotEmpty(record.getAgentPhone())) {//发送短信
				List<String> list = new ArrayList<>();
				list.add("您好");
				;
				list.add("您好");
				list.add("您好");
				SmsModel smsModel = new SmsModel(record.getAgentPhone(), "2029157", list);
				apiHelper.smsSender("resetAgent", smsModel);
			}
			map.put("state", 0);
		} catch (Exception e) {
			logger.error("resetAgent : {}", record, e);
			map.put("state", -1);
		}
		return map;
	}
	
	
	@RequestMapping("/to_update")
	public String toUpdateAppointment(int appointId, Model model) {
		Appointment appointment = appointmentServiceImpl.selectAppointmentById(appointId);
		List<AppointLog> appointLogs = appointLogServiceImpl.selectAppointLogByAppointId(appointId);
		getSelectParam(model);
		List<ParamObject> paramObjects = JsonMapReader.getMap("decoration");
		model.addAttribute("decorations", paramObjects);
		model.addAttribute("appointment", appointment);
		model.addAttribute("appointLogs", appointLogs);
		return "appointment/updateAppointInfo";
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Object updateByPrimaryKeySelective(Appointment record, String remark, HttpServletRequest request) {
		Map map = new HashMap();
		try {
			if (0 != record.getStatus()) {
				//如果委托状态不是已分配，隐藏重新分配按钮
				record.setResetFlag(0);
			}
			appointmentServiceImpl.updateAppointmentById(record);
			User user = (User) request.getSession().getAttribute("userInfo");
			
			AppointLog appointLog = new AppointLog();
			if (StringUtils.isEmpty(remark)) {
				appointLog.setRemark("无");
			} else {
				appointLog.setRemark(remark);
			}
			appointLog.setAppointId(record.getId());
			appointLog.setLogTime(MidlandHelper.getCurrentTime());
			appointLog.setOperatorId(user.getId());
			appointLog.setOperatorName(user.getUserCnName());
			
			appointLog.setState(record.getStatus());
			appointLogServiceImpl.insertAppointLog(appointLog);
			map.put("state", 0);
		} catch (Exception e) {
			logger.error("updateByPrimaryKeySelective : {}", record, e);
			map.put("state", -1);
		}
		
		return map;
	}
	
	
	@RequestMapping("/export")
	public void userInfoExportExcel(Appointment appointment,HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = MidlandHelper.getCurrentUser(request);
		//只展示用户所属城市的信息
		appointment.setCityId(user.getCityId());
		List<Appointment> dataList = appointmentServiceImpl.findAppointmentList(appointment);
		PoiExcelExport pee = new PoiExcelExport(response, "预约看房", "sheet1");
		//调用
		List<ExportModel> exportModels = new ArrayList<>();
		for (Appointment appointment1 : dataList) {
			ExportModel exportModel = new ExportModel();
			exportModel.setModelName1(appointment1.getAppointSn());
			List<ParamObject> sources = JsonMapReader.getMap("source");
			exportModel.setModelName2(MidlandHelper.getNameById(appointment1.getSource(), sources));
			exportModel.setModelName3(appointment1.getNickName());
			exportModel.setModelName4(appointment1.getPhone());
			List<ParamObject> houseTypes = JsonMapReader.getMap("appointment_houseType");
			exportModel.setModelName5(MidlandHelper.getNameById(appointment1.getHouseType(), houseTypes));
			List<ParamObject> sellRents = JsonMapReader.getMap("appointment_sellRent");
			exportModel.setModelName6(MidlandHelper.getNameById(appointment1.getSellRent(), sellRents));
			exportModel.setModelName7(appointment1.getAppointmentTime());
			exportModel.setModelName8(appointment1.getAreaName());
			exportModel.setModelName9(appointment1.getCommunityName());
			exportModel.setModelName10(appointment1.getAddress());
			exportModel.setModelName11(appointment1.getLayout());
			exportModel.setModelName12(appointment1.getMeasure());
			exportModel.setModelName13(appointment1.getPrice());
			exportModel.setModelName14(appointment1.getAgentName());
			List<ParamObject> statusList = JsonMapReader.getMap("appointment_status");
			exportModel.setModelName15(MidlandHelper.getNameById(appointment1.getStatus(), statusList));
			exportModel.setModelName16(appointment1.getHandleTime());
			exportModels.add(exportModel);
		}
		String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5", "modelName6", "modelName7", "modelName8", "modelName9", "modelName10", "modelName11", "modelName12", "modelName13", "modelName14", "modelName15", "modelName16"};
		String titleName[] = {"预约编号", "平台", "称呼", "手机号码", "类型", "分类", "预约时间", "所属区域", "小区名", "门牌地址", "户型", "面积", "售价/租价", "经纪人", "状态", "处理时间"};
		int titleSize[] = {13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13};
		//其他设置 set方法可全不调用
		pee.wirteExcel(titleColumn, titleName, titleSize, exportModels,request);
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,Appointment appointment) throws Exception {
		List<Appointment> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			Appointment comment1 = new Appointment();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(appointment.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateAppointmentById  {}",commentList);
			appointmentServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
	
	
}

