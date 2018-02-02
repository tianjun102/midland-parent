package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.core.util.DateUtils;
import com.midland.web.model.Area;
import com.midland.web.model.Category;
import com.midland.web.model.Information;
import com.midland.web.model.user.User;
import com.midland.web.service.*;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/information/")
public class InformationController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(InformationController.class);
    @Autowired
    private InformationService informationServiceImpl;

    @Autowired
    private SettingService settingService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JdbcService jdbcService;
    @Autowired
    private RedisService redisServiceImpl;

    /**
     *
     **/
    @RequestMapping("index")
    public String informationIndex(Information information, Model model, HttpServletRequest request) throws Exception {
        Category cate1 = new Category();
        //查询资讯分类
        cate1.setType(1);
        String result = getCategoryTree("", cate1);
        if (StringUtils.isNotEmpty(result)) {
            model.addAttribute("categoryData", result);
        }
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("openFlag", redisServiceImpl.getInformationOpenFlag());
        model.addAttribute("type", cate1.getType());
        model.addAttribute("isSuper", user.getIsSuper());
        return "information/informationIndex";
    }

    @RequestMapping("/banner/openOrClose")
    @ResponseBody
    public Object informationBannerOpenAndClose(Integer id) {
        Map map = new HashMap();
        try {
            redisServiceImpl.setInformationOpenFlag(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("openAudit ", e);
            map.put("state", 0);

        }
        return map;
    }

    @RequestMapping("/banner/get")
    @ResponseBody
    public Object getinformationBannerOpenAndClose(Integer id) {
        Map map = new HashMap();
        try {
            redisServiceImpl.getInformationOpenFlag();
            map.put("state", 0);
        } catch (Exception e) {
            log.error("openAudit ", e);
            map.put("state", 0);

        }
        return map;
    }


    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddInformation(Information information, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        Category category = new Category();
        //查询资讯分类
        category.setType(1);
        category.setParentId(0);
        List<Category> cateList = categoryService.findCategoryList(category);
        List<Area> cityList = cityMap.get("city");
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        Category category1 = new Category();
        category1.setType(1);
        String result = getCategoryTree("", category1);
        if (StringUtils.isNotEmpty(result)) {
            model.addAttribute("categoryData", result);
        }
        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityList", cityList);
        model.addAttribute("cateList", cateList);
        return "information/addInformation";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addInformation(Information information) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addInformation {}", information);
            //1=资讯；0=市场调研
            information.setArticeType(1);
            if (information.getCateId() == null && StringUtils.isEmpty(information.getCateName())) {
                information.setCateId(information.getCateParentid());
                information.setCateName(information.getCateParentName());
            }
            informationServiceImpl.insertInformation(information);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addInformation异常 {}", information, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_information")
    public String getInformationById(Integer id, Model model) {
        log.debug("getInformationById  {}", id);
        Information result = informationServiceImpl.selectInformationById(id);
        model.addAttribute("item", result);
        return "information/updateInformation";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteInformationById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteInformationById  {}", id);
            informationServiceImpl.deleteInformationById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteInformationById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateInformation(Integer id, Model model, HttpServletRequest request) throws Exception {
        Information result = informationServiceImpl.selectInformationById(id);
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        Category category = new Category();
        //查询资讯分类
        category.setType(1);
        category.setParentId(0);
        List<Category> cateList = categoryService.findCategoryList(category);
        List<Area> cityList = cityMap.get("city");
        category.setParentId(result.getCateParentid());
        List<Category> cateChildList = categoryService.findCategoryList(category);
        model.addAttribute("item", result);
        model.addAttribute("cityList", cityList);
        model.addAttribute("cateList", cateList);
        model.addAttribute("cateChildList", cateChildList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        Category category1 = new Category();
        category1.setType(1);
        String result1 = getCategoryTree("", category1);
        if (StringUtils.isNotEmpty(result1)) {
            model.addAttribute("categoryData", result1);
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "information/updateInformation";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateInformationById(Information information) throws Exception {
        if (information.getStatus() != null && information.getStatus() == 0) {
            information.setReleaseTime(DateUtils.nowDateToStringYYMMDDHHmmss());
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateInformationById  {}", information);
            informationServiceImpl.updateInformationById(information);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateInformationById  {}", information, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findInformationList(Information information, Model model, HttpServletRequest request) {
        try {
            if (information.getCateId() != null && information.getCateId() == 0) {
                information.setCateId(null);
            }
            log.debug("findInformationList  {}", information);
            MidlandHelper.doPage(request);
            information.setArticeType(1);
            Page<Information> result = (Page<Information>) informationServiceImpl.findInformationList(information);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findInformationList  {}", information, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "information/informationList";
    }


    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(Information information, int sort, Model model, HttpServletRequest request) throws Exception {
        if (sort == 0) {
            informationServiceImpl.shiftTop(information);
        } else if (sort == 1) {
            informationServiceImpl.shiftUp(information);
        } else {
            informationServiceImpl.shiftDown(information);
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
    public Object batchUpdate(String ids, Information information) throws Exception {
        List<Information> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Information comment1 = new Information();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(information.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            informationServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
