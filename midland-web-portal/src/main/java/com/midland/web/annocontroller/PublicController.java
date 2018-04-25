package com.midland.web.annocontroller;

import com.midland.web.Contants.Contant;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {
    private static Logger logger = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    private RedisService redisServiceImpl;


    @RequestMapping("/test")
    public Object test(){
        Map map = new HashMap();
        map.put("state","0");
        map.put("msg","成功");
        return map;
    }

    @RequestMapping("/video")
    public Object getVideo(){
        Result result=new Result();
        try {
            logger.info("getVideo"  );
            String videoUrl = (String)redisServiceImpl.getValue(Contant.MIDLAND_VIDEO_URL_KEY);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
            result.setModel(videoUrl);
        } catch(Exception e) {
            logger.error("getVideo异常 {}",e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("service error");
        }
        return result;
    }

}
