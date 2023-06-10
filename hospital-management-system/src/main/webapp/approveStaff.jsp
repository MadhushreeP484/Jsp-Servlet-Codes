<%@page import="com.jsp.hospital.dto.StaffDto"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.hospital.dao.StaffDaoImpl"%>
<%@page import="com.jsp.hospital.dao.StaffDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Staff Approval Page</title>
</head>
<body>
	<%
	List<StaffDto> list = (List<StaffDto>)request.getAttribute("list");
	%>
	<table border="1">
		<tr>
			<td>Id</td><td>Name</td><td>Phone</td><td>Email</td><td>Gender</td><td>Age</td><td>Status</td><td>Approve</td>
			<%for (StaffDto dto : list) {%>
		<tr>
			<td><%=dto.getSId()%></td><td><%=dto.getName()%></td>
			<td><%=dto.getPhone()%></td><td><%=dto.getEmail()%></td>
			<td><%=dto.getGender()%></td><td><%=dto.getAge()%></td><td><%=dto.isStatus()%></td>
			<td><a href="approveStaffStatus?sId=<%=dto.getSId()%>"><button>Approve</button></a></td>
	<%}%>
	</table>
	<a href="adminHome.jsp"><button>Back</button></a>
</body>
</html>