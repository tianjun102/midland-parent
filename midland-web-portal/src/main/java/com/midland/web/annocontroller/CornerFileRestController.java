package com.midland.web.annocontroller;

import com.midland.web.model.CornerFile;
import com.midland.web.service.CornerFileService;
import com.midland.base.BaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/cornerFile/")
public class CornerFileRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(CornerFileRestController.class);
	@Autowired
	private CornerFileService cornerFileServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addCornerFile(@RequestBody CornerFile obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addCornerFile {}",obj);
			cornerFileServiceImpl.insertCornerFile(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addCornerFile异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getCornerFileById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getCornerFileById  {}",id);
			CornerFile cornerFile = cornerFileServiceImpl.selectCornerFileById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(cornerFile);
		} catch(Exception e) {
			log.error("getCornerFile异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateCornerFileById(@RequestBody CornerFile obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateCornerFileById  {}",obj);
			cornerFileServiceImpl.updateCornerFileById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCornerFileById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findCornerFileList(@RequestBody CornerFile  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findCornerFileList  {}",obj);
			MidlandHelper.doPage(request);
			Page<CornerFile> list = (Page<CornerFile>)cornerFileServiceImpl.findCornerFileList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findCornerFileList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
