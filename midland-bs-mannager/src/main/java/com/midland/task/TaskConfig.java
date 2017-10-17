package com.midland.task;

import com.midland.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 暂时没用
 */
@Component
public class TaskConfig {
    private Integer appointmentWarn;
    private String appointmentWarnKey ="appointmentWarnKey";
    private Integer appointClose;
    private String appointCloseKey = "appointCloseKey";


    @Autowired
    private RedisService redisServiceImpl;

    public Integer getAppointmentWarn(){
        if (appointmentWarn == null){
           Integer value = redisServiceImpl.getValue(appointmentWarnKey);
           if (value == null){
               redisServiceImpl.setValue(appointmentWarnKey,0);
               appointmentWarn = 0;
           }else{
               appointmentWarn =value;
           }
        }
        return appointmentWarn;
    }


    public void setAppointmentWarn(Integer appointmentWarn) {
        this.appointmentWarn = appointmentWarn;
        redisServiceImpl.setValue(appointmentWarnKey, appointmentWarn);
    }


    public Integer getAppointClose(){
        if (appointClose == null){
            Integer value = redisServiceImpl.getValue(appointCloseKey);
            if (value == null){
                redisServiceImpl.setValue(appointCloseKey,0);
                appointClose = 0;
            }else{
                appointClose =value;
            }
        }
        return appointClose;
    }


    public void setAppointClose(Integer appointClose) {
        this.appointClose = appointClose;
        redisServiceImpl.setValue(appointCloseKey, appointClose);
    }

}
