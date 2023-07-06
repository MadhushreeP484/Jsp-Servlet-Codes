package com.jsp.hospital.controller.doctor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dto.AppointmentDto;
import com.jsp.hospital.dto.DoctorDto;

@WebServlet("/viewAllAppointmentsDoc")
public class ViewAllAppointmentsDoc extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked ViewAllAppointmentsDoc doGet");
		DoctorDto doctorDto=(DoctorDto)req.getSession().getAttribute("doctor");
		if(doctorDto==null) {
			resp.getWriter().print("<h1>Session Expired, Login again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else {
			List<AppointmentDto> list=doctorDto.getAppointmentDtos();
			if(list.isEmpty()) {
				resp.getWriter().print("<h1>No Appointments made</h1>");
				req.getRequestDispatcher("doctorHome.html").include(req, resp);
			}else {
				req.setAttribute("list", list);
				req.getRequestDispatcher("viewAllAppointmentsDoc.jsp").include(req, resp);
			}
		}
	}

}
