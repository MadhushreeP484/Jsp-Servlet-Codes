<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.jsp.hospital.dao.PatientDaoImpl"%>
<%@page import="com.jsp.hospital.dao.PatientDao"%>
<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Patient Details</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
	<%if (session.getAttribute("staff") == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {
		PatientDto patientDto=(PatientDto) request.getAttribute("patientDto");%>
		<div class="topnav"><a href="editPatientById.html"><button>Back</button></a></div><br>
	<form action="editPatientDetails" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr><td>Id</td><td>Name</td><td>Phone</td><td>Gender</td><td>Dob</td><td>Age</td><td>Picture</td><td>Update</td>
			<tr>
				<td><input type="number" name="id" value="<%=patientDto.getId()%>" readonly="readonly"></td>
				<td><input type="text" name="name" value="<%=patientDto.getName()%>"></td>
				<td><input type="tel" pattern="[0-9]{10}" name="phone" value="<%=patientDto.getPhone()%>"></td>
				<td><input type="text" name="gender" value="<%=patientDto.getGender()%>" readonly="readonly"></td>
				<td><input type="Date" name="dob" value="<%=patientDto.getDob()%>"></td>
				<td><input type="number" name="age" value="<%=patientDto.getAge()%>" readonly="readonly"></td>
				<td><%String base64 = Base64.encodeBase64String(patientDto.getPicture());%>
					<img height="100" width="100" alt="unknown" src="data:image/jpeg;base64,<%=base64%>"></td>
				<td><button type="submit">Update</button></td>
		</table>
	</form>
	<%}%>
</body>
</html>