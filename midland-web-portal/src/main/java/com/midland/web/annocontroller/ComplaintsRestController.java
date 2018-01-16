package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Complaints;
import com.midland.web.service.ComplaintsService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
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
@RequestMapping("/complaints/")
public class ComplaintsRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(ComplaintsRestController.class);
	@Autowired
	private ComplaintsService complaintsServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addComplaints(@RequestBody Complaints obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addComplaints {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setAddTime(MidlandHelper.getCurrentTime());
			complaintsServiceImpl.insertComplaints(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addComplaints异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getComplaintsById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getComplaintsById  {}",id);
			Complaints complaints = complaintsServiceImpl.selectComplaintsById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(complaints);
		} catch(Exception e) {
			log.error("getComplaints异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateComplaintsById(@RequestBody Complaints obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateComplaintsById  {}",obj);
			complaintsServiceImpl.updateComplaintsById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateComplaintsById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findComplaintsList(@RequestBody Complaints  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findComplaintsList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<Complaints> list = (Page<Complaints>)complaintsServiceImpl.findComplaintsList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findComplaintsList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
