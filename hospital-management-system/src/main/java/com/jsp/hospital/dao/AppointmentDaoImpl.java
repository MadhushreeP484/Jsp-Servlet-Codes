package com.jsp.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jsp.hospital.dto.AppointmentDto;
import com.jsp.hospital.dto.DoctorDto;
import com.jsp.hospital.dto.PatientDto;

public class AppointmentDaoImpl implements AppointmentDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public AppointmentDaoImpl() {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
	}

	@Override
	public void saveAppointment(AppointmentDto dto) {
		System.out.println("Invoked saveAppointment");
		this.transaction.begin();
		this.entityManager.persist(dto);
		this.transaction.commit();

	}

	@Override
	public List<AppointmentDto> fetchAppointmentDetails() {
		System.out.println("Invoked fetchAppointmentDetails");
		List<AppointmentDto> list = this.entityManager.createQuery("select data from AppointmentDto data").getResultList();
		if (list == null)
			return null;
		else
			return list;
	}

	@Override
	public List<AppointmentDto> fetchAppointmentsByPatientId(int id) {
		System.out.println("Invoked fetchAppointmentsByPatientId");
		List<AppointmentDto> dtos= new ArrayList<AppointmentDto>();
		PatientDto patientDto= new PatientDto();
		List<AppointmentDto> list = fetchAppointmentDetails();
		if (list == null)
			return null;
		else
			for (AppointmentDto aDto : list) {
				patientDto=aDto.getPatientDto();
				if(patientDto.getId()==id) {
					dtos.add(aDto);
				}
			}
			return dtos;
	}

	@Override
	public void updateAppointment(AppointmentDto dto) {
		System.out.println("updateAppointment");
		this.transaction.begin();
		this.entityManager.merge(dto);
		this.transaction.commit();	
	}

}
