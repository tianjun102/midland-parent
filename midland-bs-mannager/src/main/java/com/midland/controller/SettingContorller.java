package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.enums.ContextEnums;
import com.midland.web.model.Area;
import com.midland.web.model.Banner;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.model.Popular;
import com.midland.web.service.SettingService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/setting")
public class SettingContorller extends BaseFilter {
@Autowired
private SettingService settingService;
    // 进入热门关注首页面
    @RequestMapping(value = "showPopularIndex", method = { RequestMethod.GET, RequestMethod.POST })
    public String showPopularIndex(Model model, HttpServletRequest request) {
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);
        return "setting/showPopularIndex";
    }
    // 进入热门关注列表页
    @RequestMapping(value = "showPopularList", method = { RequestMethod.GET, RequestMethod.POST })
    public String showPopularList(Model model, HttpServletRequest request, Popular popular) {
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        if(pageNo==null||pageNo.equals("")){
            pageNo = ContextEnums.PAGENO;
        }
        Map<String,Object> map =new HashMap<>();
        if(pageSize==null||pageSize.equals("")){
            pageSize = ContextEnums.PAGESIZE;
        }
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        Page<Popular> PopularList =(Page<Popular>) settingService.findPopularList(popular);
        model.addAttribute("popularList",PopularList);
        model.addAttribute("paginator",PopularList.getPaginator());

        return "setting/popularList";
    }

    @RequestMapping(value = "toAddPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String toAddPage(Model model, HttpServletRequest request){
        settingService.getAllProvinceList(model);
        return "setting/addPopular";
    }
    
   
    
    @RequestMapping(value = "seleAddress", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object seleAddress(Model model, HttpServletRequest request,String id,String flag,String parentId){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag",flag);
        parem.put("id",id);
        Map<String, List<Area>> result = null;
        if(("city").equals(flag)){
            result = settingService.queryCityByRedis(parem);
        }else if (("area").equals(flag) || ("sheet").equals(flag)){
            if (StringUtils.isNotEmpty(parentId)) {
                parem.put("parentId", parentId);
            }
            result = settingService.queryAreaByRedis(parem);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("result",result==null?null:result.get(flag));
        return map;
    }



    @RequestMapping(value = "addPopular", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object addPopular(Model model, HttpServletRequest request,Popular popular){
        Map<String,String> parem = new HashMap<>();
        Integer num = settingService.insertPopular(popular);
        if (num>=0){
            parem.put("flag","1");
        }

        return parem;
    }


    @RequestMapping(value = "toEditPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String toEditPage(Model model, HttpServletRequest request,Popular popular){
        popular = settingService.findPopular(popular);
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","province");
        Map<String, List<Area>> result = settingService.queryCityByRedis(parem);
        List<Area> provinceList = result.get("province");
        model.addAttribute("provinceList",provinceList);
        Map<String, List<Area>> cityList = null;
        if(StringUtils.isNotEmpty(popular.getProvinceId())&&StringUtils.isNotEmpty(popular.getCityId())){
            parem.put("flag","city");
            parem.put("id",popular.getProvinceId());
            cityList = settingService.queryCityByRedis(parem);
        }
        model.addAttribute("cityList",cityList==null?null:cityList.get("city"));
        Map<String, List<Area>> areaList = null;
        if(StringUtils.isNotEmpty(popular.getCityId())/*&&StringUtils.isNotEmpty(popular.getAreaId())*/){
            parem.put("flag","area");
            parem.put("id",popular.getCityId());
            areaList = settingService.queryAreaByRedis(parem);
        }
         model.addAttribute("areaList",areaList==null?null:areaList.get("area"));
        Map<String, List<Area>> sheetLst = null;
        if(StringUtils.isNotEmpty(popular.getCityId())/*&&StringUtils.isNotEmpty(popular.getAreaId())*/){
            parem.put("flag","sheet");
            parem.put("id",popular.getCityId());
            parem.put("parentId",popular.getAreaId());
            sheetLst = settingService.queryAreaByRedis(parem);
        }
        model.addAttribute("sheetList",sheetLst==null?null:sheetLst.get("sheet"));
        model.addAttribute("popular",popular);
        return "setting/editPopular";
    }



    @RequestMapping(value = "saveEditPopular", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object saveEditPopular(Model model, HttpServletRequest request,Popular popular){
        Map<String,String> parem = new HashMap<>();
        Integer num = settingService.updatePopular(popular);
        if (num>=0){
            parem.put("flag","1");
        }

        return parem;
    }


    @RequestMapping(value = "deletePopular", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object deletePopular(Model model, HttpServletRequest request,Popular popular){
        Map<String,String> parem = new HashMap<>();
        popular.setIsDelete(1);
        Integer num = settingService.updatePopular(popular);
        if (num>0){
            parem.put("flag","1");
        }

        return parem;
    }



    // 进入友情链接首页面
    @RequestMapping(value = "showlinkUrlIndex", method = { RequestMethod.GET, RequestMethod.POST })
    public String showlinkUrlIndex(Model model, HttpServletRequest request) {
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);
        return "setting/linkUrlMannagerIndex";
    }
    // 进入友情链接列表页
    @RequestMapping(value = "showlinkUrlList", method = { RequestMethod.GET, RequestMethod.POST })
    public String showlinkUrlList(Model model, HttpServletRequest request, LinkUrlManager linkUrlManager) {
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        if(pageNo==null||pageNo.equals("")){
            pageNo = ContextEnums.PAGENO;
        }
        Map<String,Object> map =new HashMap<>();
        if(pageSize==null||pageSize.equals("")){
            pageSize = ContextEnums.PAGESIZE;
        }
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        Page<LinkUrlManager> linkUrlList =(Page<LinkUrlManager>) settingService.findLinkUrlList(linkUrlManager);
        model.addAttribute("linkUrlList",linkUrlList);
        model.addAttribute("paginator",linkUrlList.getPaginator());

        return "setting/linkUrlList";
    }





    @RequestMapping(value = "toAddLinkUrlPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String toAddLinkUrlPage(Model model, HttpServletRequest request){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);
        return "setting/addLinkUrl";
    }

    @RequestMapping(value = "addLinkUrl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object addLinkUrl(Model model, HttpServletRequest request,LinkUrlManager linkUrlManager){
        Map<String,Object> parem = new HashMap<>();
        int num = settingService.insertLinkUrlManage(linkUrlManager);
        if (num>0){
            parem.put("flag","1");
        }
        return parem;
    }


    @RequestMapping(value = "toEditLinkUrlPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String toEditLinkUrlPage(Model model, HttpServletRequest request,LinkUrlManager linkUrlManager){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);
        linkUrlManager = settingService.findLinkUrlManager(linkUrlManager);
        model.addAttribute("linkUrlManager",linkUrlManager);
        return "setting/editLinkUrl";
    }


    @RequestMapping(value = "saveEditLinkUrl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object saveEditLinkUrl(Model model, HttpServletRequest request,LinkUrlManager linkUrlManager){
        Map<String,Object> parem = new HashMap<>();
        int num = settingService.updateLinkUrlManager(linkUrlManager);
        if(num>0){
            parem.put("flag","1");
        }

        return parem;
    }

    @RequestMapping(value = "deleteLinkUrl", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object deleteLinkUrl(Model model, HttpServletRequest request,LinkUrlManager linkUrlManager){
        Map<String,String> parem = new HashMap<>();
        linkUrlManager.setIsDelete(1);
        Integer num = settingService.updateLinkUrlManager(linkUrlManager);
        if (num>0){
            parem.put("flag","1");
        }

        return parem;
    }


    @RequestMapping(value = "bannerIndex", method = { RequestMethod.GET, RequestMethod.POST })
    public String bannerIndex(Model model, HttpServletRequest request,Banner Banner){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);

        return "setting/bannerIndex";
    }

    @RequestMapping(value = "bannerList", method = { RequestMethod.GET, RequestMethod.POST })
    public String bannerList(Model model, HttpServletRequest request,Banner Banner){
        MidlandHelper.doPage(request);
        Page<com.midland.web.model.Banner> bannerList =  (Page<com.midland.web.model.Banner>) settingService.findBannerList(Banner);
        Paginator paginator = bannerList.getPaginator();
        model.addAttribute("paginator",paginator);
        model.addAttribute("bannerList",bannerList);
        return "setting/bannerList";
    }

    /**
     * 进入新建banner页面
     * @param model
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "enterBanner", method = { RequestMethod.GET, RequestMethod.POST })
    public String enterBanner(Model model, HttpServletRequest request,Banner banner){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);

        return "setting/addBanner";
    }


    @RequestMapping(value = "addBanner", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object addBanner(Model model, HttpServletRequest request,Banner banner){
        Map<String,Object> map = new HashMap<>();
        Integer num = settingService.insertBanner(banner);
        if (num>0){
            map.put("result","ok");
        }
        return map;
    }

    /**
     * 跳转到修改banner页面
     * @param model
     * @param request
     * @param banner
     * @return
     */
    @RequestMapping(value = "enterEditBanner", method = { RequestMethod.GET, RequestMethod.POST })
    public String enterEditBanner(Model model, HttpServletRequest request,Banner banner){
        Map<String,String> parem = new HashMap<>();
        parem.put("flag","city");
        parem.put("id","*");
        Map<String, List<Area>> cityMap = settingService.queryCityByRedis(parem);
        List<Area> cityList = cityMap.get("city");
        model.addAttribute("cityList",cityList);
        banner = settingService.findBanner(banner);
        model.addAttribute("banner",banner);
        return "/setting/editBanner";
    }


    /**
     * 修改banner
     * @param model
     * @param request
     * @param banner
     * @return
     */

    @RequestMapping(value = "editBanner", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object editBanner(Model model, HttpServletRequest request,Banner banner){
        Map<String,Object> map = new HashMap<>();
        Integer num = settingService.updateBanner(banner);
        if (num>0){
            map.put("result","ok");
        }
        return map;
    }



    @RequestMapping(value = "deleteBanner", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public Object deleteBanner(Model model, HttpServletRequest request,Banner banner){
        Map<String,Object> map = new HashMap<>();
        banner.setIsDelete(1);
        Integer num = settingService.updateBanner(banner);
        if (num>0){
            map.put("result","ok");
        }
        return map;
    }

















}
