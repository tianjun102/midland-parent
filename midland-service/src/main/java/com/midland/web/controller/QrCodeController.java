package com.midland.web.controller;

import com.midland.web.model.Area;
import com.midland.web.model.QrCode;
import com.midland.web.service.QrCodeService;
import com.midland.web.controller.base.BaseController;
import com.midland.web.service.SettingService;
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
@RequestMapping("/qrCode/")
public class QrCodeController extends BaseController  {

	private Logger log = LoggerFactory.getLogger(QrCodeController.class);
	@Autowired
	private QrCodeService qrCodeServiceImpl;
	@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String qrCodeIndex(QrCode qrCode,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "qrCode/qrCodeIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddQrCode(QrCode qrCode,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);
		return "qrCode/addQrCode";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addQrCode(QrCode qrCode) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addQrCode {}",qrCode);
			qrCodeServiceImpl.insertQrCode(qrCode);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addQrCode异常 {}",qrCode,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_qrCode")
	public String getQrCodeById(Integer id,Model model) {
		log.info("getQrCodeById  {}",id);
		QrCode result = qrCodeServiceImpl.selectQrCodeById(id);
		model.addAttribute("item",result);
		return "qrCode/updateQrCode";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteQrCodeById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteQrCodeById  {}",id);
			qrCodeServiceImpl.deleteQrCodeById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteQrCodeById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateQrCode(Integer id,Model model) throws Exception {
		Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		QrCode result = qrCodeServiceImpl.selectQrCodeById(id);
		model.addAttribute("cityList",cityList);
		model.addAttribute("item",result);
		return "qrCode/updateQrCode";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateQrCodeById(QrCode qrCode) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateQrCodeById  {}",qrCode);
			qrCodeServiceImpl.updateQrCodeById(qrCode);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateQrCodeById  {}",qrCode,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findQrCodeList(QrCode qrCode,Model model, HttpServletRequest request) {
		try {
			log.info("findQrCodeList  {}",qrCode);
			MidlandHelper.doPage(request);
			Page<QrCode> result = (Page<QrCode>)qrCodeServiceImpl.findQrCodeList(qrCode);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findQrCodeList  {}",qrCode,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "qrCode/qrCodeList";
	}
}
