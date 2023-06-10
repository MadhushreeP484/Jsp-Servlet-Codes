package com.jsp.hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jsp.hospital.dto.DoctorDto;

public class DoctorDaoImpl implements DoctorDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public DoctorDaoImpl() {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.transaction = entityManager.getTransaction();
	}

	@Override
	public void saveDoctorData(DoctorDto doctorDto) {
		System.out.println("Invoked saveDoctorData");
		this.transaction.begin();
		this.entityManager.persist(doctorDto);
		this.transaction.commit();
		System.out.println("Doctor data saved");
	}

	@Override
	public int fetchDoctorId(String email) {
		System.out.println("Invoked fetchStaffId");
		List<DoctorDto> listFromDB = this.entityManager
				.createQuery("select data from DoctorDto data where data.email=?1").setParameter(1, email)
				.getResultList();
		if (!listFromDB.isEmpty()) {
			DoctorDto dto = listFromDB.get(0);
			return dto.getDId();
		} else {
			return 0;
		}
	}

	@Override
	public DoctorDto fetchDoctorDataByPhone(Long phone) {
		System.out.println("Invoked fetchStaffId");
		List<DoctorDto> list = this.entityManager.createQuery("select data from DoctorDto data where data.phone=?1")
				.setParameter(1, phone).getResultList();
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public DoctorDto fetchDoctorDataById(int id) {
		System.out.println("Invoked fetchDoctorDataById");
		DoctorDto dto = this.entityManager.find(DoctorDto.class, id);
		return dto;
	}

	@Override
	public void updateDoctorPassword(DoctorDto dto) {
		System.out.println("Invoked updateDoctorPasswordById");
		this.transaction.begin();
		this.entityManager.merge(dto);
		this.transaction.commit();
	}

	@Override
	public List<DoctorDto> fetchAllDoctor() {
		System.out.println("Invoked fetchAllDoctor");
		List<DoctorDto> list = this.entityManager.createQuery("select data from DoctorDto data").getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}

	}

	@Override
	public void updateDoctorStatus(int id) {
		System.out.println("Invoked updateDoctorStatus");
		DoctorDto dtoFromDB = this.entityManager.find(DoctorDto.class, id);
		if (dtoFromDB != null) {
			if (dtoFromDB.isStatus()) {
				dtoFromDB.setStatus(false);
			} else {
				dtoFromDB.setStatus(true);
			}
			updateDoctorPassword(dtoFromDB);
		} else {
			System.out.println("Id doesn't Exists");
		}
	}

}
