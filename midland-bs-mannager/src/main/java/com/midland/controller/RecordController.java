package com.midland.controller;

import com.midland.web.model.Record;
import com.midland.web.model.user.User;
import com.midland.web.service.RecordService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import org.slf4j.Logger;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/record/")
public class RecordController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(RecordController.class);
	@Autowired
	private RecordService recordServiceImpl;
@Autowired
	private SettingService settingService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String recordIndex(Record record,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		return "record/recordIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddRecord(Record record,Model model) throws Exception {
		settingService.getAllProvinceList(model);

		return "record/addRecord";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addRecord(Record record) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addRecord {}",record);
			record.setAddTime(MidlandHelper.getCurrentTime());
			recordServiceImpl.insertRecord(record);
			map.put("state",0);
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException){
				map.put("state",1);
			}else{
				map.put("state",-1);
			}
			log.error("addRecord异常 {}",record,e);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_record")
	public String getRecordById(Integer id,Model model) {
		log.info("getRecordById  {}",id);
		Record result = recordServiceImpl.selectRecordById(id);
		model.addAttribute("item",result);
		return "record/updateRecord";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteRecordById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteRecordById  {}",id);
			recordServiceImpl.deleteRecordById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteRecordById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateRecord(Integer id,Model model) throws Exception {
		settingService.getAllProvinceList(model);
		Record result = recordServiceImpl.selectRecordById(id);
		model.addAttribute("item",result);
		return "record/updateRecord";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateRecordById(Record record) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateRecordById  {}",record);
			record.setUpdateTime(MidlandHelper.getCurrentTime());
			recordServiceImpl.updateRecordById(record);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateRecordById  {}",record,e);
			if (e instanceof DuplicateKeyException){
				map.put("state",1);
			}else{
				map.put("state",-1);
			}
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findRecordList(Record record,Model model, HttpServletRequest request) {
		try {
			log.info("findRecordList  {}",record);
			MidlandHelper.doPage(request);
			Page<Record> result = (Page<Record>)recordServiceImpl.findRecordList(record);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findRecordList  {}",record,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "record/recordList";
	}
}
