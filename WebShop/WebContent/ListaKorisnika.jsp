<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="servlet1.webshop.User"%>
<%@page import="java.util.List"%>
<jsp:useBean id="ListUsersServlet" class="servlet1.ListUsersServlet"
	scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista korisnika</title>
<link href="table.css" rel="stylesheet">
<script src="jquery-1.11.0.js"></script>
<script>
$(function () {
    var $chk = $("#grpChkBox input:checkbox"); 
    var $tbl = $("#someTable");
    var $tblhead = $("#someTable th");
 
    $chk.prop('checked', true); 
 
    $chk.click(function () {
        var colToHide = $tblhead.filter("." + $(this).attr("name"));
        var index = $(colToHide).index();
        $tbl.find('tr :nth-child(' + (index + 1) + ')').toggle();
    });
});
</script>
</head>
<body>
    <table id="someTable">
    <thead>
    	<tr>
    		<th>Korisnicko ime</th>
    		<th class="lozinka">Lozinka</th>
    		<th class="akcija">Akcija</th>
    	</tr>
    </thead>
    <% List<User> users = (List<User>)request.getSession().getAttribute("users"); %>
    <% for(User user : users){    	
     %>
     <tbody>
     <tr>
        <td><%=user.getUsername() %></td>
        <td class="lozinka"><%=user.getPassword() %></td>
        <td class="akcija"><a href="DeleteUserServlet?id=<%=user.getId()%>">Brisanje</a></td>
       </tr>
      </tbody>
    <% 
    }
    %>
    </table>
    <div id="grpChkBox">
    	<p><input type="checkbox" name="lozinka" />Password</p>
    	<p><input type="checkbox" name="akcija" />Brisanje</p>
    </div>
</body>
</html>