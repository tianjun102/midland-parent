package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.task.TaskConfig;
import com.midland.web.Contants.Contant;
import com.midland.web.model.*;
import com.midland.web.model.user.User;
import com.midland.web.service.*;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/setting")
public class SettingController extends BaseFilter {

    private final Logger logger = LoggerFactory.getLogger(SettingController.class);
    @Autowired
    private SettingService settingService;

    @Autowired
    private PopularService popularServiceImpl;

    @Autowired
    private TaskConfig taskConfig;
    @Autowired
    private BannerService bannerServiceImpl;
    @Autowired
    private CategoryService categoryServiceImpl;
    @Autowired
    private RedisService redisServiceImpl;
    // 进入热门关注首页面
    @RequestMapping(value = "popularIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String popularIndex(Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        //初始化的时候,最多只拿出50个数据,
        PageHelper.startPage(1, 50);
        Category category = new Category();
        category.setIsDelete(Contant.isNotDelete);
        category.setType(3);
        category.setCityId(user.getCityId());
        Page<Category> cateList =(Page<Category>) categoryServiceImpl.getCateGorys(category);

        settingService.getAllProvinceList(model);

        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
        }
        model.addAttribute("cateList",  cateList.getResult());
        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("type", request.getParameter("type"));
        return "setting/popular/popularIndex";
    }

    // 进入热门关注列表页
    @RequestMapping(value = "showPopularList", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPopularList(Model model, HttpServletRequest request, Popular popular) {

        MidlandHelper.doPage(request);

        Page<Popular> PopularList = (Page<Popular>) settingService.findPopularList(popular);
        model.addAttribute("popularList", PopularList);
        model.addAttribute("paginator", PopularList.getPaginator());

        return "setting/popular/popularList";
    }
    //通过模块类型定位分类
    @RequestMapping("getCate")
    @ResponseBody
    public Object getCate(Category category){
        Map map = new HashMap();
        try {
            PageHelper.startPage(1,50);
            category.setIsDelete(Contant.isNotDelete);
            Page<Category> cateList =(Page<Category>) categoryServiceImpl.getCateGorys(category);
            map.put("state",0);
            map.put("data",cateList.getResult());

        } catch (Exception e) {
            logger.error("getCate",e);
            map.put("state",-1);
        }
        return map;
    }
    @RequestMapping(value = "toAddPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAddPage(Model model, HttpServletRequest request) {
        settingService.getAllProvinceList(model);
        Category category = new Category();
        int type = 3;
        category.setType(type);
        model.addAttribute("type", type);
        String result = getCategoryTree("", category);
        if (StringUtils.isNotEmpty(result)) {
            model.addAttribute("categoryData", result);
        }
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "setting/popular/addPopular";
    }
    @RequestMapping(value = "queryProvinceByCityId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object queryProvinceByCityId(Model model, HttpServletRequest request,String cityId){
        Map map = new HashMap();
        try {
            Area list = settingService.queryProvinceByCityId(cityId);
            map.put("state",0);
            map.put("data",list);
        } catch (Exception e) {
            logger.error("queryProvinceByCityId",e);
            map.put("state",-1);
        }
        return map;
    }



    @RequestMapping(value = "seleAddress", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object seleAddress(Model model, HttpServletRequest request, String id, String flag, String parentId) {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", flag);
        parem.put("id", id);
        Map<String, List<Area>> result = null;
        if (("city").equals(flag)) {
            result = settingService.queryCityByRedis(parem);
        } else if (("area").equals(flag) || ("sheet").equals(flag)) {
            if (StringUtils.isNotEmpty(parentId)) {
                parem.put("parentId", parentId);
            }
            result = settingService.queryAreaByRedis(parem);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result == null ? null : result.get(flag));
        return map;
    }


    @RequestMapping(value = "addPopular", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object addPopular(Model model, HttpServletRequest request, Popular popular) {
        Map<String, String> parem = null;
        try {
            parem = new HashMap<>();
            Integer num = settingService.insertPopular(popular);
            if (num >= 0) {
                parem.put("flag", "1");
            }
        } catch (Exception e) {
            logger.error("addPopular",e);
            parem.put("flag", "0");
        }

        return parem;
    }

    @RequestMapping("popular/sort")
    @ResponseBody
    public Map listDesc(Popular popular, int sort, Model model, HttpServletRequest request) throws Exception {
        if (sort == 1) {
            popularServiceImpl.shiftUp(popular);
        } else {
            popularServiceImpl.shiftDown(popular);
        }
        Map map = new HashMap();
        map.put("state", 0);
        return map;
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
            count = popularServiceImpl.getCountByCateId(cateId);
            map.put("state", 0);
            map.put("data", count);
        } catch (Exception e) {
            logger.error("getcountByCateId {}", cateId, e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping(value = "toEditPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String toEditPage(Model model, HttpServletRequest request, Popular popular) {
        popular = settingService.findPopular(popular);
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "province");
        Map<String, List<Area>> result = settingService.queryCityByRedis(parem);
        List<Area> provinceList = result.get("province");
        model.addAttribute("provinceList", provinceList);
        Map<String, List<Area>> cityList = null;
        if (StringUtils.isNotEmpty(popular.getProvinceId()) && StringUtils.isNotEmpty(popular.getCityId())) {
            parem.put("flag", "city");
            parem.put("id", popular.getProvinceId());
            cityList = settingService.queryCityByRedis(parem);
        }
        model.addAttribute("cityList", cityList == null ? null : cityList.get("city"));
        Map<String, List<Area>> areaList = null;
        if (StringUtils.isNotEmpty(popular.getCityId())/*&&StringUtils.isNotEmpty(popular.getAreaId())*/) {
            parem.put("flag", "area");
            parem.put("id", popular.getCityId());
            areaList = settingService.queryAreaByRedis(parem);
        }
        model.addAttribute("areaList", areaList == null ? null : areaList.get("area"));
        Map<String, List<Area>> sheetLst = null;
        if (StringUtils.isNotEmpty(popular.getCityId())/*&&StringUtils.isNotEmpty(popular.getAreaId())*/) {
            parem.put("flag", "sheet");
            parem.put("id", popular.getCityId());
            parem.put("parentId", popular.getAreaId());
            sheetLst = settingService.queryAreaByRedis(parem);
        }
        Category category = new Category();
        int type = 3;
        category.setType(type);
        model.addAttribute("type", type);
        String resultCate = getCategoryTree("", category);
        if (StringUtils.isNotEmpty(resultCate)) {
            model.addAttribute("categoryData", resultCate);
        }
        model.addAttribute("sheetList", sheetLst == null ? null : sheetLst.get("sheet"));
        model.addAttribute("item", popular);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("provinceId", popular.getProvinceId());
        model.addAttribute("provinceName", popular.getProvinceName());
        model.addAttribute("isSuper", user.getIsSuper());
        return "setting/popular/editPopular";
    }


    @RequestMapping(value = "saveEditPopular", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object saveEditPopular(Model model, HttpServletRequest request, Popular popular) {
        Map<String, String> parem = new HashMap<>();
        Integer num = settingService.updatePopular(popular);
        if (num >= 0) {
            parem.put("flag", "1");
        }

        return parem;
    }


    @RequestMapping(value = "deletePopular", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object deletePopular(Model model, HttpServletRequest request, Popular popular) {
        Map<String, String> parem = new HashMap<>();
        Integer num = settingService.updatePopular(popular);
        if (num > 0) {
            parem.put("flag", "1");
        }

        return parem;
    }
   


    @RequestMapping(value = "bannerIndex", method = {RequestMethod.GET, RequestMethod.POST})
    public String bannerIndex(Model model, HttpServletRequest request, Banner Banner) {
        /*Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);*/
        settingService.getAllProvinceList(model);
        List<ParamObject> obj = JsonMapReader.getMap("source");
        model.addAttribute("sources", obj);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "setting/banner/bannerIndex";
    }

    @RequestMapping(value = "bannerList", method = {RequestMethod.GET, RequestMethod.POST})
    public String bannerList(Model model, HttpServletRequest request, Banner Banner) {
        MidlandHelper.doPage(request);
        Page<com.midland.web.model.Banner> bannerList = (Page<com.midland.web.model.Banner>) settingService.findBannerList(Banner);
        Paginator paginator = bannerList.getPaginator();
        model.addAttribute("paginator", paginator);
        model.addAttribute("bannerList", bannerList);
        return "setting/banner/bannerList";
    }

    /**
     * 进入新建banner页面
     *
     * @param model
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "enterBanner", method = {RequestMethod.GET, RequestMethod.POST})
    public String enterBanner(Model model, HttpServletRequest request, Banner banner) {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap != null){
             cityList = cityMap.get("city");
        }
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        banner.setStartTime(MidlandHelper.formatDate(MidlandHelper.getCurrentTime()));
        banner.setEndTime(MidlandHelper.formatDate(MidlandHelper.getyyyyMMddHHmmss(MidlandHelper.getCurrentTime(), 3)));
        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("item", banner);
        return "setting/banner/addBanner";
    }


    @RequestMapping(value = "addBanner", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object addBanner(Model model, HttpServletRequest request, Banner banner) {
        Map<String, Object> map = new HashMap<>();
        Integer num = settingService.insertBanner(banner);
        if (num > 0) {
            map.put("result", "ok");
        }
        return map;
    }

    /**
     * 跳转到修改banner页面
     *
     * @param model
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "enterEditBanner", method = {RequestMethod.GET, RequestMethod.POST})
    public String enterEditBanner(Model model, HttpServletRequest request, Banner banner) {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
             cityList = cityMap.get("city");
        }
        model.addAttribute("cityList", cityList);
        banner = settingService.findBanner(banner);
        model.addAttribute("banner", banner);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "setting/banner/editBanner";
    }


    /**
     * 修改banner
     *
     * @param model
     * @param request
     * @param banner
     * @return
     */

    @RequestMapping(value = "editBanner", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object editBanner(Model model, HttpServletRequest request, Banner banner) {
        Map<String, Object> map = new HashMap<>();
        Integer num = settingService.updateBanner(banner);
        if (num > 0) {
            map.put("result", "ok");
        }
        return map;
    }


    @RequestMapping(value = "deleteBanner", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object deleteBanner(Model model, HttpServletRequest request, Banner banner) {
        Map<String, Object> map = new HashMap<>();
        Integer num = settingService.updateBanner(banner);
        if (num > 0) {
            map.put("result", "ok");
        }
        return map;
    }

    @RequestMapping(value = "time/index")
    public String timeSetting(Model model, HttpServletRequest request, Banner banner) {

        model.addAttribute("item", taskConfig);
        return "setting/timeSetting";
    }

    @RequestMapping(value = "setTime")
    @ResponseBody
    public Object timeSet(HttpServletRequest request) {
        Map map = new HashMap();
        try {
            Double appointmentWarn = request.getParameter("appointmentWarn") == null ? null : Double.valueOf(request.getParameter("appointmentWarn"));
            Double appointClose = request.getParameter("appointClose") == null ? null : Double.valueOf(request.getParameter("appointClose"));
            Double taskInterval = request.getParameter("taskInterval") == null ? null : Double.valueOf(request.getParameter("taskInterval"));
            Double entrustWarn = request.getParameter("entrustWarn") == null ? null : Double.valueOf(request.getParameter("entrustWarn"));
            if (appointmentWarn == null) {
                appointmentWarn = 0.5;
            }
            if (appointClose == null) {
                appointClose = 0.5;
            }
            if (taskInterval == null) {
                taskInterval = 0.5;
            }
            if (entrustWarn == null) {
                entrustWarn = 0.5;
            }
            taskConfig.setAppointClose(appointClose);
            taskConfig.setAppointmentWarn(appointmentWarn);
            taskConfig.setTaskInterval(taskInterval);
            taskConfig.setEntrustWarn(entrustWarn);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("", e);
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("bannerSort")
    @ResponseBody
    public Map bannerSort(Banner banner, int sort, Model model, HttpServletRequest request) throws Exception {

        if (sort == 1) {
            bannerServiceImpl.shiftUp(banner);
        } else {
            bannerServiceImpl.shiftDown(banner);
        }
        Map map = new HashMap();
        map.put("state", 0);
        return map;
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdateBanner")
    @ResponseBody
    public Object batchUpdateBanner(String ids, Banner banner) throws Exception {
        List<Banner> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Banner comment1 = new Banner();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(banner.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            logger.debug("updateCategoryById  {}", commentList);
            settingService.batchUpdateBanner(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdatePopular")
    @ResponseBody
    public Object batchUpdatePopular(String ids, Popular popular) throws Exception {
        List<Popular> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Popular comment1 = new Popular();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(popular.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            logger.debug("updateCategoryById  {}", commentList);
            settingService.batchUpdatePopular(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            logger.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }


    

    @RequestMapping("filmIndex")
    public String filmIndex( Model model, HttpServletRequest request){
        String videoUrl = (String)redisServiceImpl.getValue(Contant.MIDLAND_VIDEO_URL_KEY);
        model.addAttribute("videoUrl",videoUrl);
        return "video/index";
    }


    @RequestMapping("videoSave")
    @ResponseBody
    public Object videoSave(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        String videoUrl=null;
        try {
            videoUrl=request.getParameter("videoUrl");
            redisServiceImpl.setValue(Contant.MIDLAND_VIDEO_URL_KEY,videoUrl);
        } catch (Exception e) {
            logger.error("videoSave",videoUrl);
        }
        map.put("state",0);
        map.put("data",videoUrl);
        return map;

    }




}
