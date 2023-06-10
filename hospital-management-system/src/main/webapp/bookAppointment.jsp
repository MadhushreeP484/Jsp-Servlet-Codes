<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Appointment</title>
</head>
<body>
<form action="bookAppointmentAction" method="post">
<table>
<tr><td>
<label for="name">Name</label></td><td>
<input type="text" name="name" id="name"></td>
<tr><td>
<label for="phone">Phone</label></td><td>
<input type="tel" pattern="{0-9}[10]" name="phone" id="phone"></td>
<tr><td>
<label for="diagnosis">Diagnosis</label></td><td>
<select id="diagnosis" name="diagnosis">
<option value="surgeon">accident</option>
<option value="radiologist"></option>
</select>
<tr><td>
<button type="reset">Cancel</button></td><td>
<button type="submit">Book</button></td>
</table>
</form>
<a href="staffHome.html"><button>Back</button></a>
</body>
</html>