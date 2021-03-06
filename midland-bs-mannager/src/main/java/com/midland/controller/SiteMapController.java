package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Area;
import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.model.user.User;
import com.midland.web.service.CategoryService;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
import com.midland.web.service.SiteMapService;
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
@RequestMapping("/siteMap/")
public class SiteMapController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(SiteMapController.class);
    @Autowired
    private SiteMapService siteMapServiceImpl;
    @Autowired
    private SettingService settingService;
    @Autowired
    private JdbcService jdbcService;
    @Autowired
    private CategoryService categoryServiceImpl;
    /**
     *
     **/
    @RequestMapping("index")
    public String siteMapIndex(SiteMap siteMap, Model model, HttpServletRequest request) throws Exception {

        //查询资讯分类

        //初始化的时候,最多只拿出50个数据,
        PageHelper.startPage(1,50);
        Category category = new Category();
        category.setType(4);//4是网站地图类型
        category.setIsDelete(Contant.isNotDelete);
        Page<Category> cateList =(Page<Category>) categoryServiceImpl.getCateGorys(category);

        PageHelper.startPage(1, 50);
        Page<SiteMap> result1 = (Page<SiteMap>) siteMapServiceImpl.findModes(siteMap);


        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("cateList", cateList.getResult());
        model.addAttribute("modeList", result1.getResult());
        model.addAttribute("isSuper", user.getIsSuper());
        return "siteMap/siteMapIndex";
    }

    @RequestMapping("delete_query_cateId")
    @ResponseBody
    public Map getcountByCateId(HttpServletRequest request) throws Exception {
        int count = 0;
        Map map = new HashMap();
        Integer cateId = null;
        try {
            String cateIdStr = request.getParameter("cateId");
            if (StringUtils.isEmpty(cateIdStr)) {
                map.put("state", -1);
                map.put("message", "cateId 不能为空,且为int型");
                return map;
            }
            cateId = Integer.valueOf(cateIdStr);
            count = siteMapServiceImpl.getCountByCateId(cateId);
            map.put("state", 0);
            map.put("data", count);
        } catch (Exception e) {
            log.error("getcountByCateId {}", cateId, e);
            map.put("state", -1);
        }
        return map;
    }

    //通过模块类型定位分类
    @RequestMapping("getCate")
    @ResponseBody
    public Object getCate(Category category){
        Map map = new HashMap();
        try {
            PageHelper.startPage(1,50);
            category.setIsDelete(Contant.isNotDelete);
            Page<Category> pularList =(Page<Category>) categoryServiceImpl.getCateGorys(category);
            map.put("state",0);
            map.put("data",pularList.getResult());

        } catch (Exception e) {
            log.error("getCate",e);
            map.put("state",-1);
        }
        return map;
    }
 //通过模块类型定位分类
    @RequestMapping("getMode")
    @ResponseBody
    public Object getMode(SiteMap siteMap){
        Map map = new HashMap();
        try {
            PageHelper.startPage(1,50);
            Page<SiteMap> siteMaps =(Page<SiteMap>) siteMapServiceImpl.findModes(siteMap);
            map.put("state",0);
            map.put("data",siteMaps.getResult());

        } catch (Exception e) {
            log.error("getMode",e);
            map.put("state",-1);
        }
        return map;
    }

    @RequestMapping("choose")
    @ResponseBody
    public Object siteMapChoose(SiteMap siteMap, Model model, HttpServletRequest request) throws Exception {
        Result<Object> result = new Result();
        List result1 = null;
        try {
            Category cate1 = new Category();
            //查询资讯分类
            cate1.setType(Integer.valueOf(request.getParameter("type")));
            cate1.setCityId(siteMap.getCityId());
            cate1.setSource(siteMap.getSource());
            if (StringUtils.isNotEmpty(request.getParameter("modeId")) && !"".equals(request.getParameter("modeId"))) {
                cate1.setModeId(Integer.valueOf(request.getParameter("modeId")));
            }
            result1 = getSiteObject("", cate1);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(result1);
        } catch (Exception e) {
            log.error("siteMapChoose", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }

        return result;
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddSiteMap(SiteMap siteMap, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        Category category = new Category();
        category.setType(4);
        String resultCate = getSiteMap("", category);
        if (StringUtils.isNotEmpty(resultCate)) {
            model.addAttribute("categoryData", resultCate);
        }
        model.addAttribute("type", 4);
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "siteMap/addSiteMap";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addSiteMap(SiteMap siteMap, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            String noteType = request.getParameter("noteType");
            log.debug("addSiteMap {}", siteMap);
            siteMapServiceImpl.insertSiteMap(siteMap);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addSiteMap异常 {}", siteMap, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_siteMap")
    public String getSiteMapById(Integer id, Model model) {
        log.debug("getSiteMapById  {}", id);
        SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
        model.addAttribute("item", result);
        return "siteMap/updateSiteMap";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteSiteMapById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteSiteMapById  {}", id);
            siteMapServiceImpl.deleteSiteMapById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteSiteMapById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }
    /**
     * 逻辑删除
     **/
    @RequestMapping("delete1")
    @ResponseBody
    public Object delete1(SiteMap siteMap) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateIsDelete  {}", siteMap);
            siteMapServiceImpl.updateIsDelete(siteMap);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateIsDelete  {}", siteMap, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateSiteMap(Integer id, Model model, HttpServletRequest request) throws Exception {
        SiteMap result = siteMapServiceImpl.selectSiteMapById(id);
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");

        Category category = new Category();
        category.setType(4);
        String resultCate = getSiteMap("", category);
        if (StringUtils.isNotEmpty(resultCate)) {
            model.addAttribute("categoryData", resultCate);
        }
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap != null){
            cityList = cityMap.get("city");
        }

        model.addAttribute("cityList", cityList);
        model.addAttribute("item", result);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("type", 4);
        model.addAttribute("isSuper", user.getIsSuper());
        return "siteMap/updateSiteMap";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateSiteMapById(SiteMap siteMap) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateSiteMapById  {}", siteMap);
            siteMapServiceImpl.updateSiteMapSelectiveById(siteMap);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateSiteMapById  {}", siteMap, e);
            map.put("state", -1);
        }
        return map;
    }
    @RequestMapping("showAndHide")
    @ResponseBody
    public Object showAndHide(SiteMap siteMap) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("showAndHide  {}", siteMap);
            siteMapServiceImpl.showAndHide(siteMap);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("showAndHide  {}", siteMap, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findSiteMapList(SiteMap siteMap, Model model, HttpServletRequest request) {
        try {
            if (siteMap.getCateId() != null && siteMap.getCateId() == 0) {
                siteMap.setCateId(null);
            }
            log.debug("findSiteMapList  {}", siteMap);
            MidlandHelper.doPage(request);
            Page<SiteMap> result = (Page<SiteMap>) siteMapServiceImpl.findSiteMapList(siteMap);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findSiteMapList  {}", siteMap, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "siteMap/siteMapList";
    }

    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(SiteMap siteMap, int sort, Model model, HttpServletRequest request) throws Exception {
        if (siteMap.getCateId() != null && siteMap.getCateId() == 0) {
            siteMap.setCateId(null);
        }
        if (sort == 1) {
            siteMapServiceImpl.shiftUp(siteMap);
        } else {
            siteMapServiceImpl.shiftDown(siteMap);
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
    public Object batchUpdate(String ids, SiteMap siteMap) throws Exception {
        List<SiteMap> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            SiteMap comment1 = new SiteMap();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(siteMap.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            siteMapServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
