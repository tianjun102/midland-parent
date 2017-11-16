package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.ResumeManager;
import com.midland.web.service.ResumeManagerService;
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
/**
 * 简历
 */
@SuppressWarnings("all")
@RequestMapping("/resumeManager/")
public class ResumeManagerRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(ResumeManagerRestController.class);
	@Autowired
	private ResumeManagerService resumeManagerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addResumeManager(@RequestBody ResumeManager obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addResumeManager {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			resumeManagerServiceImpl.insertResumeManager(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addResumeManager异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getResumeManagerById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getResumeManagerById  {}",id);
			ResumeManager resumeManager = resumeManagerServiceImpl.selectResumeManagerById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(resumeManager);
		} catch(Exception e) {
			log.error("getResumeManager异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateResumeManagerById(@RequestBody ResumeManager obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateResumeManagerById  {}",obj);
			resumeManagerServiceImpl.updateResumeManagerById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateResumeManagerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findResumeManagerList(@RequestBody ResumeManager  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findResumeManagerList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<ResumeManager> list = (Page<ResumeManager>)resumeManagerServiceImpl.findResumeManagerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findResumeManagerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
