package com.jsp.hospital.controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dao.PatientDao;
import com.jsp.hospital.dao.PatientDaoImpl;
import com.jsp.hospital.dto.DoctorDto;
import com.jsp.hospital.dto.PatientDto;
import com.jsp.hospital.dto.StaffDto;

@WebServlet("/bookPatientById")
public class BookPatientByIdController extends HttpServlet {

	private PatientDao patientDao;
	private DoctorDao doctorDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.patientDao = new PatientDaoImpl();
		this.doctorDao= new DoctorDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked BookPatientByIdController service");
		if (req.getSession().getAttribute("staff") == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			int id = Integer.parseInt(req.getParameter("pId"));
			PatientDto dto = this.patientDao.fetchPatientById(id);
			if (dto == null) {
				resp.getWriter().print("<h1 style='color:red'>No Patient Data Found for " + id + "</h1>");
				req.getRequestDispatcher("bookAppointmentHome.jsp").include(req, resp);
			} else {
				List<DoctorDto> doctorDtos=this.doctorDao.fetchAvailableDoctors();
				if (doctorDtos.isEmpty()) {
					resp.getWriter().print("<h1 style='color:red'>No Doctors are available </h1>");
					req.getRequestDispatcher("bookAppointmentHome.jsp").include(req, resp);
				}else {
				req.setAttribute("doctorDtos", doctorDtos);
				req.setAttribute("patientDto", dto);
				req.getRequestDispatcher("bookAppointment.jsp").include(req, resp);
				}
			}
		}
	}

}
