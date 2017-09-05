package com.midland.web.dao;

import com.midland.web.model.Appointment;
import java.util.List;

public interface AppointmentMapper {

	Appointment selectAppointmentById(Integer appointment);

	int deleteAppointmentById(Integer appointment);

	int updateAppointmentById(Appointment appointment);

	int insertAppointment(Appointment appointment);

	List<Appointment> findAppointmentList(Appointment appointment);

}
