package com.midland.web.dao;

import com.midland.web.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppointmentMapper {

	Appointment selectAppointmentById(Integer appointment);

	int deleteAppointmentById(Integer appointment);

	int updateAppointmentById(Appointment appointment);

	int insertAppointment(Appointment appointment);

	List<Appointment> findAppointmentList(Appointment appointment);

}
