package com.jsp.hospital.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
public class DoctorDto {
	@Id
	@SequenceGenerator(initialValue = 1001, allocationSize = 1, sequenceName = "doctorId", name = "doctorId")
	@GeneratedValue(generator = "doctorId")
	private int dId;
	private String name;
	private long phone;
	private String email;
	private Date dob;
	private String qualification;
	private String Specialisation;
	private String password;
	private String gender;
	private int age;
	private boolean availability;
	private boolean status;
	
	@OneToMany
	private List<AppointmentDto> appointmentDtos;
	
	public DoctorDto(String name, long phone, String email, Date dob, String qualification, String specialisation,
			String password, String gender, int age) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.dob = dob;
		this.qualification = qualification;
		Specialisation = specialisation;
		this.password = password;
		this.gender = gender;
		this.age = age;
	}
	
	

}
