package com.midland.web.service.impl;

import com.midland.core.redis.IBaseRedisTemplate;
import com.midland.web.Contants.Contant;
import com.midland.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 'ms.x' on 2017/9/20.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private IBaseRedisTemplate baseRedisTemplate;

    /**
     * 0关闭,1开启
     * @param value
     */
    @Override
    public void setAnswerAuditFlag(int value) {

        baseRedisTemplate.saveValue(Contant.answerAuditKey, value);
    }
    @Override
    public Integer getAnswerAuditFlag() {

        Integer result = (Integer) baseRedisTemplate.getValueByKey(Contant.answerAuditKey);
        if (result == null) {
            //如果没有获取到值，说明还未设置，直接设置为“未开启”
            setAnswerAuditFlag(Contant.answerAuditClose);
            return Contant.answerAuditClose;
        }
        return result;
    }
    /**
     * 0关闭,1开启
     * @param value
     */
    @Override
    public void setInformationOpenFlag(int value) {

        baseRedisTemplate.saveValue(Contant.informationBannerKey, value);
    }

    @Override
    public Integer getInformationOpenFlag() {
        Integer result = (Integer) baseRedisTemplate.getValueByKey(Contant.informationBannerKey);
        if (result == null) {
            //如果没有获取到值，说明还未设置，直接设置为“未开启”
            setAnswerAuditFlag(Contant.informationBannerClose);
            return Contant.informationBannerClose;
        }
        return result;
    }

    @Override
    public Object getValue(String key) {
        return baseRedisTemplate.getValueByKey(key);
    }

    @Override
    public void setValue(String key, Object value) {
        baseRedisTemplate.saveValue(key, value);
    }

}
