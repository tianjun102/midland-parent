package com.bluemobi.midland.web.commons.core.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class DemoJedis {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	public void save(){
		
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		
		opsForValue.set("testRedis", "保存成功了！！！！！");
	};

}
