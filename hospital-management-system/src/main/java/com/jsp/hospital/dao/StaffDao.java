package com.jsp.hospital.dao;

import java.util.List;

import com.jsp.hospital.dto.DoctorDto;
import com.jsp.hospital.dto.StaffDto;

public interface StaffDao {
	
	public void saveStaffData(StaffDto staffDto);

	public int fetchStaffId(String email);

	public StaffDto fetchStaffDataByPhone(Long phone);

	public StaffDto fetchStaffDataById(int id);
	
	public void updateStaffPassword(StaffDto dto);
	
	public List<StaffDto> fetchAllStaff();
	
	public void updateStaffStatus(int id);
}
