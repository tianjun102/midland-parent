package com.midland.web.annocontroller;

import com.midland.web.model.Meta;
import com.midland.web.service.MetaService;
import com.midland.web.base.BaseFilter;
import org.slf4j.Logger;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.PageUtil;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/meta/")
public class MetaRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(MetaRestController.class);
	@Autowired
	private MetaService metaServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addMeta(@RequestBody Meta obj) throws Exception {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("addMeta {}",obj);
			metaServiceImpl.insertMeta(obj);
			map.put("state",0);
			map.put("msg","success");
		} catch(Exception e) {
			log.error("addMeta异常 {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getMetaById(@RequestBody Map param) {
		 Map<String,Object> map = new HashMap<>();
		try {
			Integer id =(Integer)param.get("id");
			log.info("getMetaById  {}",id);
			Meta meta = metaServiceImpl.selectMetaById(id);
			map.put("state",0);
			map.put("msg","success");
			map.put("data",meta);
		} catch(Exception e) {
			log.error("getMeta异常 {}",map,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}



	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findMetaList(@RequestBody Meta  obj, HttpServletRequest request) {
		 Map<String,Object> map = new HashMap<>();
		try {
			log.info("findMetaList  {}",obj);
			PageUtil.doPage(request);
			Page<Meta> list = (Page<Meta>)metaServiceImpl.findMetaList(obj);
			Paginator paginator=list.getPaginator();
			map.put("state",0);
			map.put("msg","success");
			map.put("data",list);
			map.put("paginator",paginator);
		} catch(Exception e) {
			log.error("findMetaList  {}",obj,e);
			map.put("state",-1);
			map.put("msg","fail");
		}
		return map;
	}
}
