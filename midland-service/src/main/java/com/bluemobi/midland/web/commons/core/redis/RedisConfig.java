package com.bluemobi.midland.web.commons.core.redis;


import com.bluemobi.midland.web.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Autowired
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        
        return stringRedisSerializer;
    }

/*    @Bean
    public Jackson2JsonRedisSerializer<ShoppingCart> jacksonJsonRedisJsonSerializer() {
        Jackson2JsonRedisSerializer<ShoppingCart> jacksonJsonRedisJsonSerializer =
                new Jackson2JsonRedisSerializer<ShoppingCart>(ShoppingCart.class);
        return jacksonJsonRedisJsonSerializer;
    }*/
    
    
    @Bean
    public Jackson2JsonRedisSerializer<Region> jacksonJsonRedisJsonSerializer2() {
        Jackson2JsonRedisSerializer<Region> jacksonJsonRedisJsonSerializer =
                new Jackson2JsonRedisSerializer<Region>(Region.class);
        return jacksonJsonRedisJsonSerializer;
    }

/*    @Bean(name="shoppingCartDtoRedisTemplate")
    public RedisTemplate<String, ShoppingCart> redisTemplate() {
        RedisTemplate<String, ShoppingCart> redisTemplate = new RedisTemplate<String, ShoppingCart>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
        return redisTemplate;
    }
    */
    
    @Bean(name="regionRedisTemplate")
    public RedisTemplate<String, Region> regionRedis() {
        RedisTemplate<String, Region> redisTemplate = new RedisTemplate<String, Region>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer2());
        return redisTemplate;
    }
    

}
