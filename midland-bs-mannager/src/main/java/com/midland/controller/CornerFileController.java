package com.midland.controller;

import com.midland.web.model.CornerFile;
import com.midland.web.model.user.User;
import com.midland.web.service.CornerFileService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/cornerFile/")
public class CornerFileController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(CornerFileController.class);
	@Autowired
	private CornerFileService cornerFileServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;
	/**
	 * 
	 **/
	@RequestMapping("index1")
	public String cornerFileIndex(CornerFile cornerFile,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "cornerFile/cornerFileIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddCornerFile(CornerFile cornerFile,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		return "cornerFile/addCornerFile";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addCornerFile(CornerFile cornerFile,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addCornerFile {}",cornerFile);
			if (StringUtils.isEmpty(cornerFile.getCityId())) {
				User user = MidlandHelper.getCurrentUser(request);
				cornerFile.setCityId(user.getCityId());
				cornerFile.setCityName(user.getCityName());
			}
			cornerFile.setCreateTime(MidlandHelper.getCurrentTime());
			cornerFileServiceImpl.insertCornerFile(cornerFile);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addCornerFile异常 {}",cornerFile,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_cornerFile")
	public String getCornerFileById(Integer id,Model model) {
		log.info("getCornerFileById  {}",id);
		CornerFile result = cornerFileServiceImpl.selectCornerFileById(id);
		model.addAttribute("item",result);
		return "cornerFile/updateCornerFile";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteCornerFileById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteCornerFileById  {}",id);
			cornerFileServiceImpl.deleteCornerFileById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteCornerFileById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateCornerFile(Integer id,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		CornerFile result = cornerFileServiceImpl.selectCornerFileById(id);
		model.addAttribute("item",result);
		return "cornerFile/updateCornerFile";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateCornerFileById(CornerFile cornerFile) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateCornerFileById  {}",cornerFile);
			cornerFileServiceImpl.updateCornerFileById(cornerFile);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCornerFileById  {}",cornerFile,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findCornerFileList(CornerFile cornerFile,Model model, HttpServletRequest request) {
		try {
			log.info("findCornerFileList  {}",cornerFile);
			MidlandHelper.doPage(request);
			Page<CornerFile> result = (Page<CornerFile>)cornerFileServiceImpl.findCornerFileList(cornerFile);
			Paginator paginator=result.getPaginator();
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findCornerFileList  {}",cornerFile,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "cornerFile/cornerFileList";
	}
}
