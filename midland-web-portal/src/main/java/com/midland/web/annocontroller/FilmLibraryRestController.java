package com.midland.web.annocontroller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.midland.web.commons.FastJsonUtils;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.FilmLibrary;
import com.midland.web.service.FilmLibraryService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/film/")
@RestController
public class FilmLibraryRestController {
    private static Logger logger = LoggerFactory.getLogger(FilmLibraryRestController.class);
    @Autowired
    private FilmLibraryService filmLibraryServiceImpl;
    @RequestMapping("list")
    public Object list(@RequestBody FilmLibrary obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            Page<FilmLibrary> list = (Page<FilmLibrary>)filmLibraryServiceImpl.findFilmLibraryList(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setList(list);
            result.setMsg("success");
            result.setPaginator(list.getPaginator());
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("list",e);
        }
        return result;
    }
}
