package com.jsp.hospital.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

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

@WebServlet("/addPatient")
@MultipartConfig
public class AddPatientController extends HttpServlet{
	
	private PatientDao dao;
	private PatientDto dto;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.dao=new PatientDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked AddPatientController doPost");
		String name=req.getParameter("name");
		String gender=req.getParameter("gender");
		Long phone=Long.parseLong(req.getParameter("phone"));
		Date dob=Date.valueOf(req.getParameter("dob"));
		int age=Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
		
		byte[] img=null;
		Part file= req.getPart("img");
		if(file!=null) {
			InputStream inputStream=file.getInputStream();
			img=new byte[inputStream.available()];
			inputStream.read(img);
			
			/*byte[] img= new byte[file.getInputStream().available()];
			file.getInputStream().read();*/
		}
		
		this.dto=new PatientDto(name, phone, age, gender, dob);
		this.dto.setPicture(img);
		
		this.dao.savePatientData(this.dto);
		resp.getWriter().print("<h1>Patient data saved</h1>");
		req.getRequestDispatcher("staffHome.html").include(req, resp);

	}

}
