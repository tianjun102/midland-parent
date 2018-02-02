package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.PageConf;
import com.midland.web.model.user.User;
import com.midland.web.service.PageConfService;
import com.midland.web.service.SettingService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/pageConf/")
public class PageConfController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(PageConfController.class);
    @Autowired
    private PageConfService pageConfServiceImpl;
    @Autowired
    private SettingService settingService;

    /**
     *
     **/
    @RequestMapping("index")
    public String pageConfIndex(PageConf pageConf, Model model, HttpServletRequest request) throws Exception {
        /*Map<String,String> parem = new HashMap<>();
		parem.put("flag","city");
		parem.put("id","*");
		Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
		List<Area> cityList = cityMap.get("city");
		model.addAttribute("cityList",cityList);*/
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "pageConf/pageConfIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddPageConf(PageConf pageConf, Model model, HttpServletRequest request) throws Exception {
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
        return "pageConf/addPageConf";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addPageConf(PageConf pageConf) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addPageConf {}", pageConf);
            pageConfServiceImpl.insertPageConf(pageConf);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addPageConf异常 {}", pageConf, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_pageConf")
    public String getPageConfById(Integer id, Model model) {
        log.debug("getPageConfById  {}", id);
        PageConf result = pageConfServiceImpl.selectPageConfById(id);
        model.addAttribute("item", result);
        return "pageConf/updatePageConf";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deletePageConfById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deletePageConfById  {}", id);
            pageConfServiceImpl.deletePageConfById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deletePageConfById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdatePageConf(Integer id, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        PageConf result = pageConfServiceImpl.selectPageConfById(id);
        model.addAttribute("cityList", cityList);
        model.addAttribute("item", result);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "pageConf/updatePageConf";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updatePageConfById(PageConf pageConf) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updatePageConfById  {}", pageConf);
            pageConfServiceImpl.updatePageConfById(pageConf);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updatePageConfById  {}", pageConf, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findPageConfList(PageConf pageConf, Model model, HttpServletRequest request) {
        try {
            log.debug("findPageConfList  {}", pageConf);
            MidlandHelper.doPage(request);
            Page<PageConf> result = (Page<PageConf>) pageConfServiceImpl.findPageConfList(pageConf);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findPageConfList  {}", pageConf, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "pageConf/pageConfList";
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, PageConf pageConf) throws Exception {
        List<PageConf> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            PageConf comment1 = new PageConf();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(pageConf.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            pageConfServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
