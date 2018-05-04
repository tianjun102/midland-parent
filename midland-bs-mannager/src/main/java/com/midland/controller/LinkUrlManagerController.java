package com.midland.controller;

import com.midland.web.model.Area;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.Menu;
import com.midland.web.model.user.User;
import com.midland.web.service.LinkUrlManagerService;
import com.midland.web.base.BaseFilter;
import com.midland.web.service.SettingService;
import com.midland.web.service.impl.LinkUrlManagerServiceImpl;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;

import java.util.ArrayList;
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
import com.midland.web.util.PageUtil;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
/**
 * 友情链接表
 **/
@Controller
@SuppressWarnings("all")
@RequestMapping("/linkUrlManager/")
public class LinkUrlManagerController extends BaseFilter  {

	private Logger log = LoggerFactory.getLogger(LinkUrlManagerController.class);
	@Autowired
	private LinkUrlManagerService linkUrlManagerServiceImpl;
	@Autowired
    private SettingService settingService;

	@RequestMapping("index")
	public String linkUrlManagerIndex(LinkUrlManager linkUrlManager,Model model,HttpServletRequest request) throws Exception {
		
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "linkUrlManager/linkUrlMannagerIndex";
	}

	/**
	 * 
	 **/
	@RequestMapping("to_add")
	public String toAddLinkUrlManager(LinkUrlManager linkUrlManager,Model model,HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "linkUrlManager/addLinkUrl";
	}

	/**
	 * 新增
	 **/
	@RequestMapping("add")
	@ResponseBody
	public Object addLinkUrlManager(LinkUrlManager linkUrlManager) throws Exception {
        Map<String, Object> parem = new HashMap<>();
        try {
            linkUrlManagerServiceImpl.insertLinkUrlManager(linkUrlManager);
            parem.put("state",0);
        } catch (Exception e) {
            log.error("addLinkUrlManager",e);
            parem.put("state",-1);
    
        }
        
        return parem;
	}
	

	/**
	 * 
	 **/
	@RequestMapping("to_update")
	public String toUpdateLinkUrlManager(Integer id,Model model,HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList", cityList);
        LinkUrlManager linkUrlManager = linkUrlManagerServiceImpl.selectById(id);
        model.addAttribute("linkUrlManager", linkUrlManager);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "linkUrlManager/updateLinkUrl";
	}

	/**
	 * 更新
	 **/
	@RequestMapping("update")
	@ResponseBody
	public Object updateLinkUrlManagerById(LinkUrlManager linkUrlManager) throws Exception {
        Map parem = new HashMap<>();
        try {
            linkUrlManagerServiceImpl.updateById(linkUrlManager);
            parem.put("state",0);
        } catch (Exception e) {
            log.error("updateLinkUrlManagerById ",e);
            parem.put("state",-1);
        }
        
        return parem;
	}

	/**
	 * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
	 **/
	@RequestMapping("list")
	public String findLinkUrlManagerList(LinkUrlManager linkUrlManager,Model model, HttpServletRequest request) {
        
        MidlandHelper.doPage(request);
        Page<LinkUrlManager> linkUrlList = null;
        try {
            linkUrlList = (Page<LinkUrlManager>) linkUrlManagerServiceImpl.findLinkUrlManagerList(linkUrlManager);
            model.addAttribute("linkUrlList", linkUrlList);
            model.addAttribute("paginator", linkUrlList.getPaginator());
        } catch (Exception e) {
            log.error("findLinkUrlManagerList {}",e);
            model.addAttribute("linkUrlList", null);
            model.addAttribute("paginator", null);
        }
        
        
        return "linkUrlManager/linkUrlList";
	}
    
    
    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(LinkUrlManager linkUrlManager, int sort, Model model, HttpServletRequest request) throws Exception {
        if (sort == 1) {
            linkUrlManagerServiceImpl.shiftUp(linkUrlManager);
        } else {
            linkUrlManagerServiceImpl.shiftDown(linkUrlManager);
        }
        Map map = new HashMap();
        map.put("state", 0);
        return map;
    }
	
	/**
	 * 批量更新
	 **/
	@RequestMapping("batchUpdate")
	@ResponseBody
	public Object batchUpdate(String ids, LinkUrlManager linkUrlManager) throws Exception {
		List<LinkUrlManager> commentList = new ArrayList<>();
		String[] ides = ids.split(",", -1);
		for (String id : ides) {
			LinkUrlManager comment1 = new LinkUrlManager();
			comment1.setId(Integer.valueOf(id));
			comment1.setIsDelete(linkUrlManager.getIsDelete());
			commentList.add(comment1);
		}
		Map<String, Object> map = new HashMap<>();
		try {
			log.debug("batchUpdate  {}", commentList);
			linkUrlManagerServiceImpl.batchUpdate(commentList);
			map.put("state", 0);
		} catch (Exception e) {
			log.error("batchUpdate  {}", commentList, e);
			map.put("state", -1);
		}
		return map;
	}
   
    
}
