package com.jsp.hospital.dao;

import java.util.List;

import com.jsp.hospital.dto.PatientDto;

public interface PatientDao {
	
	public void savePatientData(PatientDto dto);
	
	public List<PatientDto> fetchAllPatient();
	
	public void updatePatient(PatientDto dto);
	
	public PatientDto fetchPatientById(int id);

}
