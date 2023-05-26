package com.jsp;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lifecycle")
public class LifeCycleCheckServlet implements Servlet{
	
	static {
		System.out.println("LifeCycleCheckServlet class is loaded");
	}
	
	public LifeCycleCheckServlet() {
		System.out.println("Invoked "+this.getClass().getSimpleName());
	}

	@Override
	public void destroy() {
		System.out.println("destroy is invoked");
		
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init is invoked");
		
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("service is invoked");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
