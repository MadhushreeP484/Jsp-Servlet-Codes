package com.jsp.hospital.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dto.DoctorDto;

@WebServlet("/fetchDoctor")
public class FetchAllDoctorController extends HttpServlet{
	
	private DoctorDao doctorDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.doctorDao= new DoctorDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked ApproveDoctorController doGet");
		if(req.getSession().getAttribute("admin")!=null) {
		List<DoctorDto> list=this.doctorDao.fetchAllDoctor();
		if(list!=null) {
		req.setAttribute("list", list);
		req.getRequestDispatcher("approveDoctor.jsp").include(req, resp);
		}else {
			resp.getWriter().print("There are no Doctors");
			req.getRequestDispatcher("adminHome.jsp").include(req, resp);
		}
		}else {
			resp.getWriter().print("<h1>Session Expired, Login Again");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}

}
