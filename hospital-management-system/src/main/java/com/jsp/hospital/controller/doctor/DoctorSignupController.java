package com.jsp.hospital.controller.doctor;

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
import com.jsp.hospital.dto.DoctorDto;

@WebServlet("/doctorSaveAction")
public class DoctorSignupController extends HttpServlet{
	private DoctorDao doctorDao;
	private StaffDao staffDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked init"+this.getClass().getSimpleName());
		this.doctorDao=new DoctorDaoImpl();
		this.staffDao= new StaffDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked DoctorSignupController doPost");
		String name= req.getParameter("name");
		String email=req.getParameter("email");
		long phone=Long.parseLong(req.getParameter("phone"));
		Date date=Date.valueOf(req.getParameter("dob"));
		String gender=req.getParameter("gender");
		String password=req.getParameter("password");
		String cPassword=req.getParameter("cPassword");
		String specialization=req.getParameter("specialization");
		String qualification=req.getParameter("qualification");
		
		int age=Period.between(date.toLocalDate(), LocalDate.now()).getYears();
		DoctorDto doctorDto=new DoctorDto(name, phone, email, date, qualification, specialization, password, gender, age);
		if(age>20) {
			if(password.equals(cPassword)) {
				if(this.doctorDao.fetchDoctorDataByPhone(phone)==null && this.doctorDao.fetchDoctorId(email)==0 && this.staffDao.fetchStaffDataByPhone(phone)==null && this.staffDao.fetchStaffId(email)==0) {
					this.doctorDao.saveDoctorData(doctorDto);
					try {
						resp.getWriter().print("<h1 style='color:green'>Registration is complete for "+name+", Wait for the Admin approval </h1>");
						resp.getWriter().print("<h1 style='color:blue'>Please find the Account Id: "+doctorDto.getDId()+"</h1>");
						req.getRequestDispatcher("login.html").include(req, resp);
					}catch (Exception e) {
						resp.getWriter().print("<h1>Registration failed</h1>");
						req.getRequestDispatcher("doctorSignup.html").include(req, resp);
					}
				}else {
					resp.getWriter().print("<h1>Registration failed since Email or Phone number is already exist</h1>");
					req.getRequestDispatcher("doctorSignup.html").include(req, resp);
				}
					
			}else {
				resp.getWriter().print("<h1>Password Missmatch</h1>");
				req.getRequestDispatcher("doctorSignup.html").include(req, resp);
			}
		}else {
			resp.getWriter().print("<h1>You are not a Doctor, Go and Study</h1>");
			req.getRequestDispatcher("doctorSignup.html").include(req, resp);
		}
	}

}
