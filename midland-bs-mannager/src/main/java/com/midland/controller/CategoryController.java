package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.model.Area;
import com.midland.web.model.Category;
import com.midland.web.model.user.User;
import com.midland.web.service.CategoryService;
import com.midland.web.service.SettingService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category/")
public class CategoryController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryServiceImpl;
    @Autowired
    private SettingService settingService;

    /**
     * 分类控制层
     **/
    @RequestMapping("index")
    public String categoryIndex(Category category, Model model, HttpServletRequest request) throws Exception {
        settingService.getAllProvinceList(model);
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper()==null){
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }

        model.addAttribute("type", category.getType());
        model.addAttribute("isSuper", user.getIsSuper());
        return "category/categoryIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddCategory(Category category, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        String result = categoryServiceImpl.getCategoryTree("", category);
        if (StringUtils.isNotEmpty(result)) {
            model.addAttribute("categoryData", result);
        }
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityList", cityList);
        model.addAttribute("type", category.getType());
        return "category/addCategory";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addCategory(Category category) throws Exception {
        Map map = new HashMap<>();
        try {
            log.debug("addCategory {}", category);
            categoryServiceImpl.insertCategory(category);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addCategory异常 {}", category, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_category")
    public String getCategoryById(Integer id, Model model) {
        log.debug("getCategoryById  {}", id);
        Category result = categoryServiceImpl.selectCategoryById(id);
        model.addAttribute("item", result);
        return "category/updateCategory";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteCategoryById(Integer id) throws Exception {
        Map map = new HashMap<>();
        try {
            log.debug("deleteCategoryById  {}", id);
            categoryServiceImpl.deleteCategoryById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteCategoryById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateCategory(Integer id, Model model, HttpServletRequest request) throws Exception {
        Map<String, String> parem = new HashMap<>();
        parem.put("flag", "city");
        parem.put("id", "*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        Category result = categoryServiceImpl.selectCategoryParentNameById(id);
        Category newCategory = new Category();
        newCategory.setType(result.getType());
        String cateResult = categoryServiceImpl.getCategoryTree("", newCategory);
        if (StringUtils.isNotEmpty(cateResult)) {
            model.addAttribute("categoryData", cateResult);
        }
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() == null) {
            model.addAttribute("cityId", user.getCityId());
            model.addAttribute("cityName", user.getCityName());
        }
        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityList", cityList);
        model.addAttribute("type", result.getType());
        model.addAttribute("item", result);
        return "category/updateCategory";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateCategoryById(Category category) throws Exception {
        Map map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", category);
            categoryServiceImpl.updateCategoryById(category);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", category, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping(value = "list", produces = "application/json; charset=UTF-8")
    public String findCategoryList(Category category, Model model, HttpServletRequest request) {
        model.addAttribute("type", category.getType());
        try {
            log.debug("findCategoryList  {}", category);
            MidlandHelper.doPage(request);
            User user = MidlandHelper.getCurrentUser(request);
            if (user.getIsSuper() == null) {
                category.setCityId(MidlandHelper.getCurrentUser(request).getCityId());
            }
            category.setIsDelete(Contant.isNotDelete);
            category.setIsShow(Contant.isShow);
            Page<Category> result = (Page<Category>) categoryServiceImpl.findCategoryList(category);
            List<ParamObject> paramObjects2 = JsonMapReader.getMap("source");
            model.addAttribute("sources", paramObjects2);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findCategoryList  {}", category, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "category/categoryList";
    }

    @RequestMapping("sort")
    @ResponseBody
    public Map listDesc(Category category, int sort, Model model, HttpServletRequest request) throws Exception {
        if (sort == 1) {
            categoryServiceImpl.shiftUp(category);
        } else {
            categoryServiceImpl.shiftDown(category);
        }
        Map map = new HashMap();
        map.put("state", 0);
        return map;
    }

    /**
     * 查询子分类
     **/
    @RequestMapping("findChildList")
    @ResponseBody
    public Object findChildList(Category category, Model model, HttpServletRequest request) {
        List<Category> cateList = null;
        try {
            category.setIsShow(Contant.isShow);
            category.setIsDelete(Contant.isNotDelete);
            cateList = categoryServiceImpl.findCategoryList(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cateList;
    }

    /**
     * 批量更新
     **/
    @RequestMapping("batchUpdate")
    @ResponseBody
    public Object batchUpdate(String ids, Category category) throws Exception {
        List<Category> commentList = new ArrayList<>();
        String[] ides = ids.split(",", -1);
        for (String id : ides) {
            Category comment1 = new Category();
            comment1.setId(Integer.valueOf(id));
            comment1.setIsDelete(category.getIsDelete());
            commentList.add(comment1);
        }
        Map<String, Object> map = new HashMap<>();
        try {
            log.debug("updateCategoryById  {}", commentList);
            categoryServiceImpl.batchUpdate(commentList);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateCategoryById  {}", commentList, e);
            map.put("state", -1);
        }
        return map;
    }

}


