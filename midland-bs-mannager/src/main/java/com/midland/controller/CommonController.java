package com.midland.controller;

import com.midland.base.BaseFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共视图控制器
 *
 * @author
 * @since 2016年4月15日 下午4:16:34
 **/
@Controller
public class CommonController extends BaseFilter {
    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping("index")
    public String index(HttpServletRequest request) {

        return "index";
    }

}