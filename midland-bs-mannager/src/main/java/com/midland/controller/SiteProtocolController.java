package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.SiteProtocol;
import com.midland.web.model.user.User;
import com.midland.web.service.SettingService;
import com.midland.web.service.SiteProtocolService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/siteProtocol/")
public class SiteProtocolController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(SiteProtocolController.class);
    @Autowired
    private SiteProtocolService siteProtocolServiceImpl;
    @Autowired
    private SettingService settingServiceImpl;

    /**
     * 备案(角文件)
     **/
    @RequestMapping("cornerFile")
    public String cornerFile(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/cornerFile";
    }

    public boolean isEmpty(String val) {
        if (val == null) {
            return true;
        }

        return StringUtils.isEmpty((String) val) || "undefined".equals(val);
    }

    /**
     * 注册协议
     **/
    @RequestMapping("registrationProtocol")
    public String registrationProtocol(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/registrationProtocol";
    }

    /**
     * 免责申明
     **/
    @RequestMapping("disclaimer")
    public String disclaimer(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/disclaimer";
    }

    /**
     * 联系我们
     **/
    @RequestMapping("contantUs")
    public String contantUs(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/contantUs";
    }
/**
     * 关于美联
     **/
    @RequestMapping("aboutUs")
    public String aboutUs(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/aboutUs";
    }

    /**
     * 私隐政策
     **/
    @RequestMapping("privacy")
    public String privacy(SiteProtocol siteProtocol, Model model, HttpServletRequest request) throws Exception {
        User user = MidlandHelper.getCurrentUser(request);
        if (user.getIsSuper() != null) {
            siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
            siteProtocol.setCityId(isEmpty(siteProtocol.getCityId()) ? user.getCityId():siteProtocol.getCityId());
        } else {
            siteProtocol.setSource(user.getSource());
            siteProtocol.setCityId(user.getCityId());
        }

        List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
        if (result != null && result.size() > 0) {
            model.addAttribute("item", result.get(0));
        }

        model.addAttribute("isSuper", user.getIsSuper());
        model.addAttribute("cityId", user.getCityId());
        model.addAttribute("cityName", user.getCityName());
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/privacy";
    }

    @RequestMapping("siteProtocol")
    @ResponseBody
    public Object siteProtocol(SiteProtocol siteProtocol, Model model, HttpServletRequest request) {
        Map map = new HashMap();
        try {
            User user = MidlandHelper.getCurrentUser(request);
            if (user.getIsSuper() != null) {
                siteProtocol.setSource(siteProtocol.getSource() != null ? siteProtocol.getSource() : user.getSource());
                siteProtocol.setCityId(siteProtocol.getCityId() != null ? siteProtocol.getCityId() : user.getCityId());
            } else {
                siteProtocol.setSource(user.getSource());
                siteProtocol.setCityId(user.getCityId());
            }

            List<SiteProtocol> result = siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
            map.put("state", 0);
            if (result != null && result.size() > 0) {
                map.put("item", result.get(0));
            } else {
                map.put("item", new SiteProtocol());
            }
        } catch (Exception e) {
            map.put("state", -1);
        }
        return map;
    }


    /**
     *
     **/
    @RequestMapping("index")
    public String siteProtocolIndex(SiteProtocol siteProtocol, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/siteProtocolIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddSiteProtocol(SiteProtocol siteProtocol, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        return "siteProtocol/addSiteProtocol";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addSiteProtocol(SiteProtocol siteProtocol) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("addSiteProtocol {}", siteProtocol);
            siteProtocolServiceImpl.insertSiteProtocol(siteProtocol);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addSiteProtocol异常 {}", siteProtocol, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_siteProtocol")
    public String getSiteProtocolById(Integer id, Model model) {
        log.info("getSiteProtocolById  {}", id);
        SiteProtocol result = siteProtocolServiceImpl.selectSiteProtocolById(id);
        model.addAttribute("item", result);
        return "siteProtocol/updateSiteProtocol";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteSiteProtocolById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("deleteSiteProtocolById  {}", id);
            siteProtocolServiceImpl.deleteSiteProtocolById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteSiteProtocolById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateSiteProtocol(Integer id, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        SiteProtocol result = siteProtocolServiceImpl.selectSiteProtocolById(id);
        model.addAttribute("item", result);
        return "siteProtocol/updateSiteProtocol";
    }

    @RequestMapping("update/aboutUs")
    @ResponseBody
    public Object updateAboutUs(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getAboutUs())){
            siteProtocol.setAboutUs("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }
    @RequestMapping("update/contantUs")
    @ResponseBody
    public Object updateContantUs(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getContantUs())){
            siteProtocol.setContantUs("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }
    @RequestMapping("update/cornerFile")
    @ResponseBody
    public Object updateCornerFile(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getCornerFile())){
            siteProtocol.setCornerFile("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }
    @RequestMapping("update/disclaimer")
    @ResponseBody
    public Object updateDisclaimer(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getDisclaimer())){
            siteProtocol.setDisclaimer("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }
    @RequestMapping("update/privacy")
    @ResponseBody
    public Object updatePrivacy(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getPrivacy())){
            siteProtocol.setPrivacy("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }
    @RequestMapping("update/registrationProtocol")
    @ResponseBody
    public Object updateRegistrationProtocol(SiteProtocol siteProtocol){
        if (StringUtils.isEmpty(siteProtocol.getRegistrationProtocol())){
            siteProtocol.setRegistrationProtocol("");
        }
        return updateOrSaveSiteProtocol(siteProtocol);
    }

    public Object updateOrSaveSiteProtocol(SiteProtocol siteProtocol){
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("updateSiteProtocolById  {}", siteProtocol);
            if (siteProtocol.getId()==null){
                siteProtocolServiceImpl.insertSiteProtocol(siteProtocol);
            }else{
                siteProtocolServiceImpl.updateSiteProtocolById(siteProtocol);
            }
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateSiteProtocolById  {}", siteProtocol, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateSiteProtocolById(SiteProtocol siteProtocol) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("updateSiteProtocolById  {}", siteProtocol);
            siteProtocolServiceImpl.updateSiteProtocolById(siteProtocol);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateSiteProtocolById  {}", siteProtocol, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findSiteProtocolList(SiteProtocol siteProtocol, Model model, HttpServletRequest request) {
        try {
            log.info("findSiteProtocolList  {}", siteProtocol);
            MidlandHelper.doPage(request);
            Page<SiteProtocol> result = (Page<SiteProtocol>) siteProtocolServiceImpl.findSiteProtocolList(siteProtocol);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findSiteProtocolList  {}", siteProtocol, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "siteProtocol/siteProtocolList";
    }
}
