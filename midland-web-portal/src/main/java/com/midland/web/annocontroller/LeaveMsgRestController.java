package com.midland.web.annocontroller;

import com.midland.web.model.LeaveMsg;
import com.midland.web.service.LeaveMsgService;
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
@RequestMapping("/leaveMsg/")
public class LeaveMsgRestController extends BaseFilter  {

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
	public Object getLeaveMsgById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
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

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findLeaveMsgList(@RequestBody LeaveMsg  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findLeaveMsgList  {}",obj);
			MidlandHelper.doPage(request);
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
}
