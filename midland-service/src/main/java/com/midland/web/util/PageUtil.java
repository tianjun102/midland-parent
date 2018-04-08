package com.midland.web.util;

import com.github.pagehelper.PageHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 'ms.x' on 2017/7/31.
 */
public class PageUtil {
    /**
     * request 包含 pageNo，pageSize
     *
     * @param request
     */
    public static void doPage(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");

        if (pageNo == null || pageNo.equals("")) {
            pageNo ="1";
        }
        if (pageSize == null || pageSize.equals("")) {

            pageSize = "10";
        }
        PageHelper.startPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
    }
}

