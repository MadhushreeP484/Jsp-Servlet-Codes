package com.jsp.hospital.controller.admin;

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

@WebServlet("/viewPatientForAdmin")
public class FetchAllPatientForAdmin extends HttpServlet{
	
	private PatientDao patientDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.patientDao= new PatientDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked FetchAllPatientForAdmin doGet");
		if (req.getSession().getAttribute("admin") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
		List<PatientDto> patientDtos=this.patientDao.fetchAllPatient();
		if(patientDtos.isEmpty()) {
			resp.getWriter().print("<h1>No Patient Data Found</h1>");
			req.getRequestDispatcher("adminHome.jsp").include(req, resp);
		}else {
			req.setAttribute("list", patientDtos);
			req.getRequestDispatcher("fetchAllPatientForAdmin.jsp").include(req, resp);
		}
		}
	}

}
