package com.jsp.hospital.controller.staff;

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

@WebServlet("/editPatientDetails")
@MultipartConfig
public class EditPatientController extends HttpServlet {

	private PatientDao patientDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.patientDao = new PatientDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked EditPatientController service");
		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			String name=req.getParameter("name");
			Long phone = Long.parseLong(req.getParameter("phone"));
			Date dob = Date.valueOf(req.getParameter("dob"));
			int age = Period.between(dob.toLocalDate(), LocalDate.now()).getYears();

			PatientDto patientDto= this.patientDao.fetchPatientById(id);
			patientDto.setId(id);
			patientDto.setName(name);
			patientDto.setPhone(phone);
			patientDto.setAge(age);
			patientDto.setDob(dob);
			this.patientDao.updatePatient(patientDto);

			req.setAttribute("patientDto", this.patientDao.fetchPatientById(id));
			req.getRequestDispatcher("editPatient.jsp").include(req, resp);
		}
	}
}
