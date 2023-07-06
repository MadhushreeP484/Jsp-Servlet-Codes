<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.hospital.dao.PatientDaoImpl"%>
<%@page import="com.jsp.hospital.dao.PatientDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Patient For Doctor</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("doctor")==null){
	response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
	request.getRequestDispatcher("login.html").include(request, response);
}else{
	PatientDao patientDao= new PatientDaoImpl();
	List<PatientDto> list=patientDao.fetchAllPatient();%>
	<div class="topnav"><a href="doctorHome.html"><button>Back</button></a></div><br>
<table border="1">
<tr><td>Id</td><td>Name</td><td>Phone</td><td>Age</td><td>Picture</td>
<%for(PatientDto dto:list){%>
	<tr><td><%=dto.getId()%></td><td><%=dto.getName()%></td><td><%=dto.getPhone()%></td><td><%=dto.getAge()%></td>
	<td><%String base64 = Base64.encodeBase64String(dto.getPicture());	%>
	<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></td>
<%} %>
</table>
<%} %>
</body>
</html>