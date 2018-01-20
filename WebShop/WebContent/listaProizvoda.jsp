<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="servlet1.webshop.Product"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista proizvoda</title>
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
 		<th>Ime proizvoda</th>
 		<th>Cena</th>
 		<th class="akcija">&nbsp;</th>
 	</tr>
 	</thead>
 <% List<Product> products = (List<Product>) session.getAttribute("proizvodi"); %>
 <% for(Product product : products ) {%>
 <tbody>
  <tr>
    <td><%=product.getName()%></td>
    <td><%=product.getPrice()%></td>
    <td class="akcija"><a href="DeleteProduct?id=<%=product.getId()%>">Brisanje</a></td>
  </tr>
  </tbody> 
 <%} %>
</table>
 <div id="grpChkBox">
    <p><input type="checkbox" name="akcija" />Brisanje</p>
 </div>
</body>
</html>