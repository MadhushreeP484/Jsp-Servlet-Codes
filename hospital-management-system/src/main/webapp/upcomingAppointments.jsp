<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.jsp.hospital.dto.AppointmentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upcoming Appointments</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
<% if (session.getAttribute("doctor") == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {
	List<AppointmentDto> list=(List<AppointmentDto>)request.getAttribute("list");%>
	<div class="topnav"><a href="doctorHome.html"><button>Back</button></a></div><br>
	<table border="1">
	<tr><td>Appointment Id</td><td>patient Id</td><td>Patient Name</td><td>Patient Picture</td><td>Problem</td><td>Status</td>
	<%for(AppointmentDto dto:list){
		if(dto.getDateTime()==null){%>
		<tr><td><%=dto.getId()%></td>	
		<td><%=dto.getPatientDto().getId()%></td>
		<td><%=dto.getPatientDto().getName()%></td>
		<td><%String base64 = Base64.encodeBase64String(dto.getPatientDto().getPicture());	%>
		<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></td>
		<td><%=dto.getProblem()%></td>
		<td><a href="checkup?id=<%=dto.getId()%>"><button>Check Up</button></a></td>
		<% }}%>
		</table>
	<% }%>
</body>
</html>