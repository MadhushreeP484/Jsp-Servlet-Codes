package com.jsp.hospital.controller.doctor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.AppointmentDao;
import com.jsp.hospital.dao.AppointmentDaoImpl;
import com.jsp.hospital.dto.AppointmentDto;
import com.jsp.hospital.dto.DoctorDto;

@WebServlet("/checkup")
public class Checkup extends HttpServlet{
	
	private AppointmentDao appointmentDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.appointmentDao= new AppointmentDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked Checkup doGet");
		DoctorDto doctorDto=(DoctorDto)req.getSession().getAttribute("doctor");
		if(doctorDto==null) {
			resp.getWriter().print("<h1>Session Expired, Login again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else {
			int id=Integer.parseInt(req.getParameter("id"));
			List<AppointmentDto> list=doctorDto.getAppointmentDtos();
			AppointmentDto appointmentDto=list.get(id);
			if(appointmentDto==null) {
				resp.getWriter().print("<h1>No Appointments made</h1>");
				req.getRequestDispatcher("doctorHome.html").include(req, resp);
			}else {
				appointmentDto.setDateTime(LocalDateTime.now());
				this.appointmentDao.updateAppointment(appointmentDto);
				req.setAttribute("list", list);
				req.getRequestDispatcher("upcomingAppointments.jsp").include(req, resp);
			}
		}
	}

}
