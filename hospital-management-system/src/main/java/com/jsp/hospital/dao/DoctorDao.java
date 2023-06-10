package com.jsp.hospital.dao;

import java.util.List;

import com.jsp.hospital.dto.DoctorDto;

public interface DoctorDao {
	
	public void saveDoctorData(DoctorDto doctorDto);

	public int fetchDoctorId(String email);

	public DoctorDto fetchDoctorDataByPhone(Long phone);

	public DoctorDto fetchDoctorDataById(int id);
	
	public void updateDoctorPassword(DoctorDto dto);
	
	public List<DoctorDto> fetchAllDoctor();
	
	public void updateDoctorStatus(int id);

}
