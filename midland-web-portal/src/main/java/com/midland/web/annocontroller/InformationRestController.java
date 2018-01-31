package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Comment;
import com.midland.web.model.Information;
import com.midland.web.service.CommentService;
import com.midland.web.service.InformationService;
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
@RequestMapping("/information/")
public class InformationRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(InformationRestController.class);
	@Autowired
	private InformationService informationServiceImpl;
	@Autowired
	private CommentService commentServiceImpl;

	/**
	 * 新增 资讯
	 **/
	@RequestMapping("add")
	public Object addInformation(@RequestBody Information obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addInformation {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			informationServiceImpl.insertInformation(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addInformation异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getInformationById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getInformationById  {}",id);
			Information information = informationServiceImpl.selectInformationById(id);
			Comment comment = new Comment();
			comment.setInformationId(id);
			Integer commentNum = 0;
			try {
				commentNum = commentServiceImpl.commentTotle(comment);
			}catch (Exception e){
				log.info("取评论数出错！");
			}
			information.setCommentNum(commentNum);
			information.setClickNum(information.getClickNum()==null?1:information.getClickNum()+1);
			informationServiceImpl.updateInformationById(information);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(information);
		} catch(Exception e) {
			log.error("getInformation异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateInformationById(@RequestBody Information obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateInformationById  {}",obj);
			informationServiceImpl.updateInformationById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateInformationById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findInformationList(@RequestBody Information  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {

			log.info("findInformationList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setStatus(Contant.PUBLISH_UP);//上架
			Page<Information> list = (Page<Information>)informationServiceImpl.findInformationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findInformationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("newest")
	public Object findNewestInformationList(@RequestBody Information  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findInformationList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setStatus(Contant.PUBLISH_UP);//上架
			Page<Information> list = (Page<Information>)informationServiceImpl.findNewestInformationList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findInformationList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
