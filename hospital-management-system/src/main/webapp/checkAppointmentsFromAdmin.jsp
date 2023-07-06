<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@page import="com.jsp.hospital.dto.DoctorDto"%>
<%@page import="com.jsp.hospital.dto.AppointmentDto"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.hospital.dao.AppointmentDaoImpl"%>
<%@page import="com.jsp.hospital.dao.AppointmentDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Appointments From Admin</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
	<%	AppointmentDao dao = new AppointmentDaoImpl();
	int id = Integer.parseInt(request.getParameter("id"));
	List<AppointmentDto> list = dao.fetchAppointmentsByPatientId(id);
	if (list.isEmpty()) {
		response.getWriter().print("<h1 style='color:red'>No Appointments made</h1>");
		request.getRequestDispatcher("fetchAllPatientForAdmin.jsp").include(request, response);
	} else {%>
	<div class="topnav"><a href="adminHome.jsp"><button>Back</button></a></div><br>
	<table border="1"><tr><td>Appointment Id</td><td>Doctor Name</td><td>Problem</td><td>Date/Time</td>
			<%for (AppointmentDto dto : list) {%>
			<tr><td><%=dto.getId()%></td>
				<td><%=dto.getDoctorDto().getName()%></td>
				<td><%=dto.getProblem()%></td>
				<td><%if(dto.getDateTime()==null){%>Not Yet Visited Doctor<%}else{%>
				<%=dto.getDateTime()%><%}%></td>
			<%}}%>	
	</table>
</body>
</html>