package com.jsp.hospital.controller.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.AppointmentDao;
import com.jsp.hospital.dao.AppointmentDaoImpl;
import com.jsp.hospital.dao.DoctorDao;
import com.jsp.hospital.dao.DoctorDaoImpl;
import com.jsp.hospital.dao.PatientDao;
import com.jsp.hospital.dao.PatientDaoImpl;
import com.jsp.hospital.dto.AppointmentDto;
import com.jsp.hospital.dto.DoctorDto;
import com.jsp.hospital.dto.PatientDto;

@WebServlet("/bookAppointment")
public class BookAppointmentController extends HttpServlet {
	
	private PatientDao patientDao;
	private DoctorDao doctorDao;
	private AppointmentDao appointmentDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.patientDao= new PatientDaoImpl();
		this.doctorDao= new DoctorDaoImpl();
		this.appointmentDao= new AppointmentDaoImpl();		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked BookAppointmentController doPost");
		String pName=req.getParameter("name");
		int pId=Integer.parseInt(req.getParameter("id"));
		String problem= req.getParameter("problem");
		int dId=Integer.parseInt(req.getParameter("doctor"));
		
		PatientDto patientDto=this.patientDao.fetchPatientById(pId);
		DoctorDto doctorDto=this.doctorDao.fetchDoctorDataById(dId);
		
		AppointmentDto appointmentDto=new AppointmentDto();
		//manyToOne
		appointmentDto.setPatientDto(patientDto);
		//manyToOne
		appointmentDto.setDoctorDto(doctorDto);
		appointmentDto.setProblem(problem);
		this.appointmentDao.saveAppointment(appointmentDto);
		
		List<AppointmentDto> pList=patientDto.getAppointments();
		if(pList==null) {
			pList= new ArrayList<AppointmentDto>();
		}
		pList.add(appointmentDto);
		patientDto.setAppointments(pList);
		
		List<AppointmentDto> dList=doctorDto.getAppointmentDtos();
		if(dList==null) {
			dList= new ArrayList<AppointmentDto>();
		}
		dList.add(appointmentDto);
		doctorDto.setAppointmentDtos(dList);
		
		//oneToMany
		this.doctorDao.updateDoctorPassword(doctorDto);
		//oneToMany
		this.patientDao.updatePatient(patientDto);
		
		resp.getWriter().print("<h1>Appointment of "+pName+" is booked with Dr."+doctorDto.getName()+" for the problem "+problem+" is complete</h1>");
		req.getRequestDispatcher("staffHome.html").include(req, resp);
	}

}
