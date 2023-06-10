package com.jsp.hospital.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.hospital.dao.StaffDao;
import com.jsp.hospital.dao.StaffDaoImpl;
import com.jsp.hospital.dto.StaffDto;

@WebServlet("/approveStaffStatus")
public class ApproveStaffStatusController extends HttpServlet {
	
	private StaffDao staffDao;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Invoked "+this.getClass().getSimpleName());
		this.staffDao= new StaffDaoImpl();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Invoked ApproveStaffStatusController service");
		int id=Integer.parseInt(req.getParameter("sId"));
		
		this.staffDao.updateStaffStatus(id);
		List<StaffDto> list=this.staffDao.fetchAllStaff();
		if(list!=null) {
			req.setAttribute("list", list);
			req.getRequestDispatcher("approveStaff.jsp").include(req, resp);
		}else {
			resp.getWriter().print("There are no Staff");
			req.getRequestDispatcher("adminHome.jsp").include(req, resp);
		}
	}

}
