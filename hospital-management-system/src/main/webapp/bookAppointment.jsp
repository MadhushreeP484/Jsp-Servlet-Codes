<%@page import="com.jsp.hospital.dto.DoctorDto"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.hospital.dto.StaffDto"%>
<%@page import="com.jsp.hospital.dto.PatientDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Appointment By Id with Diagnosis</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
	<%if (session.getAttribute("staff") == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {
		List<DoctorDto> doctorDtos= (List<DoctorDto>)request.getAttribute("doctorDtos");
		PatientDto patientDto = (PatientDto) request.getAttribute("patientDto");
		StaffDto staffDto = (StaffDto) session.getAttribute("staff");%>
	
	<div class="topnav"><a href="bookAppointmentHome.jsp"><button>Back</button></a></div><br>
	<form action="bookAppointment" method="post">
		<table> <tr>
				<td><label for="sname">Staff Name</label></td>
				<td><input type="text" name="sname" id="sname" value="<%=staffDto.getName()%>" readonly="readonly"></td>
			<tr>
				<td><label for="name">Patient Name</label></td>
				<td><input type="text" name="name" id="name" value="<%=patientDto.getName()%>" readonly="readonly"></td>
			<tr>
				<td><label for="id">Patient Id</label></td>
				<td><input type="number" name="id" id="id" value="<%=patientDto.getId()%>" readonly="readonly"></td>
			<tr>
				<td><label for="pbm">Problem</label></td>
				<td><textarea rows="4" cols="40" name="problem" id="pbm" placeholder="Enter Problem here" ></textarea></td>
			<tr>
				<td><label for="doctor">select Doctor</label></td>
				<td><select id="doctor" name="doctor">
				<%for(DoctorDto doctorDto:doctorDtos){ %>
						<option value="<%=doctorDto.getDId()%>"><%=doctorDto.getName()%>(<%=doctorDto.getSpecialisation() %>)</option>
					<%} %>	
				</select>
			<tr><td><button type="reset">Cancel</button></td>
				<td><button type="submit">Book</button></td>
		</table>
	</form>
	<%}%>
	
	
</body>
</html>