package com.jsp.hospital.dao;

import java.util.List;

import com.jsp.hospital.dto.AppointmentDto;

public interface AppointmentDao {
	
	public void saveAppointment(AppointmentDto dto);

	public List<AppointmentDto> fetchAppointmentDetails();
	
	public List<AppointmentDto> fetchAppointmentsByPatientId(int id);
	
	public void updateAppointment(AppointmentDto dto);

}
