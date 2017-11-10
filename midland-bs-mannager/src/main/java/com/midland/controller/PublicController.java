/**
 * Created by 'ms.x' on 2017/9/20.
 * 公用的controller
 */
package com.midland.controller;

import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.model.remote.Agent;
import com.midland.web.util.MidlandHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/public")
public class PublicController extends BaseFilter{
	
	@Autowired
	private MidlandConfig midlandConfig;
	
	/**
	 * 用户列表查询（重新分配经纪人）
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/toRedistribute", method = {RequestMethod.GET,RequestMethod.POST})
	public String toRedistribute(Model model, HttpServletRequest request){
		String id=request.getParameter("id");
		String url=request.getParameter("url");
		String indexUrl=request.getParameter("indexUrl");
		String pageNo=request.getParameter("pageNo") ;
		String pageSize=request.getParameter("pageSize");
		model.addAttribute("id",id);
		model.addAttribute("url",url);
		String redirectUrl=indexUrl+"?pageNo="+pageNo+"&pageSize="+pageSize;
		model.addAttribute("indexUrl",redirectUrl);
		return "layout/redistributeIndex";
	}
	
	
	/**
	 * 预约看房（重新分配经纪人）
	 * @param agent
	 * @return
	 */
	@RequestMapping(value = "/redistribute_page", method = {RequestMethod.GET,RequestMethod.POST})
	public String getAppointRedistribute(Agent agent, Model model, HttpServletRequest request){
		String pageSize=request.getParameter("pageSize");
		String pageNo=request.getParameter("pageNo");
		if (pageSize == null ){
			pageSize="5";
		}
		if (pageNo == null ){
			pageNo="1";
		}
		Map map1 = agent.agentToMap();
		map1.put("pageSize",pageSize);
		map1.put("pageNo",pageNo);
		String data = HttpUtils.get(midlandConfig.getAgentPage(), map1);
		List result = MidlandHelper.getAgentPojoList(data, Agent.class);
		Paginator paginator = new Paginator(Integer.valueOf(pageNo),Integer.valueOf(pageSize),100);
		model.addAttribute("paginator", paginator);
		model.addAttribute("agents", result);
		
		return "layout/redistributeList";
	}
	
}
