package com.midland.web.service.impl;

import java.util.concurrent.TimeUnit;

public interface PublicService {
    void setV(String K, String V, Integer timeout, TimeUnit timeUnit);

    Object getV(String K);
}
