package com.midland.task;

import com.midland.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 暂时没用
 */
@Component
public class TaskConfig {
    /**
     * 预约未处理告警间隔时间
     */
    private Double appointmentWarn;
    private String appointmentWarnKey ="appointmentWarnKey";
    /**
     * 预约关闭间隔时间
     */
    private Double appointClose;
    private String appointCloseKey = "appointCloseKey";
    private Double initTime=0.5;


    /**
     * 任务执行间隔时间
     */
    private Double taskInterval;
    private String taskIntervalKey="taskIntervalKey";
    private Double initTaskInterval=0.5;

    /**
     * 预约管理-任务上次执行的时间
     */
    private Long taskAppointFirstTime;
    private String taskAppointFirstTimeKey ="taskAppointFirstTimeKey";

    /**
     * 委托管理-任务上次执行的时间
     */
    private Long taskEntrustFirstTime;
    private String taskEntrustFirstTimeKey ="taskEntrustFirstTimeKey";


    @Autowired
    private RedisService redisServiceImpl;



    public Long getTaskEntrustFirstTime(){
        if (taskEntrustFirstTime == null){
            Object value = redisServiceImpl.getValue(taskEntrustFirstTimeKey);
            if (value == null){
                long intTime = System.currentTimeMillis();;
                redisServiceImpl.setValue(taskEntrustFirstTimeKey,intTime );
                taskEntrustFirstTime = intTime;
            }else{
                taskEntrustFirstTime =Long.valueOf(String.valueOf(value));
            }
        }
        return taskEntrustFirstTime;
    }


    public void setTaskEntrustFirstTime(Long taskEntrustFirstTime) {
        this.taskEntrustFirstTime = taskEntrustFirstTime;
        redisServiceImpl.setValue(taskEntrustFirstTimeKey, taskEntrustFirstTime);
    }




    public Long getTaskAppointFirstTime(){
        if (taskAppointFirstTime == null){
            Object value = redisServiceImpl.getValue(taskAppointFirstTimeKey);
            if (value == null){
                long intTime = System.currentTimeMillis();
                redisServiceImpl.setValue(taskAppointFirstTimeKey,intTime );
                taskAppointFirstTime = intTime;
            }else{
                taskAppointFirstTime =Long.valueOf(String.valueOf(value));
            }
        }
        return taskAppointFirstTime;
    }


    public void setTaskAppointFirstTime(Long taskAppointFirstTime) {
        this.taskAppointFirstTime = taskAppointFirstTime;
        redisServiceImpl.setValue(taskAppointFirstTimeKey, taskAppointFirstTime);
    }



    public Double getTaskInterval(){
        if (taskInterval == null){
            Object value = redisServiceImpl.getValue(taskIntervalKey);
            if (value == null){
                redisServiceImpl.setValue(taskIntervalKey,initTime);
                taskInterval = initTaskInterval;;
            }else{
                taskInterval =Double.valueOf(String.valueOf(value));
            }
        }
        return taskInterval;
    }


    public void setTaskInterval(Double taskInterval) {
        this.taskInterval = taskInterval;
        redisServiceImpl.setValue(taskIntervalKey, taskInterval);
    }








    public Double getAppointmentWarn(){
        if (appointmentWarn == null){
           Object value = redisServiceImpl.getValue(appointmentWarnKey);
           if (value == null){
               redisServiceImpl.setValue(appointmentWarnKey,initTime);;
               appointmentWarn = initTime;
           }else{
               appointmentWarn =Double.valueOf(String.valueOf(value));
           }
        }
        return appointmentWarn;
    }


    public void setAppointmentWarn(Double appointmentWarn) {
        this.appointmentWarn = appointmentWarn;
        redisServiceImpl.setValue(appointmentWarnKey, appointmentWarn);
    }


    public Double getAppointClose(){
        if (appointClose == null){
            Object value = redisServiceImpl.getValue(appointCloseKey);;
            if (value == null){
                redisServiceImpl.setValue(appointCloseKey,initTime);
                appointClose = initTime;
            }else{
                appointClose =Double.valueOf(String.valueOf(value));
            }
        }
        return appointClose;
    }


    public void setAppointClose(Double appointClose) {
        this.appointClose = appointClose;
        redisServiceImpl.setValue(appointCloseKey, appointClose);
    }

}
