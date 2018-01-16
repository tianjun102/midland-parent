package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.LeaveMsg;
import com.midland.web.service.LeaveMsgService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;

import java.util.List;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/leaveMsg/")
public class LeaveMsgRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(LeaveMsgRestController.class);
	@Autowired
	private LeaveMsgService leaveMsgServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addLeaveMsg(@RequestBody LeaveMsg obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addLeaveMsg {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setAddTime(MidlandHelper.getCurrentTime());
			leaveMsgServiceImpl.insertLeaveMsg(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addLeaveMsg异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getLeaveMsgById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			if (id==null){
				log.debug("getLeaveMsgById id不能为空");
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("id不能为空");
			}
			log.info("getLeaveMsgById  {}",id);
			LeaveMsg leaveMsg = leaveMsgServiceImpl.selectLeaveMsgById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(leaveMsg);
		} catch(Exception e) {
			log.error("getLeaveMsg异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateLeaveMsgById(@RequestBody LeaveMsg obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateLeaveMsgById  {}",obj);
			leaveMsgServiceImpl.updateLeaveMsgById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLeaveMsgById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	@RequestMapping("delete")
	public Object deleteLeaveMsgById(@RequestBody LeaveMsg obj) throws Exception {
		Result result=new Result();
		try {
			if (obj.getId()==null){
				log.debug("getLeaveMsgById id不能为空");
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("id不能为空");
			}
			obj.setIsDelete(Contant.isDelete);
			leaveMsgServiceImpl.updateLeaveMsgById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateLeaveMsgById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}


	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLeaveMsgList(@RequestBody LeaveMsg  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findLeaveMsgList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			Page<LeaveMsg> list = (Page<LeaveMsg>)leaveMsgServiceImpl.findLeaveMsgList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findLeaveMsgList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 批量删除
	 **/
	@RequestMapping("batchUpdate")
	public Object batchUpdate(@RequestBody Map map) throws Exception {
		Result result=new Result();
		List<LeaveMsg> commentList = new ArrayList<>();
		Object ids = map.get("id");
		if (ids instanceof Integer){
			LeaveMsg comment1 = new LeaveMsg();
			comment1.setId((Integer) ids);
			comment1.setIsDelete(Contant.isDelete);
			commentList.add(comment1);
		}else{
			String idStr=(String) ids;
			String[] ides=idStr.split(",",-1);
			for (String id:ides ){
				LeaveMsg comment1 = new LeaveMsg();
				comment1.setId(Integer.valueOf(id));
				comment1.setIsDelete(Contant.isDelete);
				commentList.add(comment1);
			}
		}

		try {
			log.debug("updateAppointmentById  {}",commentList);
			leaveMsgServiceImpl.batchUpdate(commentList);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateAppointmentById  {}",commentList,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

}
