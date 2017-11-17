package com.midland.web.annocontroller;

import com.midland.web.model.Answer;
import com.midland.web.service.AnswerService;
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
@RequestMapping("/answer/")
public class AnswerRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(AnswerRestController.class);
	@Autowired
	private AnswerService answerServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addAnswer(@RequestBody Answer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addAnswer {}",obj);
			obj.setAnswerTime(MidlandHelper.getCurrentTime());
			answerServiceImpl.insertAnswer(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addAnswer异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getAnswerById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getAnswerById  {}",id);
			Answer answer = answerServiceImpl.selectAnswerById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(answer);
		} catch(Exception e) {
			log.error("getAnswer异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateAnswerById(@RequestBody Answer obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateAnswerById  {}",obj);
			answerServiceImpl.updateAnswerById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateAnswerById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findAnswerList(@RequestBody Answer  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findAnswerList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Answer> list = (Page<Answer>)answerServiceImpl.findAnswerList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findAnswerList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 点赞
	 */
	@RequestMapping("thumb_up")
	public Object thumb_up(@RequestBody Answer  obj, HttpServletRequest request) {
		Result result=new Result();
		try {
			if (obj.getId()==null){
				result.setCode(ResultStatusUtils.STATUS_CODE_400);
				result.setMsg("参数错误");
				return result;
			}
			answerServiceImpl.thumb_up(obj.getId());
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch (Exception e) {
			log.error("thumb_up",e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
	/**
	 * 点赞
	 */
	@RequestMapping("thumb_down")
	public Object thumb_down(@RequestBody Answer  obj, HttpServletRequest request) {
		Result result=new Result();
		try {
			if (obj.getId()==null){
				result.setCode(ResultStatusUtils.STATUS_CODE_400);
				result.setMsg("参数错误");
				return result;
			}
			answerServiceImpl.thumb_down(obj.getId());
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch (Exception e) {
			log.error("thumb_up",e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
