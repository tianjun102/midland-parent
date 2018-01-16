package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Appointment;
import com.midland.web.model.Comment;
import com.midland.web.model.Information;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.CommentService;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.service.InformationService;
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
@RequestMapping("/comment/")
public class CommentRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(CommentRestController.class);
	@Autowired
	private CommentService commentServiceImpl;
	@Autowired
	private AppointmentService appointmentServiceImpl;
@Autowired
	private InformationService informationServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addComment(@RequestBody Comment obj) throws Exception {
		/**
		 * type 0=资讯；1=委托；2=预约
		 **/
		 Result result=new Result();
		try {
			log.info("addComment {}",obj);
			obj.setCommentTime(MidlandHelper.getCurrentTime());
			obj.setIsDelete(Contant.isNotDelete);
			commentServiceImpl.insertComment(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addComment异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getCommentById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			if (id==null){
				result.setCode(ResultStatusUtils.STATUS_CODE_202);
				result.setMsg("id 不能为空");
				return result;
			}
			log.info("getCommentById  {}",id);
			Comment comment = commentServiceImpl.selectCommentById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(comment);
		} catch(Exception e) {
			log.error("getComment异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateCommentById(@RequestBody Comment obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateCommentById  {}",obj);
			commentServiceImpl.updateCommentById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCommentById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findCommentList(@RequestBody Comment  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findCommentList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			List<Integer> ids = new ArrayList<>();
			Page<Comment> list = (Page<Comment>)commentServiceImpl.findCommentList(obj);
			list.forEach(e->{
				ids.add(e.getInformationId());
			});
			List<Information> informationList = informationServiceImpl.getByIdList(ids);
			List<Comment> comments=new ArrayList<>();
			for (Comment comment:list){
				for (Information in : informationList){
					if (comment.getInformationId() == in.getId()){
						comment.setInformationTitle(in.getTitle());
						comment.setInformationImgUrl(in.getImgUrl());
					}
				}
				comments.add(comment);
			}


			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(comments);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findCommentList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	//未点评接口
	@RequestMapping("/unComment")
	public Object getUnCommentList(@RequestBody Appointment appointment,HttpServletRequest request) throws Exception {
		Result result = new Result();
		try {
			MidlandHelper.doPage(request);
			Page resultPage = (Page)appointmentServiceImpl.getUnCommentList(appointment);
			Paginator paginator = resultPage.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(resultPage.getResult());
			result.setPaginator(paginator);
			return result;
		} catch(Exception e) {
			log.error("getUnCommentList",e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
			return result;
		}
	}

	/**
	 * 点赞
	 **/
	@RequestMapping("like")
	public Object clickNum(@RequestBody Comment obj) throws Exception {
		Result result=new Result();
		try {
			log.info("updateCommentById  {}",obj);
			obj = commentServiceImpl.selectCommentById(obj.getId());
			obj.setLike(obj.getLike()==null?1:obj.getLike()+1);
			commentServiceImpl.updateCommentById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(obj);
		} catch(Exception e) {
			log.error("updateCommentById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}


	/**
	 * 更新
	 **/
	@RequestMapping("delete")
	public Object deleteCommentById(@RequestBody Comment obj) throws Exception {
		Result result=new Result();
		try {
			log.info("updateCommentById  {}",obj);
			commentServiceImpl.deleteCommentById(obj.getId());
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCommentById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
