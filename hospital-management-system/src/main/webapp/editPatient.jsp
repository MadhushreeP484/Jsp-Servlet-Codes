<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Patient Details</title>
</head>
<body>
<%List<PatientDto> list=(List<PatientDto>)request.getAttribute("list"); %>
<table border="1">
		<tr>
			<td>Id</td><td>Name</td><td>Phone</td><td>Gender</td><td>Dob</td><td>Age</td><td>Update</td>
			<%for (PatientDto dto : list) {%>
		<tr>
			<td><input type="number" name="id" value="<%=dto.getId()%>" readonly="readonly"></td>
			<td><input type="text" name="name" value="<%=dto.getName()%>" readonly="readonly"></td>
			<td><input type="tel" pattern="[0-9]{10}" name="phone" value="<%=dto.getPhone()%>"></td>
			<td><input type="text" name="gender" value="<%=dto.getGender()%>" readonly="readonly"></td>
			<td><input type="Date" name="dob" value="<%=dto.getDob()%>" readonly="readonly"></td>
			<td><input type="number" name="age" value="<%=dto.getAge()%>" readonly="readonly"></td>
			<td><a href="editPatientDetails?pId=<%=dto.getId()%>"><button>Update</button></a></td>
	<%} %>
	</table>
</body>
</html>