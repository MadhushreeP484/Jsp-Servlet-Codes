package com.jsp.hospital.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dao.StaffDao;
import com.jsp.hospital.dao.StaffDaoImpl;
import com.jsp.hospital.dto.StaffDto;

@WebServlet("/staffSaveAction")
public class StaffSignupController extends HttpServlet{
	
	private StaffDao staffDao;
	private DoctorDao doctorDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked StaffSignupController init");
		this.staffDao= new StaffDaoImpl();
		this.doctorDao= new DoctorDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked StaffSignupController doPost");
		String name= req.getParameter("name");
		String email=req.getParameter("email");
		long phone=Long.parseLong(req.getParameter("phone"));
		Date date=Date.valueOf(req.getParameter("dob"));
		String gender=req.getParameter("gender");
		String password=req.getParameter("password");
		String cPassword=req.getParameter("cPassword");
		
		int age=Period.between(date.toLocalDate(), LocalDate.now()).getYears();
		StaffDto staffDto=new StaffDto(name, phone, email, password, date, gender, age);
		if(password.equals(cPassword)) {
			if(age>18) {
				if(this.staffDao.fetchStaffDataByPhone(phone)==null && this.staffDao.fetchStaffId(email)==0 && this.doctorDao.fetchDoctorDataByPhone(phone)==null && this.doctorDao.fetchDoctorId(email)==0) {
					this.staffDao.saveStaffData(staffDto);	
					try {
						resp.getWriter().print("<h1 style='color:green'>Account Registration is complete for the user "+name+", Wait for the Admin approval </h1>");
						resp.getWriter().print("<h1 style='color:blue'>Please find the Account Id: "+staffDto.getSId()+"</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}catch (Exception e) {
						resp.getWriter().print("<h1 style='color:red'>Registration failed</h1>");
						req.getRequestDispatcher("staffSignup.html").include(req, resp);
					}
				}else {
					resp.getWriter().print("<h1 style='color:red'>Registration failed since Email or Phone is already exist</h1>");
					req.getRequestDispatcher("staffSignup.html").include(req, resp);
				}	
			}else {
				resp.getWriter().print("<h1 style='color:red'>Age should be greater than 18</h1>");
				req.getRequestDispatcher("staffSignup.html").include(req, resp);
			}
		}else {
			resp.getWriter().print("<h1 style='color:red'>Enter Same Password</h1>");
			req.getRequestDispatcher("staffSignup.html").include(req, resp);
		}
	}

}
