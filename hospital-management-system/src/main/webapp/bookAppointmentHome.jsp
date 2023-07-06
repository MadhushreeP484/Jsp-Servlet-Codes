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
	<%
	if (session.getAttribute("staff") == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired, Login Again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {%>
	<div class="topnav"><a href="staffHome.html"><button>Back</button></a></div><br>
	<a href="fetchAllPatientForStaff"><button>View All Patient</button></a>
	<a href="fetchPatientById.html"><button>Book Patient by Id</button></a>
	<%}%>
</body>
</html>