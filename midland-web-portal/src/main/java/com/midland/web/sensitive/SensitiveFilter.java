package com.midland.web.sensitive;

import com.alibaba.fastjson.JSONObject;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.service.impl.PublicService;
import com.midland.web.util.HTMLSpirit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SensitiveFilter implements HandlerInterceptor {
    @Autowired
    private PublicService publicServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws IOException {
        StringBuffer sb = new StringBuffer();

        if (!request.getRequestURI().contains("sensitive")&&request.getRequestURI().contains("/add")) {//对敏感字符管理的请求不要拦截
            Set set = publicServiceImpl.getSensitiveSet();
            SensitivewordFilter filter = new SensitivewordFilter(set);
            Map<String, String[]> map = request.getParameterMap();
            if (map.size()<1){
                BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
                Set<Map.Entry<String, Object>> param= jsonObject.entrySet();
                param.forEach(item -> {
                    if (item.getValue() != null && !item.getValue().equals("")) {
                        String string = String.valueOf(item.getValue());
                        Set<String> set1 = filter.getSensitiveWord(string, 2);
                        if (set1.size() > 0) {
                            String s = set1.iterator().next();
                            returnErrorMessage(response, "内容包括违规字：" + s + ",请修正后在保存，谢谢");
                            sb.append("false");
                        }
                    }
                });
            }else{
                map.entrySet().forEach(item -> {
                    if (item.getValue() != null && !item.getValue().equals("")) {
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

        }
        if (sb.toString().contains("false")) {
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
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try {
            JSONObject res = new JSONObject();
            res.put("state", ResultStatusUtils.STATUS_CODE_205);
            res.put("msg",errorMessage);
            out = response.getWriter();
            out.append(res.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
