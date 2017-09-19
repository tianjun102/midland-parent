package com.midland.task;

import com.midland.config.MidlandConfig;
import com.midland.web.model.Appointment;
import com.midland.web.service.AppointmentService;
import com.midland.web.util.MidlandHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 'ms.x' on 2017/9/19.
 */
@Component
@EnableScheduling
public class MidlandTask {
	
	@Autowired
	private AppointmentService appointmentServiceImpl;
	
	@Autowired
	private MidlandConfig midlandConfig;
	
	//经纪人重新分配规则，24小时内状态没有修改，发送短信给经纪人及其领导，48小时后没有处理，则关闭此预约，并发送给有指定邮箱
	@Scheduled(fixedRate = 3600000)
	public void scanAppointment() {
		try {
			Appointment temp = new Appointment();
			temp.setStatus(0);//0说明预约未处理
			List<Appointment> lists = appointmentServiceImpl.findAppointmentList(temp);
			for (Appointment appointment1 : lists) {
				String time = appointment1.getAppointmentTime();
				Date appointTime = MidlandHelper.stringToDate(time);
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				//后退48小时；current-48;
				calendar.add(Calendar.HOUR, -48);
				Date time_48 = calendar.getTime();
				//后退24小时；current-48+24;
				calendar.add(calendar.HOUR, 24);
				Date time_24 = calendar.getTime();
				if (time_48.getTime() > appointTime.getTime()) {
					//超过48小时,48小时后没有处理，则关闭此预约，并发送给有指定邮箱
					Appointment appoint = new Appointment();
					appoint.setStatus(3);
					appoint.setId(appointment1.getId());
					appointmentServiceImpl.updateAppointmentById(appoint);
					// TODO: 2017/9/19  发送给有指定邮箱
					System.out.println(222);
				} else if (time_24.getTime() > appointTime.getTime()) {
					//超过24小时,24小时内状态没有修改，发送短信给经纪人及其领导，
					if (appointment1.getFlag() == 0) {
						Appointment appoint = new Appointment();
						appoint.setFlag(1);//标记为已通知，不需要再次通知
						appoint.setId(appointment1.getId());
						appointmentServiceImpl.updateAppointmentById(appoint);
						// TODO: 2017/9/19  发送短信给经纪人及其领导，
						System.out.println(111);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
