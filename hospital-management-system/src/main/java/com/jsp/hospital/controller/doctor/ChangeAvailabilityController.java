package com.jsp.hospital.controller.doctor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dto.DoctorDto;

@WebServlet("/changeAvailability")
public class ChangeAvailabilityController extends HttpServlet{
	
	private DoctorDao doctorDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.doctorDao= new DoctorDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DoctorDto dto=(DoctorDto)req.getSession().getAttribute("doctor");
		if(dto==null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}else {
			if(dto.isAvailability()) {
				dto.setAvailability(false);
				this.doctorDao.updateDoctorPassword(dto);
				req.getSession().setAttribute("doctor", dto);
				resp.getWriter().print("<h1 style='color:red'>Changed to Not Available</h1>");
				req.getRequestDispatcher("doctorHome.html").include(req, resp);
			}else {
				dto.setAvailability(true);
				this.doctorDao.updateDoctorPassword(dto);
				req.getSession().setAttribute("doctor", dto);
				resp.getWriter().print("<h1 style='color:green'>Changed to Available</h1>");
				req.getRequestDispatcher("doctorHome.html").include(req, resp);
			}
		}
	}

}
