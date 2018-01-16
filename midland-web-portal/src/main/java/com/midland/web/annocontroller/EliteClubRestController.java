package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.EliteClub;
import com.midland.web.service.EliteClubService;
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
@RequestMapping("/eliteClub/")
public class EliteClubRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(EliteClubRestController.class);
	@Autowired
	private EliteClubService eliteClubServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addEliteClub(@RequestBody EliteClub obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addEliteClub {}",obj);
			obj.setAdTime(MidlandHelper.getCurrentTime());
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			eliteClubServiceImpl.insertEliteClub(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addEliteClub异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getEliteClubById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getEliteClubById  {}",id);
			EliteClub eliteClub = eliteClubServiceImpl.selectEliteClubById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(eliteClub);
		} catch(Exception e) {
			log.error("getEliteClub异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateEliteClubById(@RequestBody EliteClub obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateEliteClubById  {}",obj);
			eliteClubServiceImpl.updateEliteClubById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateEliteClubById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findEliteClubList(@RequestBody EliteClub  obj, HttpServletRequest request) {
		 Result result=new Result();
		 obj.setIsShow(0);
		try {
			log.info("findEliteClubList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<EliteClub> list = (Page<EliteClub>)eliteClubServiceImpl.findEliteClubList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findEliteClubList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
