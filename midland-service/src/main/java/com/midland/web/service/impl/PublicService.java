package com.midland.web.service.impl;

import java.util.concurrent.TimeUnit;

public interface PublicService {
    void setV(String K, String V, Integer timeout, TimeUnit timeUnit);

    void setV(String K, String V);

    Object getV(String K);

    String getCode(String prefix);

    Object codeCheck(String phone, String vcode, String key);

    Object sendCode(String phone, int tpId, String vCode, String key, int codeEffective, TimeUnit timeUnit);
}
