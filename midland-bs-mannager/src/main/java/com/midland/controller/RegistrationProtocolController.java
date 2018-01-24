package com.midland.controller;

import com.midland.web.model.RegistrationProtocol;
import com.midland.web.model.user.User;
import com.midland.web.service.RegistrationProtocolService;
import com.midland.base.BaseFilter;
import com.midland.web.service.SettingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Map;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;

import java.util.List;

import com.midland.web.util.MidlandHelper;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Controller
@SuppressWarnings("all")
@RequestMapping("/registrationProtocol/")
public class RegistrationProtocolController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(RegistrationProtocolController.class);
    @Autowired
    private RegistrationProtocolService registrationProtocolServiceImpl;
    @Autowired
    private SettingService settingServiceImpl;

    /**
     *
     **/
    @RequestMapping("index1")
    public String registrationProtocolIndex(RegistrationProtocol registrationProtocol, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        return "registrationProtocol/registrationProtocolIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddRegistrationProtocol(RegistrationProtocol registrationProtocol, Model model) throws Exception {
        settingServiceImpl.getAllProvinceList(model);
        return "registrationProtocol/addRegistrationProtocol";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addRegistrationProtocol(RegistrationProtocol registrationProtocol, HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("addRegistrationProtocol {}", registrationProtocol);
            if (StringUtils.isEmpty(registrationProtocol.getCityId())) {
                User user = MidlandHelper.getCurrentUser(request);
                registrationProtocol.setCityId(user.getCityId());
                registrationProtocol.setCityName(user.getCityName());
            }
            registrationProtocol.setCreateTime(MidlandHelper.getCurrentTime());
            registrationProtocolServiceImpl.insertRegistrationProtocol(registrationProtocol);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addRegistrationProtocol异常 {}", registrationProtocol, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_registrationProtocol")
    public String getRegistrationProtocolById(Integer id, Model model) {
        log.info("getRegistrationProtocolById  {}", id);
        RegistrationProtocol result = registrationProtocolServiceImpl.selectRegistrationProtocolById(id);
        model.addAttribute("item", result);
        return "registrationProtocol/updateRegistrationProtocol";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteRegistrationProtocolById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("deleteRegistrationProtocolById  {}", id);
            registrationProtocolServiceImpl.deleteRegistrationProtocolById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteRegistrationProtocolById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateRegistrationProtocol(Integer id, Model model) throws Exception {
        RegistrationProtocol result = registrationProtocolServiceImpl.selectRegistrationProtocolById(id);
        model.addAttribute("item", result);
        settingServiceImpl.getAllProvinceList(model);
        return "registrationProtocol/updateRegistrationProtocol";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateRegistrationProtocolById(RegistrationProtocol registrationProtocol) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("updateRegistrationProtocolById  {}", registrationProtocol);
            registrationProtocolServiceImpl.updateRegistrationProtocolById(registrationProtocol);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateRegistrationProtocolById  {}", registrationProtocol, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findRegistrationProtocolList(RegistrationProtocol registrationProtocol, Model model, HttpServletRequest request) {
        try {
            log.info("findRegistrationProtocolList  {}", registrationProtocol);
            MidlandHelper.doPage(request);
            Page<RegistrationProtocol> result = (Page<RegistrationProtocol>) registrationProtocolServiceImpl.findRegistrationProtocolList(registrationProtocol);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findRegistrationProtocolList  {}", registrationProtocol, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "registrationProtocol/registrationProtocolList";
    }
}
