package com.midland.web.annocontroller;

import com.midland.web.model.CommunityAlbum;
import com.midland.web.service.CommunityAlbumService;
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
@RequestMapping("/communityAlbum/")
public class CommunityAlbumRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(CommunityAlbumRestController.class);
	@Autowired
	private CommunityAlbumService communityAlbumServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addCommunityAlbum(@RequestBody CommunityAlbum obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addCommunityAlbum {}",obj);
			communityAlbumServiceImpl.insertCommunityAlbum(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addCommunityAlbum异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getCommunityAlbumById(Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getCommunityAlbumById  {}",id);
			CommunityAlbum communityAlbum = communityAlbumServiceImpl.selectCommunityAlbumById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(communityAlbum);
		} catch(Exception e) {
			log.error("getCommunityAlbum异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateCommunityAlbumById(@RequestBody CommunityAlbum obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateCommunityAlbumById  {}",obj);
			communityAlbumServiceImpl.updateCommunityAlbumById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCommunityAlbumById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findCommunityAlbumList(@RequestBody CommunityAlbum  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findCommunityAlbumList  {}",obj);
			MidlandHelper.doPage(request);
			Page<CommunityAlbum> list = (Page<CommunityAlbum>)communityAlbumServiceImpl.findCommunityAlbumList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findCommunityAlbumList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
