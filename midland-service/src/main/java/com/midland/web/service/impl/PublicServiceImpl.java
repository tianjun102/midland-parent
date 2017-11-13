package com.midland.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PublicServiceImpl implements PublicService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void setV(String K, String V, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(K, V);
        if (timeout == null || timeUnit != null) {
            redisTemplate.expire(K, timeout, timeUnit);
        }
    }

    @Override
    public Object getV(String K) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        if (vo == null){
            return null;
        }
        return vo.get(K);
    }


}
