<%@page import="com.jsp.hospital.dto.DoctorDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Approval Page</title>
</head>
<body>
	<%
	List<DoctorDto> list = (List<DoctorDto>) request.getAttribute("list");
	%>
	<table border="1">
		<tr>
			<td>Id</td><td>Name</td><td>Phone</td><td>Email</td><td>Gender</td><td>Age</td><td>Specialization</td><td>status</td><td>Approve</td>
			<%for (DoctorDto dto : list) {%>
		<tr>
			<td><%=dto.getDId()%></td><td><%=dto.getName()%></td>
			<td><%=dto.getPhone()%></td><td><%=dto.getEmail()%></td>
			<td><%=dto.getGender()%></td><td><%=dto.getAge()%></td>
			<td><%=dto.getSpecialisation()%></td><td><%=dto.isStatus()%></td>
			<td><a href="approveDoctorStatus?dId=<%=dto.getDId()%>"><button>Approve</button></a></td>
	<%} %>
	</table>
	<a href="adminHome.jsp"><button>Back</button></a>
</body>
</html>