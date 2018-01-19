package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.model.Category;
import com.midland.web.service.CategoryService;
import com.midland.base.ServiceBaseFilter;
import org.slf4j.Logger;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import com.midland.web.util.MidlandHelper;
import javax.servlet.http.HttpServletRequest;
@RestController
@SuppressWarnings("all")
@RequestMapping("/category/")
public class CategoryRestController extends ServiceBaseFilter {

	private Logger log = LoggerFactory.getLogger(CategoryRestController.class);
	@Autowired
	private CategoryService categoryServiceImpl;

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	public Object addCategory(@RequestBody Category obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("addCategory {}",obj);
			obj.setIsShow(Contant.isShow);
			obj.setIsDelete(Contant.isNotDelete);
			categoryServiceImpl.insertCategory(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("addCategory异常 {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get")
	public Object getCategoryById(@RequestBody Map map) {
		 Result result=new Result();
		try {
			Integer id =(Integer)map.get("id");
			log.info("getCategoryById  {}",id);
			Category category = categoryServiceImpl.selectCategoryById(id);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setModel(category);
		} catch(Exception e) {
			log.error("getCategory异常 {}",map,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	public Object updateCategoryById(@RequestBody Category obj) throws Exception {
		 Result result=new Result();
		try {
			log.info("updateCategoryById  {}",obj);
			categoryServiceImpl.updateCategoryById(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
		} catch(Exception e) {
			log.error("updateCategoryById  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public Object findCategoryList(@RequestBody Category  obj, HttpServletRequest request) {
		 Result result=new Result();
		try {
			log.info("findCategoryList  {}",obj);
			//MidlandHelper.doPage(request);
			/*Page<Category> list = (Page<Category>)categoryServiceImpl.findCategoryList(obj);*/
            obj.setIsDelete(Contant.isNotDelete);
            obj.setIsShow(Contant.isShow);
			List<Category> list= categoryServiceImpl.findleveCategory(obj);
			result.setCode(ResultStatusUtils.STATUS_CODE_200);
			result.setMsg("success");
			result.setList(list);
		} catch(Exception e) {
			log.error("findCategoryList  {}",obj,e);
			result.setCode(ResultStatusUtils.STATUS_CODE_203);
			result.setMsg("service error");
		}
		return result;
	}
}
