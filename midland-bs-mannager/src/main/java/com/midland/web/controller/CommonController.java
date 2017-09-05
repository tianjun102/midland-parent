package com.midland.web.controller;

import javax.servlet.http.HttpServletRequest;

import com.midland.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

/**
 * 公共视图控制器
 * 
 * @author 
 * @since 2016年4月15日 下午4:16:34
 **/
@Controller
public class CommonController extends BaseController{
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