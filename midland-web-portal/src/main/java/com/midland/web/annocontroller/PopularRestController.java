package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Popular;
import com.midland.web.service.PopularService;
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

import java.util.List;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/popular/")
public class PopularRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(PopularRestController.class);
	@Autowired
	private PopularService popularServiceImpl;



	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getPopularById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getPopularById  {}",id);
			Popular popular = popularServiceImpl.selectById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(popular);
		} catch(Exception e) {
			log.error("getPopular异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}


	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findPopularList(@RequestBody Popular  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findPopularList  {}",obj);
			obj.setIsShow(Contant.isShow);
			obj.setIsDelete(Contant.isNotDelete);
			List<Popular> list = popularServiceImpl.findPopularList(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
		} catch(Exception e) {
			log.error("findPopularList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
