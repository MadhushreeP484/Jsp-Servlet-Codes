<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Appointment</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
<%if(session.getAttribute("staff")!=null){
	List<PatientDto> list=(List<PatientDto>)request.getAttribute("list"); %>
	<div class="topnav"><a href="bookAppointment.jsp"><button>Back</button></a></div><br>
	<table border="1">
			<tr>
				<td>Id</td><td>Name</td><td>Phone</td><td>Gender</td><td>Dob</td><td>Age</td><td>image</td><td>Book</td>
				<%for (PatientDto dto : list) {%>
			<tr>
				<td><%=dto.getId()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getPhone()%></td>
				<td><%=dto.getGender()%></td>
				<td><%=dto.getDob()%></td>
				<td><%=dto.getAge()%></td>
				<td>
				<%String base64 = Base64.encodeBase64String(dto.getPicture());	%>
				<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%=base64%>">
				</td>
				<td><a href="bookPatientById?pId=<%=dto.getId()%>"><button>Book</button></a></td>
		<%} %>
		</table>
<%}%>
</body>
</html>