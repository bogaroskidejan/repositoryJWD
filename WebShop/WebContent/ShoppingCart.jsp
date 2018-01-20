<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "servlet1.webshop.ShoppingCart" %>
<%@ page import = "servlet1.webshop.ShoppingCartItem" %>
<%@ page import = "servlet1.webshop.User" %>
<%@ page import = "java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="table.css" rel="stylesheet">
</head>
<body>
   <table id="someTable">
	<thead>
   	<tr>
   		<th>Naziv</th>
   		<th>Jedinicna cena</th>
   		<th>Komada</th>
   		<th>Ukupna cena</th>
   		<th>&nbsp;</th>
   	</tr>
   	</thead>
   <% double total = 0; %>
   <% ShoppingCart sc = ((User)session.getAttribute("user")).getShoppingCart(); %>
   <%for(ShoppingCartItem item : sc.getItems()) { %>
     <tbody>
     <tr>
       <td><%=item.getProduct().getName()%></td>
       <td><%=item.getProduct().getPrice()%></td>
       <td><%=item.getCount()%></td>
       <td>
       <% double price = item.getProduct().getPrice() * item.getCount(); %>
       <%= price %>
       </td>
       <td><form action = "DeleteServlet" method = "post">
       <input type = "hidden" name="id" value="<%=item.getId()%>">
       <input type="submit" value = "Brisanje"></form></td>
     </tr>  
     </tbody>
     <% total += price; %>
   <%} %>
   </table>
   <p>Ukupno <%=total %> dinara</p>
   <a href ="WebShopServlet">Povratak</a>
</body>
</html>