package com.jsp.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dao.StaffDao;
import com.jsp.hospital.dao.StaffDaoImpl;
import com.jsp.hospital.dto.DoctorDto;
import com.jsp.hospital.dto.StaffDto;

@WebServlet("/loginAction")
public class LoginController extends HttpServlet {

	private DoctorDao doctorDao;
	private StaffDao staffDao;

	@Override
	public void init() throws ServletException {
		System.out.println("Invoked " + this.getClass().getSimpleName());
		this.doctorDao = new DoctorDaoImpl();
		this.staffDao = new StaffDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked LoginController doPost");
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");

		if (id == 99999999) {
			if(password.equals("admin")) {
				resp.getWriter().print("<h1 style='color:green'>Login is Success</h1>");
				req.getRequestDispatcher("adminHome.jsp").include(req, resp);
				System.out.println("Login is Success");
			}else {
				resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}	
			
		} else if (id < 121000) {
			DoctorDto dto = this.doctorDao.fetchDoctorDataById(id);
			if (dto!=null) {
				if(dto.getPassword().equals(password)) {
					if(dto.isStatus()) {
						resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
						req.getRequestDispatcher("doctorHome.html").forward(req, resp);
					}else {
						resp.getWriter().print("<h1 style='color:blue'>Account is not approved, Contact Admin</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}
				}else {
					resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
					req.getRequestDispatcher("login.html").include(req, resp);
				}
			}else {
				resp.getWriter().print("<h1 style='color:red'>Incorrect Id</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}		
			
		} else if(id>=121000){
			StaffDto dto = this.staffDao.fetchStaffDataById(id);
			if (dto.getPassword().equals(password))
				if(dto.isStatus()) {
					resp.getWriter().print("<h1 style='color:green'>Login Success</h1>");
					req.getRequestDispatcher("staffHome.html").forward(req, resp);
				}else {
					resp.getWriter().print("<h1 style='color:blue'>Account is not approved, Contact Admin</h1>");
					req.getRequestDispatcher("login.html").include(req, resp);
				}			
			else {
				resp.getWriter().print("<h1 style='color:red'>Incorrect Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}else {
			resp.getWriter().print("<h1 style='color:red'>Incorrect Id</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}

}
