package com.midland.core.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface IBaseRedisTemplate <V, HK>{
	
	public int delByKeys(final String[] keys);
	
	public Long incrByKey(final String keys, final long num);
	
	public Long decrByKey(final String keys, final long num);
	
	public Set<byte[]> getKeysLike(final String pattern);

	public void saveValue(String key, V value);

	public void saveValue(String key, V value, long offset);

	public void saveValue(String key, V value, long timeout, TimeUnit unit);
	
	public void set(final byte[] key, final byte[] value, final long liveTime);

	public V getValueByKey(String key);

	public int addListItem(String key, V value);

	public int removeListItem(String key, long count, V value);
	
	public long del(final String... keys);

	public int getListSize(String key);

	public List<V> getListByKey(String key);

	public List<V> getListByKeyAndIndex(String key, long start, long end);

	public void putHashItem(String key, HK hashKey, V value);

	public void removeHashItem(String key, HK[] hashKeys);

	public int getHashSize(String key);

	public HashMap<HK, V> getHashByKey(String key);

	public List<V> getHashValuesByKey(String key);

	public Set<HK> getHashKeysByKey(String key);

	public V getHashValueByKeyAndHashKey(String key, HK hashKey);
	
	public boolean exists(final String key);
	
	public String flushDB();
	
	public long dbSize();
	
	public String ping();
	
}
