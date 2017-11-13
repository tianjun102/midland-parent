package com.midland.web.annocontroller;

import com.midland.web.model.Attention;
import com.midland.web.service.AttentionService;
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
@RequestMapping("/attention/")
public class AttentionRestController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(AttentionRestController.class);
	@Autowired
	private AttentionService attentionServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addAttention(@RequestBody Attention obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addAttention {}",obj);
			attentionServiceImpl.insertAttention(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addAttention异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getAttentionById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getAttentionById  {}",id);
			Attention attention = attentionServiceImpl.selectAttentionById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(attention);
		} catch(Exception e) {
			log.error("getAttention异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateAttentionById(@RequestBody Attention obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateAttentionById  {}",obj);
			attentionServiceImpl.updateAttentionById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateAttentionById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findAttentionList(@RequestBody Attention  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findAttentionList  {}",obj);
			MidlandHelper.doPage(request);
			Page<Attention> list = (Page<Attention>)attentionServiceImpl.findAttentionList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findAttentionList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
