<%@ page import = "biblioteka.model.Autor"%>
<%@ page import = "java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<style>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pregled autora</title>
<link href="table.css" rel="stylesheet">
</head>
<body>

<% List<Autor> autori = (List<Autor>) session.getAttribute("autori"); %>
<table id="someTable">
	<thead>
	<tr>
		<th>Ime</th>
		<th>Prezime</th>
	</tr>
	</thead>
<% for (Autor a : autori) {%>
	<tbody>
	<tr>
		<td><%=a.getIme() %></td>
		<td><%=a.getPrezime()%></td>
	</tr>
	</tbody>
<%} %>
</table>
</body>
</html>