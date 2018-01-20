<%@ page import = "biblioteka.model.Knjiga"%>
<%@ page import = "java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pregled knjiga</title>
<link href="table.css" rel="stylesheet">
</head>
<body>

<% List<Knjiga> knjige = (List<Knjiga>) session.getAttribute("knjige"); %>
<table id="someTable">
	<thead>
		<tr>
			<th>Naslov</th>
			<th>Autor</th>
		</tr>
	</thead>
<% for (Knjiga k : knjige) {%>
	<tbody>
	<tr>
		<td><%=k.getNaslov() %></td>
		<td><%=k.getAutor().getIme() + " " + k.getAutor().getPrezime()%></td>
	</tr>
	</tbody>
<%} %>
</table>
</br>
<form action="PronadjiKnjiguServlet" method="post">
Naziv knjige:<input type="text" name="naziv"/><br/>
<input type="submit" value="pronadji"/><br/>
</form>
</body>
</html>