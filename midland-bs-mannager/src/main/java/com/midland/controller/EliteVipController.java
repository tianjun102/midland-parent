package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.model.Category;
import com.midland.web.model.EliteVip;
import com.midland.web.model.user.User;
import com.midland.web.service.CategoryService;
import com.midland.web.service.EliteVipService;
import com.midland.web.service.SettingService;
import com.midland.web.service.impl.PublicService;
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
import java.util.stream.Collectors;

@Controller
@SuppressWarnings("all")
@RequestMapping("/eliteVip/")
public class EliteVipController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(EliteVipController.class);
    @Autowired
    private EliteVipService eliteVipServiceImpl;
    @Autowired
    CategoryService categoryServiceImpl;
    @Autowired
    PublicService publicServiceImpl;
    @Autowired
    SettingService settingServiceImpl;

    /**
     *
     **/
    @RequestMapping("index")
    public String eliteVipIndex(EliteVip eliteVip, Model model, HttpServletRequest request) throws Exception {
        Category category = new Category();
        category.setType(2);
        List<Category> list = categoryServiceImpl.findCategoryList(category);
        model.addAttribute("categoryList", list);

        User user = MidlandHelper.getCurrentUser(request);
        model.addAttribute("isSuper", user.getIsSuper());
        return "eliteVip/eliteVipIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddEliteVip(EliteVip eliteVip, Model model, HttpServletRequest request) throws Exception {
        Category category = new Category();
        category.setType(2);
        category.setCityId(MidlandHelper.getCurrentUser(request).getCityId());//避免category查询出多个城市的类型,所以这里默认为登录用户的城市
        category.setIsDelete(Contant.isNotDelete);
        List<Category> result = categoryServiceImpl.findCategoryList(category);
        settingServiceImpl.getAllProvinceList(model);
        model.addAttribute("cityId", MidlandHelper.getCurrentUser(request).getCityId());
        model.addAttribute("cityName", MidlandHelper.getCurrentUser(request).getCityName());
        model.addAttribute("vipCateGory", result);
        model.addAttribute("type", category.getType());
        return "eliteVip/addEliteVip";
    }

    @RequestMapping("getVipCate")
    @ResponseBody
    public Object getVipCateGory(Category category, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addEliteVip {}", category);
            category.setType(2);
            category.setIsDelete(Contant.isNotDelete);
            category.setIsShow(Contant.isShow);
            List<Category> result = categoryServiceImpl.findCategoryList(category);
            map.put("result", result);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addEliteVip异常 {}", category, e);
            map.put("state", -1);
        }
        return map;

    }


    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addEliteVip(EliteVip eliteVip) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addEliteVip {}", eliteVip);
            eliteVipServiceImpl.insertEliteVip(eliteVip);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addEliteVip异常 {}", eliteVip, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_eliteVip")
    public String getEliteVipById(Integer id, Model model) {
        log.debug("getEliteVipById  {}", id);
        EliteVip result = eliteVipServiceImpl.selectEliteVipById(id);
        model.addAttribute("item", result);
        return "eliteVip/updateEliteVip";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteEliteVipById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteEliteVipById  {}", id);
            eliteVipServiceImpl.deleteEliteVipById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteEliteVipById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateEliteVip(Integer id, Model model, HttpServletRequest request) throws Exception {
        EliteVip eliteVip = eliteVipServiceImpl.selectEliteVipById(id);
        Category category = new Category();
        category.setType(2);
        category.setIsDelete(Contant.isNotDelete);
        category.setId(eliteVip.getCateId());
        category.setIsShow(Contant.isShow);
        List<Category> result = categoryServiceImpl.findCategoryListFromCityIdById(category);
        settingServiceImpl.getAllProvinceList(model);
        String cityId = null;
        String cityName = null;
        if (result.size() > 0) {
            cityId = result.get(0).getCityId();
            cityName = result.get(0).getCityName();
        } else {
            User user = MidlandHelper.getCurrentUser(request);
            cityId = user.getCityId();
            cityName = user.getCityName();
        }
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityName", cityName);
        model.addAttribute("vipCateGory", result);
        model.addAttribute("item", eliteVip);
        return "eliteVip/updateEliteVip";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateEliteVipById(EliteVip eliteVip) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateEliteVipById  {}", eliteVip);
            eliteVipServiceImpl.updateEliteVipById(eliteVip);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateEliteVipById  {}", eliteVip, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findEliteVipList(EliteVip eliteVip, Model model, HttpServletRequest request) {
        try {
            if (eliteVip.getCateId() != null && eliteVip.getCateId() == 0) {
                eliteVip.setCateId(null);
                eliteVip.setCateName(null);
            }
            log.debug("findEliteVipList  {}", eliteVip);
            MidlandHelper.doPage(request);
            Page<EliteVip> result = (Page<EliteVip>) eliteVipServiceImpl.findEliteVipList(eliteVip);
            List<Integer> list = result.stream().map(e -> e.getCateId()).collect(Collectors.toList());
            List<Category> vipCateGory = new ArrayList<>();
            if (list.size() > 0) {
                vipCateGory = categoryServiceImpl.findCategoryListByIdList(list);
            }
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("vipCateGory", vipCateGory);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findEliteVipList  {}", eliteVip, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "eliteVip/eliteVipList";
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, EliteVip eliteVip) throws Exception {
        List<EliteVip> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            EliteVip comment1 = new EliteVip();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(eliteVip.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            eliteVipServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
