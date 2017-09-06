package com.midland.core.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * JedisClusterFactory
 * @category          JedisClusterFactory
 * @projectName  	 hxin-common-cache-client
 * @package          org.hxin.common.cache.client.api.JedisClusterFactory.java
 * @className        JedisClusterFactory
 * @description      
 * @author           huangpq
 * @createDate       2017-3-14
 * @updateUser       huangpq
 * @updateDate       2017-3-14
 * @updateRemarۈۍ
 * @version          v0.0.1
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
	private Resource addressConfig;
	private String addressKeyPrefix ;
	private JedisCluster jedisCluster;
	private Integer timeout;
	private Integer maxRedirections;
	private GenericObjectPoolConfig genericObjectPoolConfig;
	private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");	

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		Set<HostAndPort> haps = this.parseHostAndPort();
		jedisCluster = new JedisCluster(haps, timeout, maxRedirections,genericObjectPoolConfig);
	}
	private Set<HostAndPort> parseHostAndPort() throws Exception {
		try {
			Properties prop = new Properties();
			prop.load(this.addressConfig.getInputStream());
			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object key : prop.keySet()) {
				if (!((String) key).startsWith(addressKeyPrefix)) {
					continue;
				}
				String val = (String) prop.get(key);
				boolean isIpPort = p.matcher(val).matches();
				if (!isIpPort) {
					throw new IllegalArgumentException("ip 格式错误！");
				}
				String[] ipAndPort = val.split(":");
				HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
				haps.add(hap);
			}

			return haps;
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new Exception("无效的参数异常！",ex);
		}
	}
	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = addressConfig;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}
}