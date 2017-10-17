package com.midland.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
@SuppressWarnings("all")
public class BaseRedisTemplate<V, HK> implements IBaseRedisTemplate<V, HK> {

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	@Resource(name = "redisTemplate")
	protected ValueOperations<Object, V> valueOperations;

	@Resource(name = "redisTemplate")
	protected ListOperations<Object, V> listOps;

	@Resource(name = "redisTemplate")
	protected HashOperations<Object, HK, V> hashOps;

	@SuppressWarnings("unchecked")
	public int delByKeys(final String[] keys) {
		return ((Integer) this.redisTemplate.execute(new RedisCallback() {
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				int count = 0;
				for (int i = 0; i < keys.length; i++)
					count += connection
							.del(new byte[][] { keys[i].getBytes() })
							.intValue();
				return Integer.valueOf(count);
			}
		})).intValue();
	}
	@SuppressWarnings("unchecked")
	public Long incrByKey(final String keys, final long num) {
		return (Long) this.redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.incrBy(keys.getBytes(), num);
			}
		});
	}
	@SuppressWarnings("unchecked")
	public Long decrByKey(final String keys, final long num) {
		return (Long) this.redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.decrBy(keys.getBytes(), num);
			}
		});
	}
	@SuppressWarnings("unchecked")
	public Set<byte[]> getKeysLike(final String pattern) {
		return (Set) this.redisTemplate.execute(new RedisCallback() {
			public Set<byte[]> doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.keys(pattern.getBytes());
			}
		});
	}
	/**
     * @param key
     * @param value
     * @param liveTime
     */
	@SuppressWarnings("unchecked")
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }
	public void saveValue(String key, V value) {
		this.valueOperations.set(key, value);
	}
	 /**
     * @param key
     * @return
     */
	@SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return (boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }
	/**
     * @return
     */
	@SuppressWarnings("unchecked")
    public String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
	/**
     * @return
     */
	@SuppressWarnings("unchecked")
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }
	 /**
     * @param keys
     */
	@SuppressWarnings("unchecked")
    public long del(final String... keys) {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }
    /**
     * @return
     */
	@SuppressWarnings("unchecked")
    public long dbSize() {
        return (long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }
	public void saveValue(String key, V value, long offset) {
		this.valueOperations.set(key, value, offset);
	}

	public void saveValue(String key, V value, long timeout, TimeUnit unit) {
		this.valueOperations.set(key, value, timeout, unit);
	}

	public V getValueByKey(String key) {
		return this.valueOperations.get(key);
	}

	public int addListItem(String key, V value) {
		return this.listOps.leftPush(key, value).intValue();
	}

	public int removeListItem(String key, long count, V value) {
		return this.listOps.remove(key, count, value).intValue();
	}

	public int getListSize(String key) {
		return this.listOps.size(key).intValue();
	}

	public List<V> getListByKey(String key) {
		List l = this.listOps.range(key, 0L, -1L);
		return l;
	}

	public List<V> getListByKeyAndIndex(String key, long start, long end) {
		List l = this.listOps.range(key, start, end);
		return l;
	}

	public void putHashItem(String key, HK hashKey, V value) {
		this.hashOps.put(key, hashKey, value);
	}

	public void removeHashItem(String key, HK[] hashKeys) {
		this.hashOps.delete(key, hashKeys);
	}

	public int getHashSize(String key) {
		return this.hashOps.size(key).intValue();
	}

	public HashMap<HK, V> getHashByKey(String key) {
		HashMap m = (HashMap) this.hashOps.entries(key);
		return m;
	}

	public List<V> getHashValuesByKey(String key) {
		List l = this.hashOps.values(key);
		return l;
	}

	public Set<HK> getHashKeysByKey(String key) {
		Set s = this.hashOps.keys(key);
		return s;
	}

	public V getHashValueByKeyAndHashKey(String key, HK hashKey) {
		return this.hashOps.get(key, hashKey);
	}

}