package com.midland.core.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.JedisCluster;

/**
 * @author huangpq
 * @Date 2017-3-14
 * 
 */
public class ClusterConnect {
	private JedisCluster jedisCluster;
	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	/**
	 * @param key 
	 * @param value
	 * @return
	 */
	public String set(String key, String value) {
		return getJedisCluster().set(key, value);
	}
	/**
	 * @param value
	 * @param seconds
	 * @return
	 */
	public String set(String key, String value,int seconds) {
		return getJedisCluster().setex(key,seconds,value);
	}
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return getJedisCluster().get(key);
	}
	//////////////////////////////////////////////////////////////////////codis map////////////////////////////////////////////
	/**
	 * @param cacheKey codis
	 * @param mapKey   map
	 * @param mapValue map
	 * @return
	 */
	public long reSetMap(String cacheKey, String mapKey,String mapValue){
    	return getJedisCluster().hset(cacheKey, mapKey,mapValue);
    }
	/**
	 * @param cacheKey
	 * @param mapKey
	 * @return
	 */
	public boolean hexists(String cacheKey, String mapKey){
    	return getJedisCluster().hexists(cacheKey, mapKey);
    }
    /**
     * @param key 
     * @param hash map
     */
    public String setMap(String key, Map<String, String> hash){
    	return getJedisCluster().hmset(key, hash);
    }
    /**
     * 
     * @param key 
     * @param fields
     * @return
     */
    public List<String> getMap(String key, String... fields){
    	return getJedisCluster().hmget(key, fields);
    }
    /**
     * @param key 
     * @param field 
     * @return
     */
    public String getMapVal(String key, String field){
    	return getJedisCluster().hget(key, field);
    }
    /**
     * 
     * @param key
     * @return
     */
    public Map<String,String> getMapAll(String key){
    	return getJedisCluster().hgetAll(key);
    }
    /**
     * @param key
     * @return
     */
    public Set<String> getMapKeys(String key){
    	return getJedisCluster().hkeys(key);
    }
    /**
     * @param key
     * @return
     */
    public List<String> getMapVals(String key){
    	return getJedisCluster().hvals(key);
    }
    /**
     * @param key
     * @param fields
     * @return
     */
    public Long delMap(String key, String... fields){
    	return getJedisCluster().hdel(key, fields);
    }

	/**
	 * @param key
	 * @return
	 */
	public Boolean exists(String key) {
		return getJedisCluster().exists(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public Long del(String key) {
		return getJedisCluster().del(key);
	}

	/**
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Long expire(String key, int seconds) {
		return getJedisCluster().expire(key, seconds);
	}
	/**
	 * @param key
	 * @return
	 */
	public Long persist(String key){
		return getJedisCluster().persist(key);
	}


	/**
	 * @param key
	 * @return
	 */
	public Long getExpireTime(String key){
		return getJedisCluster().ttl(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public Long append(String key, String value) {
		return getJedisCluster().append(key, value);
	}

	/**
	 * @param key
	 * @param members
	 * @return
	 */
	public Long addMembers(String key, String... members){
		return getJedisCluster().sadd(key, members);
	}
	/**
	 * @param key
	 * @param members
	 * @return
	 */
	public Long removeMembers(String key, String... members){
		return getJedisCluster().srem(key, members);
	}
	/**
	 * @param key
	 * @return
	 */
	public Set<String> getMembers(String key) {
		return getJedisCluster().smembers(key);
	}
	/**
	 * @param key
	 * @param member
	 * @return
	 */
	public Boolean isMember(String key, String member){
		return getJedisCluster().sismember(key, member);
	}
	/**
	 * @param key
	 * @return
	 */
	public Long getMemberCount(String key){
		return getJedisCluster().scard(key);
	}

}