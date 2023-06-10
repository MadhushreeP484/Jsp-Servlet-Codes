package com.jsp.hospital.controller;

import java.io.IOException;
import java.sql.Date;

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

@WebServlet("/updatePasswordAction")
public class ForgotPasswordController extends HttpServlet{
	private DoctorDao doctorDao;
	private StaffDao staffDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.doctorDao= new DoctorDaoImpl();
		this.staffDao= new StaffDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked ForgotPasswordController doPost");
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String emailOrPhone=req.getParameter("email/phone");
		Date date=Date.valueOf(req.getParameter("dob"));
		String password=req.getParameter("password");
		String cPassword= req.getParameter("cPassword");
		
		if(password.equals(cPassword)) {
			if(id<121000) {
				DoctorDto doctorDto=this.doctorDao.fetchDoctorDataById(id);
				try {
					Long phone= Long.parseLong(emailOrPhone);
					if(doctorDto!=null && doctorDto.getPhone()==phone && doctorDto.getName().equals(name) && doctorDto.getDob().equals(date)) {
						this.doctorDao.updateDoctorPassword(doctorDto);
						resp.getWriter().print("<h1 style='color:green'>Password Changed Successfully</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);	
					}else {
						resp.getWriter().print("<h1 style='color:red'>Incorrect Details</h1>");
						req.getRequestDispatcher("forgotPassword.html").include(req, resp);
					}
				}catch (Exception e) {
					if(doctorDto!=null && doctorDto.getEmail().equals(emailOrPhone) && doctorDto.getName().equals(name) && doctorDto.getDob().equals(date)) {
						this.doctorDao.updateDoctorPassword(doctorDto);
						resp.getWriter().print("<h1 style='color:green'>Password Changed Successfully</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}else {
						resp.getWriter().print("<h1 style='color:red'>Incorrect Details</h1>");
						req.getRequestDispatcher("forgotPassword.html").include(req, resp);
					}
				}				
			}else {
				StaffDto staffDto=this.staffDao.fetchStaffDataById(id);
				try {
					Long phone=Long.parseLong(emailOrPhone);
					System.out.println("String is parsed");
					if(staffDto!=null && staffDto.getPhone()==phone && staffDto.getName().equals(name) && staffDto.getDob().equals(date)) {
						this.staffDao.updateStaffPassword(staffDto);
						resp.getWriter().print("<h1 style='color:green'>Password Changed Successfully</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}else {
						System.out.println("Failed");
						resp.getWriter().print("<h1 style='color:red'>Incorrect Details</h1>");
						req.getRequestDispatcher("forgotPassword.html").include(req, resp);
					}
				}catch (Exception e) {
					if(staffDto!=null && staffDto.getEmail().equals(emailOrPhone) && staffDto.getName().equals(name) && staffDto.getDob().equals(date)) {
						this.staffDao.updateStaffPassword(staffDto);
						resp.getWriter().print("<h1 style='color:green'>Password Changed Successfully</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}else {
						System.out.println("Failed");
						resp.getWriter().print("<h1 style='color:red'>Incorrect Details</h1>");
						req.getRequestDispatcher("forgotPassword.html").include(req, resp);
					}
				}	
			}
		}else {
			resp.getWriter().print("<h1 style='color:red'>Password Missmatch</h1>");
			req.getRequestDispatcher("forgotPassword.html").include(req, resp);
		}
	}
}
