package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.TradeFair;
import com.midland.web.model.user.User;
import com.midland.web.service.TradeFairService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/tradeFair/")
public class TradeFairController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(TradeFairController.class);
	@Autowired
	private TradeFairService tradeFairServiceImpl;
	

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String tradeFairIndex(TradeFair tradeFair, Model model,HttpServletRequest request) throws Exception {
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		List<ParamObject> obj = JsonMapReader.getMap("is_delete");
		model.addAttribute("isDeletes",obj);
		return "tradeFair/tradeFairIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddTradeFair(TradeFair tradeFair, Model model) throws Exception {

		return "tradeFair/addTradeFair";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addTradeFair(TradeFair tradeFair, HttpServletRequest request) throws Exception {
		Map map = new HashMap<>();
		try {
			log.debug("addTradeFair {}",tradeFair);
			User user = (User)request.getSession().getAttribute("userInfo");
			tradeFair.setOperatorId(user.getId());
			tradeFair.setOperatorName(user.getUserCnName());
			tradeFairServiceImpl.insertTradeFair(tradeFair);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addTradeFair异常 {}",tradeFair,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_tradeFair")
	public String getTradeFairById(Integer id,Model model) {
		log.debug("getTradeFairById  {}",id);
		TradeFair result = tradeFairServiceImpl.selectTradeFairById(id);
		model.addAttribute("item",result);
		return "tradeFair/updateTradeFair";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteTradeFairById(Integer id)throws Exception {
		Map map = new HashMap<>();
		try {
			log.debug("deleteTradeFairById  {}",id);
			tradeFairServiceImpl.deleteTradeFairById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteTradeFairById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateTradeFair(Integer id,Model model) throws Exception {
		TradeFair result = tradeFairServiceImpl.selectTradeFairById(id);
		if (result!=null){
			if (StringUtils.isNotEmpty(result.getImgUrl())){
				String[] array = result.getImgUrl().split("\\|\\|");
				List<String> imglist = Arrays.asList(array);
				result.setImgUrlList(imglist);
			}
		}
		model.addAttribute("item",result);
		return "tradeFair/updateTradeFair";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateTradeFairById(TradeFair tradeFair) throws Exception {
		Map map = new HashMap<>();
		try {
			log.debug("updateTradeFairById  {}",tradeFair);
			tradeFairServiceImpl.updateTradeFairById(tradeFair);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateTradeFairById  {}",tradeFair,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findTradeFairList(TradeFair tradeFair, Model model, HttpServletRequest request) {
		try {
			log.debug("findTradeFairList  {}",tradeFair);
			MidlandHelper.doPage(request);
			List<TradeFair> tempResultList = new ArrayList<>();
			Page<TradeFair> result = (Page<TradeFair>)tradeFairServiceImpl.findTradeFairList(tradeFair);
			for (TradeFair temp : result){
				if (StringUtils.isNotEmpty(temp.getImgUrl())){
					String[] array = temp.getImgUrl().split("\\|\\|");
					List<String> imgList = Arrays.asList(array);
					temp.setImgUrlList(imgList);
				}else{
					temp.setImgUrlList(Collections.EMPTY_LIST);
				}
				tempResultList.add(temp);
			}


			Paginator paginator=result.getPaginator();
			User user = MidlandHelper.getCurrentUser(request);
			model.addAttribute("isSuper",user.getIsSuper());
			List<ParamObject> obj = JsonMapReader.getMap("is_delete");
			model.addAttribute("isDeletes",obj);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",tempResultList);
		} catch(Exception e) {
			log.error("findTradeFairList  {}",tradeFair,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "tradeFair/tradeFairList";
	}

	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids,TradeFair tradeFair) throws Exception {
		List<TradeFair> commentList = new ArrayList<>();
		String[] ides=ids.split(",",-1);
		for (String id:ides ){
			TradeFair comment1 = new TradeFair();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(tradeFair.getIsDelete());
			commentList.add(comment1);
		}
		Map<String,Object> map = new HashMap<>();
		try {
			log.debug("updateCategoryById  {}",commentList);
			tradeFairServiceImpl.batchUpdate(commentList);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateCategoryById  {}",commentList,e);
			map.put("state",-1);
		}
		return map;
	}
}
