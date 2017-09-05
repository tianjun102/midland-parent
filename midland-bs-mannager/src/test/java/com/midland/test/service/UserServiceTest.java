package com.midland.test.service;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.midland.core.util.ApplicationUtils;
import com.midland.core.feature.test.TestSupport;
import com.midland.core.util.HttpUtils;
import com.midland.web.model.LinkUrlManager;
import com.midland.web.service.JdbcService;
import com.midland.web.service.LinkUrlManagerService;
import com.midland.web.util.MidlandHelper;
import com.midland.web.model.user.User;
import com.midland.web.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

public class UserServiceTest extends TestSupport {
    
    
    @Autowired
    private LinkUrlManagerService linkUrlManagerServiceImpl;

    @Resource
    private UserService userService;

     @Resource
    private JdbcService jdbcService;

    
    
    @Test
    public void sdfsf(){
        LinkUrlManager ob=new LinkUrlManager();
        try {
            Page<LinkUrlManager> result = (Page<LinkUrlManager>) linkUrlManagerServiceImpl.findLinkUrlManagerList(ob);
            System.out.println(result.getPageNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Test
    public void sdfdsfdsfsf(){
        LinkUrlManager ob=new LinkUrlManager();
        try {
            LinkUrlManager result = linkUrlManagerServiceImpl.selectById(1);
            System.out.println(result.getCityId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    @Test
    public void test_insert() {
        User model = new User();
        model.setUsername("");
        model.setPassword(ApplicationUtils.sha256Hex("123456"));
        model.setCreateTime(MidlandHelper.getCurrentTime());
        userService.insert(model);
    }

    @Test
    public void test_10insert() {
        for (int i = 0; i < 10; i++) {
            User model = new User();
            model.setUsername("" + i);
            model.setPassword(ApplicationUtils.sha256Hex("123456"));
            model.setCreateTime(MidlandHelper.getCurrentTime());
            userService.insert(model);
        }
    }

    @Test
    public void testSend(){
        Map<String,String> map = new HashMap<>();
        map.put("webKey","eb043d9ddf9e1a275755da3307cf80fe58bdeff635a595eb89293b8367989935");//短信密钥
        map.put("sessionId","");
        map.put("bizAppId","F0000006");//应用id
        map.put("customerId","test");
        map.put("sendType","1");
        map.put("userId","admin");
        map.put("phones","13600158343");
        map.put("cont","11111111");
        map.put("type","13");
        map.put("ucCode","");

        String result = HttpUtils.post("http://www.bizapp.com/api/SmsService/SendSMS",map);
        System.out.println(result);


    }

}
