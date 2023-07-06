package com.jsp.hospital.controller.staff;

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

@WebServlet("/fetchAllPatientForStaff")
public class FetchAllPatientForStaffController extends HttpServlet {

	private PatientDao patientDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.patientDao = new PatientDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked FetchAllPatientController doGet");

		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			List<PatientDto> list = this.patientDao.fetchAllPatient();
			if(list.isEmpty()) {
				resp.getWriter().print("<h1 style='color:red'>No Patient Data Found</h1>");
				req.getRequestDispatcher("bookAppointment.jsp").include(req, resp);
			}else {
				req.setAttribute("list", list);
				req.getRequestDispatcher("displayAllPatientBook.jsp").include(req, resp);
			}
		}
	}
}
