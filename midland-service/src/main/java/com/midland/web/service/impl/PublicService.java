package com.midland.web.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface PublicService {
    void setV(String K, Object V, Integer timeout, TimeUnit timeUnit);

    void listLeftPush(String K, Object V, Integer timeout, TimeUnit timeUnit);

    void listLeftPush(String K, Object V);

    void listRemove(String K, Object V);

    void setV(String K, Object V);

    Object getV(String K);

    List getList(String K);

    String getCode(String K, String prefix);

    Object codeCheck(String phone, String vCode, String key);

    Object sendCode(String phone, int tpId, String vCode, String key, int codeEffective, TimeUnit timeUnit);

    void removeAll(String K);
}
