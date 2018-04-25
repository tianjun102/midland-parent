package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.Area;
import com.midland.web.model.ExportModel;
import com.midland.web.model.HotSearch;
import com.midland.web.model.user.User;
import com.midland.web.service.HotSearchService;
import com.midland.web.service.JdbcService;
import com.midland.web.service.SettingService;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import com.midland.web.util.ParamObject;
import com.midland.web.util.PoiExcelExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/hotSearch/")
public class HotSearchController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(HotSearchController.class);
    @Autowired
    private HotSearchService hotSearchServiceImpl;
    @Autowired
    private SettingService settingService;
    @Autowired
    private JdbcService jdbcService;

    /**
     *
     **/
    @RequestMapping("index")
    public String hotSearchIndex(HotSearch hotSearch, Model model, HttpServletRequest request) throws Exception {
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

        return "hotSearch/hotSearchIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddHotSearch(HotSearch hotSearch, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
            cityList = cityMap.get("city");
        }
        model.addAttribute("cityList", cityList);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "hotSearch/addHotSearch";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addHotSearch(HotSearch hotSearch) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("addHotSearch {}", hotSearch);
            hotSearchServiceImpl.insertHotSearch(hotSearch);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addHotSearch异常 {}", hotSearch, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_hotSearch")
    public String getHotSearchById(Integer id, Model model) {
        log.debug("getHotSearchById  {}", id);
        HotSearch result = hotSearchServiceImpl.selectHotSearchById(id);
        model.addAttribute("item", result);
        return "hotSearch/updateHotSearch";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteHotSearchById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("deleteHotSearchById  {}", id);
            hotSearchServiceImpl.deleteHotSearchById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteHotSearchById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateHotSearch(Integer id, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList=new ArrayList<>();
        if (cityMap !=null){
            cityList = cityMap.get("city");
        }
        HotSearch result = hotSearchServiceImpl.selectHotSearchById(id);
        model.addAttribute("cityList", cityList);
        model.addAttribute("item", result);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        return "hotSearch/updateHotSearch";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateHotSearchById(HotSearch hotSearch) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateHotSearchById  {}", hotSearch);
            hotSearchServiceImpl.updateHotSearchById(hotSearch);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateHotSearchById  {}", hotSearch, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findHotSearchList(HotSearch hotSearch, Model model, HttpServletRequest request) {
        try {
            log.debug("findHotSearchList  {}", hotSearch);
            MidlandHelper.doPage(request);
            Page<HotSearch> result = (Page<HotSearch>) hotSearchServiceImpl.findHotSearchList(hotSearch);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("sortOrder", hotSearch.getSortOrder());
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findHotSearchList  {}", hotSearch, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "hotSearch/hotSearchList";
    }

    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(HotSearch hotSearch, int sort, Model model, HttpServletRequest request) throws Exception {
        if (sort == 1) {
            hotSearchServiceImpl.shiftUp(hotSearch);
        } else {
            hotSearchServiceImpl.shiftDown(hotSearch);
        }
        Map map = new HashMap();
        map.put("state", 0);
        return map;
    }


    @RequestMapping("/export")
    public void userInfoExportExcel(HotSearch hotSearch, HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<HotSearch> dataList = hotSearchServiceImpl.findHotSearchList(hotSearch);
        PoiExcelExport pee = new PoiExcelExport(response, "热搜词", "sheet1");
        //调用
        List<ExportModel> exportModels = new ArrayList<>();
        for (HotSearch hotSearch1 : dataList) {
            ExportModel exportModel = new ExportModel();
            exportModel.setModelName1(hotSearch1.getCityName());
            exportModel.setModelName2(hotSearch1.getKeywords());
            exportModel.setModelName3(String.valueOf(hotSearch1.getCount()==null?0:hotSearch1.getCount()));
            exportModel.setModelName4(hotSearch1.getMenuName());
            List<ParamObject> sources = JsonMapReader.getMap("source");
            exportModel.setModelName5(MidlandHelper.getNameById(hotSearch1.getSource(), sources));

            exportModels.add(exportModel);
        }
        String titleColumn[] = {"modelName1", "modelName2", "modelName3", "modelName4", "modelName5"};
        String titleName[] = { "城市", "热搜词","搜索量" ,"模块","平台"};
        int titleSize[] = {13, 13, 13, 13,13};
        //其他设置 set方法可全不调用
        pee.wirteExcel(titleColumn, titleName, titleSize, exportModels, request);
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, HotSearch hotSearch) throws Exception {
        List<HotSearch> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            HotSearch comment1 = new HotSearch();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(hotSearch.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            hotSearchServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }
}
