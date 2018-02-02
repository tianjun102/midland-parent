package com.midland.base;

import com.midland.web.service.impl.PublicService;
import com.midland.web.util.HTMLSpirit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SensitiveFilter implements HandlerInterceptor {
    @Autowired
    private PublicService publicServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {
        Set set = publicServiceImpl.getSensitiveSet();
        SensitivewordFilter filter = new SensitivewordFilter(set);
        StringBuffer sb = new StringBuffer();
        Map<String, String[]> map = request.getParameterMap();
        if (!request.getRequestURI().contains("sensitive/add")) {//对敏感字符管理的请求不要拦截
            map.entrySet().forEach(item -> {
                if (item.getValue() != null || !item.getValue().equals("")) {
                    String string = Arrays.toString(item.getValue());
                    string = HTMLSpirit.delHTMLTag(string);
                    Set<String> set1 = filter.getSensitiveWord(string, 2);
                    if (set1.size() > 0) {
                        String s = set1.iterator().next();
                        returnErrorMessage(response, "内容包括违规字：" + s + ",请修正后在保存，谢谢");
                        sb.append("false");
                    }
                }
            });
        }
        if (sb.toString().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private void returnErrorMessage(HttpServletResponse response, String errorMessage) {

        try {
            response.setContentType("application/json");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.print(errorMessage);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
