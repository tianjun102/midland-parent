package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.HotSearch;
import com.midland.web.service.HotSearchService;
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
@RequestMapping("/hotSearch/")
public class HotSearchRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(HotSearchRestController.class);
	@Autowired
	private HotSearchService hotSearchServiceImpl;



	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getHotSearchById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getHotSearchById  {}",id);
			HotSearch hotSearch = hotSearchServiceImpl.selectHotSearchById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(hotSearch);
		} catch(Exception e) {
			log.error("getHotSearch异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	@RequestMapping("click_num")
	public Object searchCount(@RequestBody HotSearch  obj, HttpServletRequest request){
		Result result=new Result();
		if (obj.getId()==null){
			result.setMsg("id不能为空");
			result.setCode(ResultStatusUtils.STATUS_CODE_202);
			return result;
		}
		try {
			hotSearchServiceImpl.click_num(obj.getId());
			result.setMsg("success");
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			return result;
		} catch (Exception e) {
			log.error("click_num",e);
			result.setMsg("service error");
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			return result;
		}
	}


	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findHotSearchList(@RequestBody HotSearch  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findHotSearchList  {}",obj);
			obj.setIsDelete(Contant.isNotDelete);
			obj.setIsShow(Contant.isShow);
			MidlandHelper.doPage(request);
			Page<HotSearch> list = (Page<HotSearch>)hotSearchServiceImpl.findHotSearchList(obj);
			Paginator paginator=list.getPaginator();
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
			result.setPaginator(paginator);
		} catch(Exception e) {
			log.error("findHotSearchList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}




}
