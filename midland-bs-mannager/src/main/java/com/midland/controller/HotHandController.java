package com.midland.controller;

import com.midland.web.model.HotHand;
import com.midland.web.model.temp.ListDescOtherParam;
import com.midland.web.model.user.User;
import com.midland.web.service.HotHandService;
import com.midland.base.BaseFilter;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.ParamObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import java.util.List;
import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
@Controller
@SuppressWarnings("all")
@RequestMapping("/hotHand/")
public class HotHandController extends BaseFilter {

	private Logger log = LoggerFactory.getLogger(HotHandController.class);
	@Autowired
	private HotHandService hotHandServiceImpl;
	@Autowired
	private SettingService settingServiceImpl;
	@Autowired
	private JdbcService jdbcService;

	/**
	 * 
	 **/
	@RequestMapping("index")
	public String hotHandIndex(HotHand hotHand,Model model,HttpServletRequest request) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		User user = MidlandHelper.getCurrentUser(request);
		model.addAttribute("isSuper",user.getIsSuper());
		return "hotHand/hotHandIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddHotHand(HotHand hotHand,Model model) throws Exception {
		settingServiceImpl.getAllProvinceList(model);
		List<ParamObject> bo = JsonMapReader.getMap("decoration");
		model.addAttribute("decorations",bo);
		return "hotHand/addHotHand";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addHotHand(HotHand hotHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("addHotHand {}",hotHand);
			int maxOrderBy = hotHandServiceImpl.getMaxOrderBy();
			hotHand.setOrderBy(maxOrderBy);
			hotHand.setCreateTime(MidlandHelper.getCurrentTime());
			hotHandServiceImpl.insertHotHand(hotHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("addHotHand异常 {}",hotHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 查询
	 **/
	@RequestMapping("get_hotHand")
	public String getHotHandById(Integer id,Model model) {
		log.info("getHotHandById  {}",id);
		HotHand result = hotHandServiceImpl.selectHotHandById(id);
		model.addAttribute("item",result);
		return "hotHand/updateHotHand";	}

	/**
	 * 删除
	 **/
	@RequestMapping("delete")
	@ResponseBody
	public Object deleteHotHandById(Integer id)throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("deleteHotHandById  {}",id);
			hotHandServiceImpl.deleteHotHandById(id);
			map.put("state",0);
		} catch(Exception e) {
			log.error("deleteHotHandById  {}",id,e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateHotHand(Integer id,Model model) throws Exception {
		HotHand result = hotHandServiceImpl.selectHotHandById(id);
		if (result!=null){
			if (StringUtils.isNotEmpty(result.getImgUrl())){
				String[] array = result.getImgUrl().split("\\|\\|");
				List<String> imglist = Arrays.asList(array);
				result.setImgUrlList(imglist);
			}
		}
		settingServiceImpl.getAllProvinceList(model);
		List<ParamObject> bo = JsonMapReader.getMap("decoration");
		model.addAttribute("decorations",bo);
		model.addAttribute("item",result);
		return "hotHand/updateHotHand";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateHotHandById(HotHand hotHand) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			log.info("updateHotHandById  {}",hotHand);
			hotHandServiceImpl.updateHotHandById(hotHand);
			map.put("state",0);
		} catch(Exception e) {
			log.error("updateHotHandById  {}",hotHand,e);
			map.put("state",-1);
		}
		return map;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findHotHandList(HotHand hotHand,Model model, HttpServletRequest request) {
		try {
			log.info("findHotHandList  {}",hotHand);
			MidlandHelper.doPage(request);
			User user = MidlandHelper.getCurrentUser(request);
			if (",".equals(hotHand.getCityId())||StringUtils.isEmpty(hotHand.getCityId())){
				hotHand.setCityId(user.getCityId());
			}
			Page<HotHand> result = (Page<HotHand>)hotHandServiceImpl.findHotHandList(hotHand);
			Paginator paginator=result.getPaginator();
			List<ParamObject> bo = JsonMapReader.getMap("decoration");
			model.addAttribute("decorations",bo);
			model.addAttribute("paginator",paginator);
			model.addAttribute("items",result);
		} catch(Exception e) {
			log.error("findHotHandList  {}",hotHand,e);
			model.addAttribute("paginator",null);
			model.addAttribute("items",null);
		}
		return "hotHand/hotHandList";
	}

	@RequestMapping("sort")
	@ResponseBody
	public Map listDesc(HotHand hotHand, int sort, Model model, HttpServletRequest request) throws Exception {
		String primaryKeyName="id";
		String primaryParam=String.valueOf(hotHand.getId());
		String tableName="hot_hand";
		String orderByColumn="order_by";
		ListDescOtherParam obj = new ListDescOtherParam();
		if (",".equals(hotHand.getCityId())||StringUtils.isEmpty(hotHand.getCityId())){
			User currUser = MidlandHelper.getCurrentUser(request);
			obj.setCityId(currUser.getCityId());

		}else{
			obj.setCityId(hotHand.getCityId());
		}
		String orderByParam=String.valueOf(hotHand.getOrderBy());
		Map map = new HashMap();
		try {
			jdbcService.hotHandListDesc(primaryKeyName,primaryParam,orderByColumn,tableName,orderByParam,obj,sort);
			map.put("state",0);
		} catch (Exception e) {
			log.error("",e);
			map.put("state",-1);
		}
		return map;
	}
	/**
	 * 重新分配经纪人，把经纪人更新到预约记录里
	 *
	 * @param record
	 * @return
	 */
	@RequestMapping("/reset_agent")
	@ResponseBody
	public Object resetAgent(HotHand record) {
		Map map = new HashMap();
		try {
			hotHandServiceImpl.updateHotHandById(record);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("resetAgent : {}", record, e);
			map.put("state", -1);
		}
		return map;
	}

}
