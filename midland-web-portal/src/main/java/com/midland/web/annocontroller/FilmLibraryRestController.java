package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Meta;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.FilmLibrary;
import com.midland.web.service.FilmLibraryService;
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
@RequestMapping("/filmLibrary/")
public class FilmLibraryRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(FilmLibraryRestController.class);
	@Autowired
	private FilmLibraryService filmLibraryServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addFilmLibrary(@RequestBody FilmLibrary obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addFilmLibrary {}",obj);
			obj.setIsShow(Contant.isShow);
			obj.setIsDelete(Contant.isNotDelete);
			filmLibraryServiceImpl.insertFilmLibrary(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addFilmLibrary异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getFilmLibraryById(@RequestBody Map map) {
		 Result result=new Result();
		Meta meta = new Meta();

		try {
			Integer id =(Integer)map.get("id");
			log.info("getFilmLibraryById  {}",id);
			FilmLibrary filmLibrary = filmLibraryServiceImpl.selectFilmLibraryById(id);
			meta.setTitle(filmLibrary.getHousesName());
			meta.setKeywords(filmLibrary.getHousesName()+","+filmLibrary.getIntroduction());
			meta.setDescription(filmLibrary.getIntroduction());
			result.setMeta(meta);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(filmLibrary);
		} catch(Exception e) {
			log.error("getFilmLibrary异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateFilmLibraryById(@RequestBody FilmLibrary obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateFilmLibraryById  {}",obj);
			filmLibraryServiceImpl.updateFilmLibraryById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateFilmLibraryById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findFilmLibraryList(@RequestBody FilmLibrary  obj, HttpServletRequest request) {
		 Result result=new Result();
		Meta meta = new Meta();
		meta.setTitle("视频片库");
		meta.setKeywords("视频片库");
		meta.setDescription("视频片库");
		result.setMeta(meta);
		try {
			log.info("findFilmLibraryList  {}",obj);
			MidlandHelper.doPage(request);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			Page<FilmLibrary> list = (Page<FilmLibrary>)filmLibraryServiceImpl.findFilmLibraryList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findFilmLibraryList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
