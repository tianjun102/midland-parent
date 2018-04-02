package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.ServiceBaseFilter;
import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.service.CategoryService;
import com.midland.web.service.SiteMapService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@SuppressWarnings("all")
@RequestMapping("/SiteMap/")
public class SiteMapRestController extends ServiceBaseFilter {

    private Logger log = LoggerFactory.getLogger(SiteMapRestController.class);
    @Autowired
    private SiteMapService siteMapServiceImpl;
    @Autowired
    private CategoryService categoryServiceImpl;

    /**
     * 新增
     **/
    @RequestMapping("add")
    public Object addSitemap(@RequestBody SiteMap obj) throws Exception {
        Result result = new Result();
        try {
            log.info("addSitemap {}", obj);
            obj.setIsDelete(Contant.isNotDelete);
            obj.setIsShow(Contant.isShow);
            siteMapServiceImpl.insertSiteMap(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("addSitemap异常 {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 查询
     **/
    @RequestMapping("get")
    public Object getSitemapById(@RequestBody Map map) {
        Result result = new Result();
        try {
            Integer id = (Integer) map.get("id");
            log.info("getSitemapById  {}", id);
            SiteMap SiteMap = siteMapServiceImpl.selectSiteMapById(id);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(SiteMap);
        } catch (Exception e) {
            log.error("getSitemap异常 {}", map, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    public Object updateSitemapById(@RequestBody SiteMap obj) throws Exception {
        Result result = new Result();
        try {
            log.info("updateSitemapById  {}", obj);
            siteMapServiceImpl.updateSiteMapById(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            log.error("updateSitemapById  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    @RequestMapping("list")
    public Object findSitemapList(@RequestBody SiteMap obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            log.info("findSitemapList  {}", obj);
            MidlandHelper.doPage(request);
            Page<SiteMap> list = (Page<SiteMap>) siteMapServiceImpl.findSiteMapList(obj);
            Paginator paginator = list.getPaginator();
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(list);
            result.setPaginator(paginator);
        } catch (Exception e) {
            log.error("findSitemapList  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("tree")
    public Object getSitemap(@RequestBody SiteMap obj, HttpServletRequest request) {
        Result result = new Result();
        try {
            if (StringUtils.isEmpty(obj.getCityId())) {
                result.setCode(ResultStatusUtils.STATUS_CODE_202);
                result.setMsg("cityId不能为空");
                return result;
            }
            Category category = new Category();
            category.setCityId(obj.getCityId());
            category.setType(4);//4为网站地图类型
            category.setSource(0);//网站地图
            List<Integer> categoryIds = new ArrayList<>();
            List<Category> categories = categoryServiceImpl.findCategoryList(category);
            categories.forEach(e -> {
                categoryIds.add(e.getId());
            });
            /**
             * 网站地图第一级,用cateId获取
             */
            List<SiteMap> siteMaps = siteMapServiceImpl.findSiteMapByListAndIsShow(categoryIds,0);
            List<Integer> siteMapIds = new ArrayList<>();
            siteMaps.forEach(e -> {
                siteMapIds.add(e.getId());
            });
            /**
             * 用modeId获取
             */
            List<SiteMap> list = siteMapServiceImpl.findSiteMapByModeId(siteMapIds);
            List<Map> map = siteMapForm(categories, siteMaps, list);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setList(map);
            return result;
        } catch (Exception e) {
            log.error("findSitemapList  {}", obj, e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

    public List<Map> siteMapForm(List<Category> a, List<SiteMap> b, List<SiteMap> c) {
        List<Map> maplist = new ArrayList<>();
        a.forEach(e -> {
            Map map = new HashMap();
            map.put("cateId", e.getId());
            map.put("cateName", e.getCateName());
            map.put("isHref", e.getIsHref());
            map.put("linkUrl", e.getLinkUrl());
            List<SiteMap> list = b.stream().filter(e1 -> e.getId().equals(e1.getCateId())).collect(Collectors.toList());
            List<Map> listMap = new ArrayList<>();
            list.forEach(e2 -> {
                Map map2 = e2.toMap();
                List<Map> listMap1 = new ArrayList<>();
                List<SiteMap> list1 = c.stream().filter(e3 -> e2.getId().equals(e3.getModeId())).collect(Collectors.toList());
                list1.forEach(e4 -> {

                    listMap1.add(e4.toMap());
                });
                map2.put("child", listMap1);
                listMap.add(map2);
            });
            map.put("child", listMap);
            maplist.add(map);
        });
        return maplist;
    }

}
