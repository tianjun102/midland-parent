package com.midland.controller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Meta;
import com.midland.web.model.user.User;
import com.midland.web.service.MetaService;
import com.midland.web.base.BaseFilter;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.List;
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

import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
/**
 * 
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/meta/")
public class MetaController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(MetaController.class);
	@Autowired
	private MetaService metaServiceImpl;
	@Autowired
	private SettingService settingService;
	@RequestMapping("index")
	public String metaIndex(Meta meta,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources", sources);
		List<ParamObject> isDeletes = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes", isDeletes);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper", user.getIsSuper());
		return "meta/metaIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddMeta(Meta meta,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources", sources);
		return "meta/addMeta";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addMeta(Meta meta,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addMeta {}",meta);
			metaServiceImpl.insertMeta(meta);
			map.put("state",0);
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException){
				log.error("addMeta meta信息已存在{}",meta,e);
				map.put("state",1);
			}else{
				log.error("addMeta异常 {}",meta,e);
				map.put("state",-1);
			}

		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_meta")
	public String getMetaById(Integer id,Model model,HttpServletRequest request) {
		log.info("getMetaById  {}",id);
		Meta result = metaServiceImpl.selectMetaById(id);
		model.addAttribute("item",result);
		return "meta/updateMeta";
	}


	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateMeta(Integer id,Model model,HttpServletRequest request) throws Exception {
		settingService.getAllProvinceList(model);
		Meta result = metaServiceImpl.selectMetaById(id);
		List<ParamObject> sources = JsonMapReader.getMap("source");
		model.addAttribute("sources", sources);
		model.addAttribute("item",result);
		return "meta/updateMeta";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateMetaById(Meta meta,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateMetaById  {}",meta);
			Meta meta1 = new Meta();
			meta1.setId(meta.getId());
			meta1.setCityId(meta.getCityId());
			meta1.setIsDelete(Contant.isNotDelete);
			meta1.setSecondModeId(meta.getSecondModeId());
			meta1.setSource(meta.getSource());
			meta1.setModeId(meta.getModeId());
			metaServiceImpl.ifExist_update(meta1);
			metaServiceImpl.updateMetaById(meta);
			map.put("state",0);
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException){
				log.error("updateMetaById meta信息已存在{}",meta,e);
				map.put("state",1);
			}else{
				log.error("updateMetaById  {}",meta,e);
				map.put("state",-1);
			}

		}
		return map;
	}
	/**
	 * 更新
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteMetaById(Meta meta,HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteMetaById  {}",meta);
			if (meta.getIsDelete()==Contant.isNotDelete){
				Meta meta1 = new Meta();
				meta1.setId(meta.getId());
				meta1.setCityId(meta.getCityId());
				meta1.setIsDelete(Contant.isNotDelete);
				meta1.setSecondModeId(meta.getSecondModeId());
				meta1.setSource(meta.getSource());
				meta1.setModeId(meta.getModeId());
				metaServiceImpl.ifExist_update(meta1);
			}
			metaServiceImpl.updateMetaById(meta);
			map.put("state",0);
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException){
				log.error("deleteMetaById meta信息已存在,不能恢复当前meta信息{}",meta,e);
				map.put("state",1);
			}else{
				log.error("deleteMetaById  {}",meta,e);
				map.put("state",-1);
			}

		}
		return map;
	}
	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findMetaList(Meta meta,Model model, HttpServletRequest request) {
		try {
			log.info("findMetaList  {}",meta);
			MidlandHelper.doPage(request);
			Page<Meta> result = (Page<Meta>)metaServiceImpl.findMetaList(meta);
			Paginator paginator=result.getPaginator();
			List<ParamObject> sources = JsonMapReader.getMap("source");
			model.addAttribute("sources", sources);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findMetaList  {}",meta,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "meta/metaList";
	}

	@RequestMapping("getExportSaleCates")
	@ResponseBody
	public Object getCate(){
		Map map = new HashMap();
		try {
			map.put("state",0);
			map.put("data",Contant.ExportSale.enumToList());

		} catch (Exception e) {
			log.error("getCate",e);
			map.put("state",-1);
		}
		return map;
	}
	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(Meta menu, int sort, Model model, HttpServletRequest request) throws Exception {
		if (sort == 1) {
			metaServiceImpl.shiftUp(menu);
		} else {
			metaServiceImpl.shiftDown(menu);
		}
		Map map = new HashMap();
		map.put("state", 0);
		return map;
	}
}
