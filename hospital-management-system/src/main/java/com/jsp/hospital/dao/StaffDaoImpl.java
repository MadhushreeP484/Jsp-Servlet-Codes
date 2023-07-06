package com.jsp.hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jsp.hospital.dto.StaffDto;

public class StaffDaoImpl implements StaffDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public StaffDaoImpl() {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.transaction = entityManager.getTransaction();
	}

	@Override
	public void saveStaffData(StaffDto staffDto) {
		System.out.println("Invoked saveStaffData");
		this.transaction.begin();
		this.entityManager.persist(staffDto);
		this.transaction.commit();
		System.out.println("Staff data saved");
	}

	@Override
	public int fetchStaffId(String email) {
		System.out.println("Invoked fetchStaffId");
		List<StaffDto> listFromDB = this.entityManager.createQuery("select data from StaffDto data where data.email=?1")
				.setParameter(1, email).getResultList();
		if (!listFromDB.isEmpty()) {
			StaffDto dto = listFromDB.get(0);
			return dto.getSId();
		} else {
			return 0;
		}
	}

	@Override
	public StaffDto fetchStaffDataByPhone(Long phone) {
		System.out.println("Invoked fetchStaffId");
		List<StaffDto> list = this.entityManager.createQuery("select data from StaffDto data where data.phone=?1")
				.setParameter(1, phone).getResultList();
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public StaffDto fetchStaffDataById(int id) {
		System.out.println("Invoked fetchStaffDataById");
		StaffDto dto = this.entityManager.find(StaffDto.class, id);
		return dto;
	}

	@Override
	public void updateStaffPassword(StaffDto dto) {
		System.out.println("Invoked updateStaffPasswordById");
		this.transaction.begin();
		this.entityManager.merge(dto);
		this.transaction.commit();
	}

	@Override
	public List<StaffDto> fetchAllStaff() {
		System.out.println("Invoked fetchAllStaff");
		List<StaffDto> list = this.entityManager.createQuery("select data from StaffDto data").getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public void updateStaffStatus(int id) {
		System.out.println("Invoked updateStaffStatus");
		StaffDto dtoFromDB = this.entityManager.find(StaffDto.class, id);
		if (dtoFromDB.isStatus()) {
			dtoFromDB.setStatus(false);
		} else {
			dtoFromDB.setStatus(true);
		}
		updateStaffPassword(dtoFromDB);
	}

}
