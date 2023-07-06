package com.jsp.hospital.controller.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.PatientDao;
import com.jsp.hospital.dao.PatientDaoImpl;
import com.jsp.hospital.dto.PatientDto;

@WebServlet("/editPatientById")
public class EditPatientByIdController extends HttpServlet {

	private PatientDao patientDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.patientDao = new PatientDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked EditPatientByIdController doPost");
		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("id"));
			PatientDto patientDto = this.patientDao.fetchPatientById(id);
			if (patientDto == null) {
				resp.getWriter().print("<h1 style='color:red'>NO Data Found for Id</h1>");
				req.getRequestDispatcher("editPatientById.html").include(req, resp);
			} else {
				req.setAttribute("patientDto", patientDto);
				req.getRequestDispatcher("editPatient.jsp").include(req, resp);
			}
		}
	}
}
