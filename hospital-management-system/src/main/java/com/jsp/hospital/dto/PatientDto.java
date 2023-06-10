package com.jsp.hospital.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
public class PatientDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long phone;
	private int age;
	private String gender;
	private Date dob;
	
	@Lob
	private byte[] picture;
	
	@OneToMany
	private List<AppointmentDto> appointments;
	
	public PatientDto(String name, long phone, int age, String gender, Date dob) {
		super();
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
	}
}
