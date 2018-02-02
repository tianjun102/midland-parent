package com.midland.task;

import com.alibaba.fastjson.JSONArray;
import com.midland.config.MidlandConfig;
import com.midland.core.util.HttpUtils;
import com.midland.web.Contants.Contant;
import com.midland.web.api.ApiHelper;
import com.midland.web.api.mailSender.MailProperties;
import com.midland.web.model.Appointment;
import com.midland.web.model.Entrust;
import com.midland.web.model.remote.Agent;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.EntrustService;
import com.midland.web.util.MidlandHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 'ms.x' on 2017/9/19.
 */
@Component
@EnableScheduling
public class MidlandTask {

    @Autowired
    private AppointmentService appointmentServiceImpl;
    @Autowired
    private EntrustService entrustServiceImpl;
    @Autowired
    private TaskConfig taskConfig;
    @Autowired
    private ApiHelper apiHelper;
    @Autowired
    private MailProperties mailProperties;

    private Logger logger = LoggerFactory.getLogger(MidlandTask.class);

    @Autowired
    private MidlandConfig midlandConfig;


    //预约看房，经纪人重新分配规则，24小时内状态没有修改，发送短信给经纪人及其领导，48小时后没有处理，则关闭此预约，并发送给有指定邮箱
    @Scheduled(fixedRate = 1000)
    public void scanAppointment() {
        long timeTemp = System.currentTimeMillis();
        //System.out.println(timeTemp-taskConfig.getTaskAppointFirstTime());
        if (timeTemp - taskConfig.getTaskAppointFirstTime() > taskConfig.getTaskInterval() * 3600000) {
            taskConfig.setTaskAppointFirstTime(timeTemp);
            try {
                Appointment temp = new Appointment();
                temp.setStatus(0);//0说明预约未处理
                List<Appointment> lists = appointmentServiceImpl.findAppointmentList(temp);
                for (Appointment appointment1 : lists) {
                    String time = appointment1.getAppointmentTime();
                    Date appointTime = MidlandHelper.stringToDate(time);
                    Date date = new Date();
                    //后退48小时；current-48;
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.SECOND, Double.valueOf(-taskConfig.getAppointClose() * 3600).intValue());
                    Date time_48 = calendar.getTime();

                    //后退24小时；current-24;
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(date);
                    calendar1.add(calendar.SECOND, Double.valueOf(-taskConfig.getAppointmentWarn() * 3600).intValue());
                    Date time_24 = calendar1.getTime();
                    long ti = appointTime.getTime() - time_48.getTime();
                    long te = appointTime.getTime() - time_24.getTime();
                    //System.out.println("关闭"+ti);
                    //System.out.println("告警"+te);
                    if (ti < 0) {
                        //超过48小时,48小时后没有处理，则关闭此预约，并发送给有指定邮箱
                        Appointment appoint = new Appointment();
                        appoint.setStatus(3);//关闭预约
                        appoint.setResetFlag(0);
                        appoint.setId(appointment1.getId());
                        appoint.setHandleTime(MidlandHelper.getCurrentTime());
                        appointmentServiceImpl.updateAppointmentById(appoint);

                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setFrom(mailProperties.getUserName());
                        message.setTo("dpall@midland.com.cn");
                        message.setSubject("主题：预约关闭");
                        message.setText("您的预约超时未处理，现已关闭，预约编号：" + appointment1.getAppointSn());
                        apiHelper.emailSender("scanAppointment", message);
                        logger.debug("发送给有指定邮箱，关闭,{}", message.toString());
                    } else if (te < 0) {
                        //超过24小时,24小时内状态没有修改，发送短信给经纪人及其领导，
                        if (appointment1.getFlag() == 0) {
                            Appointment appoint = new Appointment();
                            appoint.setFlag(1);//标记为已通知，不需要再次通知
                            appoint.setResetFlag(1);//标记为展示
                            appoint.setId(appointment1.getId());
                            appointmentServiceImpl.updateAppointmentById(appoint);

                            Map map1 = new HashMap();
                            map1.put("pageSize", "1");
                            map1.put("pageNo", "1");
                            String data = HttpUtils.get(midlandConfig.getAgentPage(), map1);
                            List<Agent> result = MidlandHelper.getAgentPojoList(data, Agent.class);
                            result.forEach(a -> {
                                List list = new ArrayList();
                                list.add(a.getLeaderName());
                                list.add(appointment1.getAgentName());
                                if (StringUtils.isEmpty(appointment1.getAgentName()))
                                    apiHelper.smsSender(a.getPhone(), Contant.SMS_TEMPLATE_63647, list);
                                logger.debug("超过24小时,24小时内状态没有修改，发送短信给经纪人及其领导,{}", JSONArray.toJSONString(list));
                            });


                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //委托记录可重新分配经纪人，建议设置提醒功能，若指定时间内已分配的业务员未跟进（还未从已分配状态变为看房中），后台可重新分配。
    @Scheduled(fixedRate = 1000)
    public void entrustReset() {
        long timeTemp = System.currentTimeMillis();
        if (timeTemp - taskConfig.getTaskEntrustFirstTime() > taskConfig.getTaskInterval() * 3600000) {
            taskConfig.setTaskEntrustFirstTime(timeTemp);
            try {
                Entrust temp = new Entrust();
                temp.setStatus(1);//1看房中，
                temp.setResetFlag(0);//1看房中，且是还没展示“重新分配”按钮的数据
                List<Entrust> lists = entrustServiceImpl.findEntrustList(temp);
                for (Entrust entrust1 : lists) {
                    String time = entrust1.getAssignedTime();
                    Date assignTime = MidlandHelper.stringToDate(time);
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);

                    calendar.add(calendar.SECOND, Double.valueOf(-taskConfig.getEntrustWarn() * 3600).intValue());
                    Date time_24 = calendar.getTime();
                    if (time_24.getTime() > assignTime.getTime()) {

                        Entrust entrust = new Entrust();
                        entrust.setResetFlag(1);//标记为展示
                        entrust.setId(entrust1.getId());
                        entrust.setEntrustTime(null);
                        entrustServiceImpl.updateEntrustById(entrust);
                        logger.debug("若指定时间内已分配的业务员未跟进（还未从已分配状态变为看房中），后台可重新分配,{}", JSONArray.toJSONString(entrust1));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//	@Scheduled(fixedRate = 3600000)
//	public void appointReset(){
//		try {
//			Appointment temp = new Appointment();
//			temp.setStatus(0);//
//			temp.setResetFlag(0);//
//			List<Appointment> lists = appointmentServiceImpl.findAppointmentList(temp);
//			for (Appointment appointment1 : lists) {
//				String time = appointment1.getAssignedTime();
//				Date assignTime = MidlandHelper.stringToDate(time);
//				Date date = new Date();
//				Calendar calendar = Calendar.getInstance();
//				calendar.setTime(date);
//
//				calendar.add(calendar.HOUR, -24);
//				Date time_24 = calendar.getTime();
//				if (time_24.getTime() > assignTime.getTime()) {
//
//					Appointment app = new Appointment();
//					app.setResetFlag(1);//标记为展示
//					app.setId(appointment1.getId());
//					app.setEntrustTime(null);
//					appointmentServiceImpl.updateAppointmentById(app);
//
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


}
