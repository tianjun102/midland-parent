package com.midland.web.service;

/**
 * Created by 'ms.x' on 2017/9/20.
 */
public interface RedisService {
    void setAnswerAuditFlag(int value);

    void setInformationOpenFlag(int value);

    Integer getAnswerAuditFlag();

    Integer getInformationOpenFlag();

    Object getValue(String key);

    void setValue(String key, Object value);
}
