package com.jsp.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookAppointmentAction")
public class BookAppointmentController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked BookAppointmentController doPost");
		String name=req.getParameter("name");
		Long phone=Long.parseLong(req.getParameter("phone"));
		String diagosis= req.getParameter("diagnosis");
	}

}
