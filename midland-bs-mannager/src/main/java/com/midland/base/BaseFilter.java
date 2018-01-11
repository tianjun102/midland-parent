package com.midland.base;

import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.service.CategoryService;
import com.midland.web.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
@Controller
public abstract class BaseFilter {
	@Autowired
	private CategoryService categoryServiceImpl;
	@Autowired
	private SiteMapService siteMapServiceImpl;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		InitStringToNull stringEditor = new InitStringToNull();
		//InitSensitive sensitive = new InitSensitive();
		binder.registerCustomEditor(String.class, stringEditor);
		//binder.registerCustomEditor(String.class,sensitive);
	}
	
	
	@ExceptionHandler({Exception.class})
	public void handlerException(Exception e, HttpServletResponse response) throws IOException {
		e.printStackTrace();
		responseInfo(response, "系统繁忙，请重试！...");
	}
	
	private void responseInfo(HttpServletResponse response, String info) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(info);
	}


	// 把查询结果转换成JSON格式      type: 1-查询1-2级 ； 为空时查询所有
	public String getCategoryTree(String type,Category category) {
		// 避免数据库中存在换行符,进行菜单文字的过滤
		// String replaceStr = "(\r\n|\r|\n|\n\r)";
		List list = new ArrayList<>();
		if("1".equals(type)){
			try {
				list = categoryServiceImpl.findCategoryList(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				list = categoryServiceImpl.findCategoryList(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		StringBuffer ret = new StringBuffer("");
		if (list != null   &&  list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Category cat = (Category) list.get(i);
				ret.append("{id:").append(cat.getId()).append(", pId:").append(cat.getParentId())
						.append(", name:'").append(cat.getCateName()).append("',open:true,nocheck:true");
				if("".equals(type)){
					ret.append(", chirdCount:").append(cat.getChirdCount());
				}
				if(!("0".equals(cat.getParentId().toString()))){
					ret.append(",iconSkin:'pIcon03'");
				}

				ret.append("},");
			}
			return ret.substring(0, ret.length() - 1);
		}

		return "";
	}
	public String getSiteMap(String type,Category category) {
		// 避免数据库中存在换行符,进行菜单文字的过滤
		// String replaceStr = "(\r\n|\r|\n|\n\r)";
		List<Category> list = new ArrayList<>();
		List<Category> listTemp = new ArrayList<>();
		List<Integer> arr=new ArrayList<>();
		if("1".equals(type)){
			try {
				list = categoryServiceImpl.findCategoryList(category);
				list.forEach(e->{
					arr.add(e.getId());
				});
				List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
				siteMaps.forEach(e->{
					Category category1 = new Category();
					category1.setType(1);
					category1.setParentId(e.getCateId());
					category1.setParentName(e.getCateName());
					category1.setId(e.getId());
					listTemp.add(category1);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				list = categoryServiceImpl.findCategoryList(category);
				list.forEach(e->{
					arr.add(e.getId());
				});
				List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByList(arr);
				siteMaps.forEach(e->{
					Category category1 = new Category();
					category1.setType(1);
					category1.setParentId(e.getCateId());
					category1.setParentName(e.getCateName());
					category1.setCateName(e.getName());
					category1.setId(e.getId());
					listTemp.add(category1);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list.addAll(listTemp);
		StringBuffer ret = new StringBuffer("");
		if (list != null   &&  list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				Category cat =  list.get(i);
				ret.append("{id:").append(cat.getId()).append(", pId:").append(cat.getParentId())
						.append(", name:'").append(cat.getCateName()).append("'")
						.append(", pName:'").append(cat.getParentName()).append("'");
				if (cat.getType() != 1) {
					ret.append(", type:").append("0");
				}else{
					ret.append(", type:").append("1");
				}

				ret.append(",open:false,nocheck:true");
				if("".equals(type)){
					ret.append(", chirdCount:").append(cat.getChirdCount());
				}
				if(!("0".equals(cat.getParentId().toString()))){
					ret.append(",iconSkin:'pIcon03'");
				}

				ret.append("},");
			}
			return ret.substring(0, ret.length() - 1);
		}

		return "";
	}

}
