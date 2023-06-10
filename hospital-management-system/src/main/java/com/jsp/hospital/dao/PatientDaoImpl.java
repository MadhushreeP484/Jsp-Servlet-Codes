package com.jsp.hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jsp.hospital.dto.PatientDto;

public class PatientDaoImpl implements PatientDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public PatientDaoImpl() {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.transaction = this.entityManager.getTransaction();
	}

	@Override
	public void savePatientData(PatientDto dto) {
		System.out.println("Invoked savePatientData");
		this.transaction.begin();
		this.entityManager.persist(dto);
		this.transaction.commit();
	}

	@Override
	public List<PatientDto> fetchAllPatient() {
		System.out.println("Invoked fetchAllPatient");
		List<PatientDto> list = this.entityManager.createQuery("select data from PatientDto data").getResultList();
		if (list.isEmpty())
			return null;
		else
			return list;
	}

	@Override
	public void updatePatient(PatientDto dto) {
		System.out.println("Invoked updatePatient");
		this.transaction.begin();
		this.entityManager.merge(dto);
		this.transaction.commit();
	}
}
