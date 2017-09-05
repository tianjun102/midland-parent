package com.midland.test.dao;

import javax.annotation.Resource;

import org.junit.Test;
import com.midland.core.feature.test.TestSupport;
import com.midland.web.dao.UserMapper;
import org.springframework.beans.factory.annotation.Value;

public class UserMapperTest extends TestSupport {

    
    @Value("APIURL")
    private String APIURL;

    @Test
    public void test_selectByExampleAndPage() {
        /*start();
        Page<User> page = new Page<>(1, 3);
        UserExample example = new UserExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<User> users = userMapper.selectByExampleAndPage(page, example);
        for (User user : users) {
            System.err.println(user);
        }
        end();*/
    }
    
    @Test
    public void dsfwef(){
        System.out.println(APIURL);
    }
}
