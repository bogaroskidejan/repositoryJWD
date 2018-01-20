<%@ page import = "biblioteka.model.Autor"%>
<%@ page import = "java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administracija</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="jquery-1.11.0.js"></script>
<link href="table.css" rel="stylesheet">
</head>
<body>

<% List<Autor> autori = (List<Autor>) session.getAttribute("autori"); %>

<h2>Dodavanje autora</h2>
<form action  = "DodajAutoraServlet" method = "POST">
<table id="someTable">	
<thead>
	<tr><td>Ime</td><td><input name = "ime"/></td></tr>
	<tr><td>Prezime</td><td><input name = "prezime"/></td></tr>
	<tr><td><input type = "submit" value = "Submit"/></td></tr>
</thead>
</table>
</form><br/><br/>

<h2>Dodavanje knjiga</h2>
<form action  = "DodajKnjiguServlet" method = "POST">
<table id="someTable">
<tr><td>Naslov</td><td><input name = "naslov"/></td>
<tr>
<td>Autor</td>
<td><select name = "autori">
<% for (Autor a : autori) {%>
	<option value = "<%=a.getId()%>"><%= a.getIme( )+ " " +  a.getPrezime() %></option>
<%} %>
</select>
</td>
</tr>
<tr><td><input type = "submit" value = "Submit"/></td></tr>
</table>
</form>

</body>
</html>