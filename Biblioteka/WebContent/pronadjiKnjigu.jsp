<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import = "biblioteka.model.Knjiga"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pronadjena knjiga</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
<link href="starr.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.js"></script>
<script src="star.js"></script>
<link href="table.css" rel="stylesheet">
</head>
<body>
<% Knjiga knjiga = (Knjiga)session.getAttribute("knjiga"); %>
  <table id="someTable">
  <thead>
  	<tr>
		<th>Naslov</th>
		<th>Autor</th>
  	</tr>
  </thead>
  <tbody>
  	<tr>
  		<td><%= knjiga.getNaslov() %></td>
  		<td> <%= knjiga.getAutor().getIme() + " " + knjiga.getAutor().getPrezime() %></td>
  	</tr>
  </tbody>
  </table>
  <div class="pull-left">
    <h4>Rejting knjige:</h4>
    <div class='starrr' id='star1'></div>
    <div>&nbsp;
      <span class='your-choice-was' style='display: none;'>
        Rejting: <span class='rejting'></span>.
      </span>
    </div>
  <script>
    $('#star1').starrr({
      change: function(e, value){
        if (value) {
          $('.your-choice-was').show();
          $('.rejting').text(value);
        } else {
          $('.your-choice-was').hide();
        }
      }
    });
    var $s2input = $('#star2_input');
    $('#star2').starrr({
      max: 10,
      rating: $s2input.val(),
      change: function(e, value){
        $s2input.val(value).trigger('input');
      }
    });
  </script>
</body>
</html>