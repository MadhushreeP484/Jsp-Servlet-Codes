<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link href="css\common.css" rel="stylesheet">
</head>
<body>
<%if(request.getSession().getAttribute("admin")!=null){ %>
<h1>Admin Home</h1>
<table>
<tr><td><a href="fetchStaff"><button>Approve Staff</button></a></td>
<tr><td><a href="fetchDoctor"><button>Approve Doctor</button></a></td>
<tr><td><a href="viewPatientForAdmin"><button>View All Patient</button></a></td> <!-- id, name,phone,age,photo,view appointment button when onclick appoin id, appoin doctor, problem -->
</table>
<a href="logout"><button>Logout</button></a>
<%}else{ response.getWriter().print("<h1>Session Expired, Login Again</h1>");
request.getRequestDispatcher("login.html").include(request, response);}%>
</body>
</html>