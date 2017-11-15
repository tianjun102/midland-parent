package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Reply;
import com.midland.web.service.ReplyService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@SuppressWarnings("all")
@RequestMapping("/reply/")
/**
 * 回复管理接口
 **/
public class ReplyRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(ReplyRestController.class);
	@Autowired
	private ReplyService replyServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addReply(@RequestBody Reply obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addreply {}",obj);
			replyServiceImpl.insertReply(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addreply异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getReplyById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getreplyById  {}",id);
			Reply reply = replyServiceImpl.selectReplyById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(reply);
		} catch(Exception e) {
			log.error("getreply异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateReplyById(@RequestBody Reply obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateReplyById  {}",obj);
			replyServiceImpl.updateReplyById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateReplyById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findReplyrList(@RequestBody Reply  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findReplyList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Reply> list = (Page<Reply>)replyServiceImpl.findReplyList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findReplyList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("clickNum")
	public Object clickNum(@RequestBody Reply reply) throws Exception {
		Result result=new Result();
		try {
			log.info("updateReplyById  {}",reply);
			reply = replyServiceImpl.selectReplyById(reply.getId());
			Integer clickNum = reply.getLikes();
			reply.setLikes(clickNum==null?1:clickNum+1);
			replyServiceImpl.updateReplyById(reply);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateReplyById  {}",reply,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
