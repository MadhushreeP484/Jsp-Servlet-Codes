package com.jsp.hospital.dto;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
public class StaffDto {
	@Id
	@GeneratedValue(generator = "staffId")
	@SequenceGenerator(initialValue = 121001, allocationSize = 1,sequenceName = "staffId", name="staffId")
	private int sId;
	private String name;
	private long phone;
	private String email;
	private String password;
	private Date dob;
	private String gender;
	private int age;
	private boolean status;
	
	public StaffDto(String name, long phone, String email, String password, Date dob, String gender, int age) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.age = age;
		
	}
	
	
	
}
