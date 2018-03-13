package com.midland.web.service.impl;

import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import com.midland.web.util.JsonMapReader;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PublicServiceImpl implements PublicService {
    @Resource
    private ApiHelper apiHelper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> redisStringTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PublicServiceImpl.class);

    @Override
    public void setV(String K, Object V, Integer timeout, TimeUnit timeUnit) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        vo.set(K, V);
        if (timeout != null && timeUnit != null) {
            redisTemplate.expire(K, timeout, timeUnit);
        }
    }

    @Override
    public void listLeftPush(String K, Object V, Integer timeout, TimeUnit timeUnit) {
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        vo.leftPush(K, V);
        if (timeout != null && timeUnit != null) {
            redisTemplate.expire(K, timeout, timeUnit);
        }
    }

    @Override
    public void listLeftPush(String K, Object V) {
        listLeftPush(K, V, null, null);
    }

    @Override
    public void listRemove(String K, Object V) {
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        vo.remove(K, 1, V);

    }


    @Override
    public void setV(String K, Object V) {
        setV(K, V, null, null);
    }

    @Override
    public Object getV(String K) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        if (vo == null) {
            return null;
        }
        return vo.get(K);
    }

    @Override
    public List getList(String K) {
        ListOperations<String, Object> vo = redisTemplate.opsForList();
        if (vo == null) {
            return null;
        }
        return vo.range(K, 0, 99);
    }

    @Override
    public void moveSet(Object V) throws Exception {
        SetOperations<String, Object> vo = redisTemplate.opsForSet();
        Long i = vo.remove(Contant.SENSITIVE_CACHE_KEY, V);
        if (i < 1) {
            throw new Exception("删除敏感词汇失败");
        }
    }

    @Override
    public void addSet(Object V) throws Exception {
        SetOperations<String, Object> vo = redisTemplate.opsForSet();
        Long i = vo.add(Contant.SENSITIVE_CACHE_KEY, V);
        if (i < 1) {
            throw new Exception("新增敏感词汇失败");
        }
    }
    @Override
    public List<String> sensitiveList() throws Exception {
        Set set=null;
        SetOperations<String, Object> vo = redisTemplate.opsForSet();
        if (vo == null) {
             set = Collections.EMPTY_SET;
        }else {
            /**
             * 取出10万个敏感字符
             */
            set = vo.distinctRandomMembers(Contant.SENSITIVE_CACHE_KEY, 100000);
        }
        List<String> list = (List<String>)set.stream().collect(Collectors.toList());
        List<String> list1 = list.stream().sorted().collect(Collectors.toList());
        return list1;
    }

    @Override
    public Set<String> getSensitiveSet() {
        SetOperations<String, String> vo = redisStringTemplate.opsForSet();
        if (vo == null) {
            return Collections.EMPTY_SET;
        }
        /**
         * 取出10万个敏感字符
         */
        Set<String> obj = vo.distinctRandomMembers(Contant.SENSITIVE_CACHE_KEY, 100000);
        if (obj.size() < 1) {//如果redis里没有敏感字符,说明还未存放,从sensitive.json里取出,并存放到redis
            Set<String> temp = new HashSet<>();
            Arrays.asList(JsonMapReader.getSensitive("sensitive").split(",")).forEach(e1 ->
                    temp.add(e1)
            );
            putSensitiveSet(temp);
            obj = temp;
        }
        return obj;
    }

    @Override
    public void putSensitiveSet(String V) {
        SetOperations<String, Object> vo = redisTemplate.opsForSet();
        vo.add(Contant.SENSITIVE_CACHE_KEY, V);
    }

    @Override
    public void putSensitiveSet(Set<String> V) {
        SetOperations<String, Object> vo = redisTemplate.opsForSet();
        Iterator<String> iterable = V.iterator();
        while (iterable.hasNext()) {
            String str = iterable.next();
            if (StringUtils.isNotEmpty(str)){
                vo.add(Contant.SENSITIVE_CACHE_KEY, str);
            }
        }


    }


    @Override
    public String getCode(String K, String prefix) {
        ValueOperations<String, Object> vo = redisTemplate.opsForValue();
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(prefix)) {
            sb.append(prefix);
        }
        sb.append(MidlandHelper.formatCode(vo.increment(K, 1).intValue()));
        return sb.toString();
    }


    @Override
    public Object codeCheck(String phone, String vCode, String key) {
        Result result = new Result();
        try {
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(vCode)) {
                result.setCode(ResultStatusUtils.STATUS_CODE_400);
                result.setMsg("IllegalArgumentException");
                return result;
            }
            String redisVCode = (String) getV(key);
            if (redisVCode.equals(vCode)) {
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("success");
            } else {
                result.setCode(ResultStatusUtils.STATUS_CODE_203);
                ;
                result.setMsg("error");
            }
        } catch (Exception e) {

            logger.error("codeCheck", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("error");
        }
        return result;
    }

    /**
     * @param phone         手机号
     * @param tpId          短信模板id   美联提供
     * @param vCode         验证码
     * @param key           验证码保存到redis的key值
     * @param codeEffective 有效时间
     * @param timeUnit      有效时间单位
     * @return
     */
    @Override
    public Object sendCode(String phone, int tpId, String vCode, String key, int codeEffective, TimeUnit timeUnit) {
        Result result = new Result();
        try {
            if (StringUtils.isEmpty(phone)) {
                throw new Exception("手机号码为空");
            }
            setV(key, vCode, codeEffective, timeUnit);
            List list = new ArrayList();
            list.add(vCode);
            list.add(String.valueOf(codeEffective));
            apiHelper.smsSender(phone, tpId, list);
            result.setCode(ResultStatusUtils.STATUS_CODE_200);
            result.setMsg("success");
        } catch (Exception e) {
            logger.error("发送验证码失败", e);
            result.setCode(ResultStatusUtils.STATUS_CODE_203);
            result.setMsg("发送验证码失败 ");
        }
        return result;
    }

    @Override
    public void removeAll(String K) {
        try {
            redisTemplate.delete(K);
        } catch (Exception e) {
            logger.error("removeAll：删除失败！", e);
        }

    }


}
