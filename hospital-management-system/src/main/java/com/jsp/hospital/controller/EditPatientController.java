package com.jsp.hospital.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jsp.hospital.dao.PatientDao;
import com.jsp.hospital.dao.PatientDaoImpl;
import com.jsp.hospital.dto.PatientDto;

@WebServlet("/editPatientDetails")
@MultipartConfig
public class EditPatientController extends HttpServlet{
	
	private PatientDao patientDao;
	private PatientDto patientDto;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.patientDao= new PatientDaoImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked EditPatientController service");
		int id= Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String gender=req.getParameter("gender");
		Long phone=Long.parseLong(req.getParameter("phone"));
		Date dob=Date.valueOf(req.getParameter("dob"));
		int age=Integer.parseInt("age");
		
		this.patientDto=new PatientDto(name, phone, age, gender, dob);
		
		this.patientDao.updatePatient(patientDto);
		
		req.setAttribute("list", this.patientDao.fetchAllPatient());
		req.getRequestDispatcher("editPatient.jsp").include(req, resp);
	}

}
