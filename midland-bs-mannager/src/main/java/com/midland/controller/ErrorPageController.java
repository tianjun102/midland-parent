package com.midland.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.Paginator;
import com.midland.base.BaseFilter;
import com.midland.web.model.ErrorPage;
import com.midland.web.service.ErrorPageService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("all")
@RequestMapping("/errorPage/")
public class ErrorPageController extends BaseFilter {

    private Logger log = LoggerFactory.getLogger(ErrorPageController.class);
    @Autowired
    private ErrorPageService errorPageServiceImpl;

    /**
     *
     **/
    @RequestMapping("index")
    public String errorPageIndex(ErrorPage errorPage, Model model) throws Exception {
        List<ErrorPage> errorPageList = errorPageServiceImpl.findErrorPageList(errorPage);
        if (errorPageList != null && errorPageList.size() > 0) {
            errorPage = errorPageList.get(0);
        }
        model.addAttribute("items", errorPage);
        return "errorPage/errorPageIndex";
    }

    /**
     *
     **/
    @RequestMapping("to_add")
    public String toAddErrorPage(ErrorPage errorPage, Model model) throws Exception {
        return "errorPage/addErrorPage";
    }

    /**
     * 新增
     **/
    @RequestMapping("add")
    @ResponseBody
    public Object addErrorPage(ErrorPage errorPage) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("addErrorPage {}", errorPage);
            if (errorPage.getId() == null) {
                errorPageServiceImpl.insertErrorPage(errorPage);
            } else {
                errorPageServiceImpl.updateErrorPageById(errorPage);
            }
            map.put("state", 0);
        } catch (Exception e) {
            log.error("addErrorPage异常 {}", errorPage, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 查询
     **/
    @RequestMapping("get_errorPage")
    public String getErrorPageById(Integer id, Model model) {
        log.info("getErrorPageById  {}", id);
        ErrorPage result = errorPageServiceImpl.selectErrorPageById(id);
        model.addAttribute("item", result);
        return "errorPage/updateErrorPage";
    }

    /**
     * 删除
     **/
    @RequestMapping("delete")
    @ResponseBody
    public Object deleteErrorPageById(Integer id) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("deleteErrorPageById  {}", id);
            errorPageServiceImpl.deleteErrorPageById(id);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("deleteErrorPageById  {}", id, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     *
     **/
    @RequestMapping("to_update")
    public String toUpdateErrorPage(Integer id, Model model) throws Exception {
        ErrorPage result = errorPageServiceImpl.selectErrorPageById(id);
        model.addAttribute("item", result);
        return "errorPage/updateErrorPage";
    }

    /**
     * 更新
     **/
    @RequestMapping("update")
    @ResponseBody
    public Object updateErrorPageById(ErrorPage errorPage) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            log.info("updateErrorPageById  {}", errorPage);
            errorPageServiceImpl.updateErrorPageById(errorPage);
            map.put("state", 0);
        } catch (Exception e) {
            log.error("updateErrorPageById  {}", errorPage, e);
            map.put("state", -1);
        }
        return map;
    }

    /**
     * 分页，这里建议使用插件（com.github.pagehelper.PageHelper）
     **/
    @RequestMapping("list")
    public String findErrorPageList(ErrorPage errorPage, Model model, HttpServletRequest request) {
        try {
            log.info("findErrorPageList  {}", errorPage);
            MidlandHelper.doPage(request);
            Page<ErrorPage> result = (Page<ErrorPage>) errorPageServiceImpl.findErrorPageList(errorPage);
            Paginator paginator = result.getPaginator();
            model.addAttribute("paginator", paginator);
            model.addAttribute("items", result);
        } catch (Exception e) {
            log.error("findErrorPageList  {}", errorPage, e);
            model.addAttribute("paginator", null);
            model.addAttribute("items", null);
        }
        return "errorPage/errorPageList";
    }
}
