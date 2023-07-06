<%@page import="com.jsp.hospital.dao.AppointmentDaoImpl"%>
<%@page import="com.jsp.hospital.dao.AppointmentDao"%>
<%@page import="com.jsp.hospital.dto.AppointmentDto"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fetch All Patient For Admin</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("admin")==null){
	response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
	request.getRequestDispatcher("login.html").include(request, response);
}else{
List<PatientDto> list=(List<PatientDto>)request.getAttribute("list");%>
<div class="topnav"><a href="adminHome.jsp"><button>Back</button></a></div><br>
<table border="1">
<tr><td>Id</td><td>Name</td><td>Phone</td><td>Age</td><td>Picture</td><td>Appointments</td>
<%for(PatientDto dto:list){%>
	<tr><td><%=dto.getId()%></td><td><%=dto.getName()%></td><td><%=dto.getPhone()%></td><td><%=dto.getAge()%></td>
	<td><%String base64 = Base64.encodeBase64String(dto.getPicture());	%>
	<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></td>
	<td><a href="checkAppointmentsFromAdmin.jsp?id=<%=dto.getId()%>"><button type="submit">See Appointments</button></a></td>
<%} %>
</table>
<%} %>
</body>
</html>