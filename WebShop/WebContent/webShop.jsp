<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.List" %>
<%@ page import = "servlet1.webshop.Product" %>
<jsp:useBean id="WebShopServlet" class="servlet1.WebShopServlet"
	scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Shop</title>
<link href="table.css" rel="stylesheet">
</head>
<body>
  <table id="someTable">
  	<thead>
  	<tr bgcolor="lightgrey">
  		<th>Naziv</th>
  		<th>Cena</th>
  		<th>&nbsp;</th>
  	</tr>
  	</thead>
  <% List<Product> products = (List<Product>)session.getAttribute("products"); %>
  <% for(Product product: products) {%>
  <tbody>
   <tr>
    <td><%=product.getName()%></td>
    <td><%=product.getPrice()%></td>
    <td>
         <form method="get" action="ShoppingCartServlet">
		   <input type="text" size="3" name="itemCount">
           <input type="hidden" name="itemId" value="<%=product.getId()%>">
           <input type="submit" value="Dodaj">
        </form> 
   </td>  
   </tr>
   </tbody> 
  <%} %>
  </table>
  <a href="ShoppingCartServlet">Pregled sadrzaja korpe</a><br/>
  <a href = "LagoutServlet">Log out</a>
</body>
</html>