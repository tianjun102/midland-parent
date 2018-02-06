package com.midland.base;

import com.midland.web.model.Category;
import com.midland.web.model.SiteMap;
import com.midland.web.service.CategoryService;
import com.midland.web.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/7/27.
 */
@Controller
public abstract class BaseFilter {

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        InitStringToNull stringEditor = new InitStringToNull();
        //InitSensitive sensitive = new InitSensitive();
        binder.registerCustomEditor(String.class, stringEditor);
        //binder.registerCustomEditor(String.class,sensitive);
    }


    @ExceptionHandler({Exception.class})
    public void handlerException(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        if (e instanceof DuplicateKeyException) {
            responseInfo(response, "数据已存在，请检查！...");
        } else {
            responseInfo(response, "系统繁忙，请重试！...");
        }

    }

    private void responseInfo(HttpServletResponse response, String info) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().print(info);
    }

}


