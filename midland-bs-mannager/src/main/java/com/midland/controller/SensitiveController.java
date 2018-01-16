package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/sensitive/")
public class SensitiveController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(SensitiveController.class);
	@Autowired
	private PublicService publicServiceImpl;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String sensitiveIndex(Model model) throws Exception {
		return "setting/sensitiveWord";
	}

	@RequestMapping("add")
	@ResponseBody
	public Object sensitiveAdd(HttpServletRequest request,Model model){
		Map map = new HashMap();
		try {

			String V = request.getParameter("V");
			String V1 = request.getParameter("V1");
			if (StringUtils.isNotEmpty(V)) {
                publicServiceImpl.addSet(V);
				map.put("state",0);
            }
            else if (StringUtils.isNotEmpty(V1)) {
                publicServiceImpl.moveSet(V1);
				map.put("state",0);
            }else {
				map.put("state",-1);
            }
		} catch (Exception e) {
			map.put("state",-1);
		}
		return map;
	}

}
