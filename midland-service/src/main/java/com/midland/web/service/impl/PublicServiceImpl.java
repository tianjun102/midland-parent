package com.midland.web.service.impl;

import com.midland.core.util.SmsUtil;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.commons.Result;
import com.midland.web.commons.core.util.ResultStatusUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class PublicServiceImpl implements PublicService {
    @Resource
    private ApiHelper apiHelper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(PublicServiceImpl.class);

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


    @Override
    public Object codeCheck(String phone, String vCode, String key) {
        Result result = new Result();
        try {
            String redisVCode = (String) getV(key);
            if (redisVCode.equals(vCode)) {
                result.setCode(ResultStatusUtils.STATUS_CODE_200);
                result.setMsg("success");
            }else {
                result.setCode(ResultStatusUtils.STATUS_CODE_203);;
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
     *
     * @param phone 手机号
     * @param tpId 短信模板id   美联提供
     * @param vCode 验证码
     * @param key 验证码保存到redis的key值
     * @param codeEffective 有效时间
     * @param timeUnit 有效时间单位
     * @return
     */
    @Override
    public Object sendCode(String phone,int tpId,String vCode,String key,int codeEffective,TimeUnit timeUnit) {
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

}
