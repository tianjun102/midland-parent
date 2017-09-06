package com.midland.core.util;


import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;


/**
 * page使用工具
 * @author TJ 
 *
 */
public class PageUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(PageUtil.class);
	
	/**
	 * 将bean转化成map对象
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!StringUtils.equals(name, "class")) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			logger.error("bean转化成map异常", e);
		}
		return params;
	}
	
	/**
	 * 将bean对象和page合装在一个map里
	 * @param obj
	 * @param page
	 * @return
	 */
	/*public static Map<String, Object> pageAndbeanMap(Object obj,Page page){
		Map<String, Object> map=beanToMap(obj);
		map.put("numPerRow", page.getNumPerPage());
		map.put("startRow", page.getPageStart());
		return map;
	}*/
	
	
	/**
	 * 将bean对象和Map合装在一个map里
	 * @param obj
	 * @param page
	 * @return
	 */
	public static Map<String, Object> mapAndbeanMap(Object obj,Map<String,Object> orgMap){
		Map<String, Object> desmap=beanToMap(obj);
		desmap.putAll(orgMap);
		return desmap;
	}
	
	
	

}
