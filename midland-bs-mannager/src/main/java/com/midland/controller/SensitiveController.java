package com.midland.controller;

import com.midland.base.BaseFilter;
import com.midland.web.service.impl.PublicService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@SuppressWarnings("all")
@RequestMapping("/sensitive/")
public class SensitiveController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(SensitiveController.class);
    @Autowired
    private PublicService publicServiceImpl;

    /**
     *
     **/
    @RequestMapping("index")
    public String sensitiveIndex(HttpServletRequest request, Model model) throws Exception {
        return "setting/sensitiveWord";
    }

    @RequestMapping("list")
    public String sensitiveList(HttpServletRequest request, Model model) throws Exception {
        String keyWord = request.getParameter("keyWord");
        List<String> list = publicServiceImpl.sensitiveList();
        if (StringUtils.isNotEmpty(keyWord)) {
            List<String> temp = list.stream().filter(e ->
                    e.contains(keyWord)).sorted((s1, s2) ->
                    s1.compareTo(s2)).collect(Collectors.toList());
            model.addAttribute("items", temp);

        } else {
            List<String> temp = list.stream().sorted((s1, s2) ->
                    s1.compareTo(s2)).collect(Collectors.toList());
            model.addAttribute("items", list);

        }
        return "setting/sensitiveList";

    }


    @RequestMapping("add")
    @ResponseBody
    public Object sensitiveAdd(HttpServletRequest request, Model model) {
        Map map = new HashMap();
        try {

            String V = request.getParameter("V");
            if (StringUtils.isNotEmpty(V)) {
                publicServiceImpl.addSet(V);
                map.put("state", 0);
            } else {
                map.put("state", -1);
            }
        } catch (Exception e) {
            map.put("state", -1);
        }
        return map;
    }

    @RequestMapping("del")
    @ResponseBody
    public Object sensitiveDel(HttpServletRequest request, Model model) {
        Map map = new HashMap();
        String keyWord = request.getParameter("keyWord");
        try {
            keyWord = java.net.URLDecoder.decode(keyWord,"UTF-8");
            publicServiceImpl.moveSet(keyWord);
            map.put("state", 0);
        } catch (Exception e1) {
            e1.printStackTrace();
            map.put("state", -1);
        }
        return map;
    }

}
