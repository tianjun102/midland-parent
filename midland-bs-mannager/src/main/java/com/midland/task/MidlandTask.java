package com.midland.task;

import com.midland.web.model.Appointment;
import com.midland.web.model.Entrust;
import com.midland.web.service.AppointmentService;
import com.midland.web.service.EntrustService;
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
	private EntrustService entrustServiceImpl;
	
	//委托看房，经纪人重新分配规则，24小时内状态没有修改，发送短信给经纪人及其领导，48小时后没有处理，则关闭此预约，并发送给有指定邮箱
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
	
	//委托记录可重新分配经纪人，建议设置提醒功能，若指定时间内已分配的业务员未跟进（还未从已分配状态变为看房中），后台可重新分配。
	@Scheduled(fixedRate = 10000)
	public void entrustReset(){
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
				
				calendar.add(calendar.HOUR, -24);
				Date time_24 = calendar.getTime();
				if (time_24.getTime() > assignTime.getTime()) {
					
					Entrust entrust = new Entrust();
					entrust.setResetFlag(1);//标记为展示
					entrust.setId(entrust1.getId());
					entrust.setEntrustTime(null);
					entrustServiceImpl.updateEntrustById(entrust);
						
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedRate = 10000)
	public void appointReset(){
		try {
			Appointment temp = new Appointment();
			temp.setStatus(0);//
			temp.setResetFlag(0);//
			List<Appointment> lists = appointmentServiceImpl.findAppointmentList(temp);
			for (Appointment appointment1 : lists) {
				String time = appointment1.getAssignedTime();
				Date assignTime = MidlandHelper.stringToDate(time);
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				calendar.add(calendar.HOUR, -24);
				Date time_24 = calendar.getTime();
				if (time_24.getTime() > assignTime.getTime()) {
					
					Appointment app = new Appointment();
					app.setResetFlag(1);//标记为展示
					app.setId(appointment1.getId());
					app.setEntrustTime(null);
					appointmentServiceImpl.updateAppointmentById(app);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
