package com.jsp.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/selectRoleAction")
public class SelectRoleController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked SelectRoleController doPost");
		String role=req.getParameter("role");
		System.out.println(role);
		if(role.equals("doctor")) {
			System.out.println("doctor");
			req.getRequestDispatcher("doctorSignup.html").forward(req, resp);
		}else {
			System.out.println("staff");
			req.getRequestDispatcher("staffSignup.html").forward(req, resp);
		}
	}
}
