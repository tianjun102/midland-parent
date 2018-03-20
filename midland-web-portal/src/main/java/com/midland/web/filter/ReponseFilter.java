package com.midland.web.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReponseFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("P3P", "CP=CAO PSA OUR");
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(httpServletRequest.getMethod())) {
            httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
            httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
            httpServletResponse.addHeader("Access-Control-Max-Age", "120");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
