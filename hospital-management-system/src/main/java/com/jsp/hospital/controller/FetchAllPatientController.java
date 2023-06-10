package com.jsp.hospital.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.PatientDao;
import com.jsp.hospital.dao.PatientDaoImpl;
import com.jsp.hospital.dto.PatientDto;

@WebServlet("/editPatient")
public class FetchAllPatientController extends HttpServlet {
	
	private PatientDao patientDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.patientDao= new PatientDaoImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked FetchAllPatientController service");
		
		List<PatientDto> list=this.patientDao.fetchAllPatient();
		req.setAttribute("list", list);
		req.getRequestDispatcher("editPatient.jsp").include(req, resp);
	}

}
