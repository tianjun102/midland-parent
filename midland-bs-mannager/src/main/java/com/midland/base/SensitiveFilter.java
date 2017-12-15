package com.midland.base;

import com.alibaba.fastjson.JSONArray;
import com.midland.web.commons.Result;
import com.midland.web.util.HTMLSpirit;
import com.midland.web.util.JsonMapReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SensitiveFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) {



        Set<String> set = new HashSet<>();
        Arrays.asList(JsonMapReader.getSensitive("sensitive").split(",")).forEach(e1 ->
                set.add(e1)
        );
        SensitivewordFilter filter = new SensitivewordFilter(set);

       Map<String, String[]> map =  request.getParameterMap();
        map.entrySet().forEach(item->{
            if(item.getValue() !=null || !item.getValue().equals("")){
                String string = Arrays.toString(item.getValue());
                string = HTMLSpirit.delHTMLTag(string);
                Set<String> set1 = filter.getSensitiveWord(string, 2);
                if (set1.size()>0){
                    returnErrorMessage(response,"内容包括违规字："+set1.iterator().next()+",请修正后在保存，谢谢");
                }
            }
        });

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private void returnErrorMessage(HttpServletResponse response, String errorMessage)  {

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
