package com.midland.web.annocontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {



    @RequestMapping("/test")
    public Object test(){
        Map map = new HashMap();
        map.put("state","0");
        map.put("msg","success");
        return map;
    }

}
