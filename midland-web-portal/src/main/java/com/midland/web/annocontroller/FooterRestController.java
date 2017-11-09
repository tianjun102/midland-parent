package com.midland.web.annocontroller;

import com.github.pagehelper.Page;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.model.Footer;
import com.midland.web.service.FooterService;
import com.midland.web.util.MidlandHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/foolter/")
@RestController
public class FooterRestController {
    private static Logger logger = LoggerFactory.getLogger(FooterRestController.class);
    @Autowired
    private FooterService FooterServiceImpl;
    @RequestMapping("list")
    public Object list(@RequestBody Footer obj, HttpServletRequest request){
        Result result=new Result();
        try {
            MidlandHelper.doPage(request);
            Page<Footer> list = (Page<Footer>)FooterServiceImpl.findFooterList(obj);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            if (list.size()>0) {
                result.setModel(list.get(0));
            }
            result.setMsg("success");
        } catch (Exception e) {
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
            logger.error("list",e);
        }
        return result;
    }
}
