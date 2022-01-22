<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flyawayproject.FlightsResultsDTO"%>
<!DOCTYPE html>
<% FlightsResultsDTO bookflight = (FlightsResultsDTO)session.getAttribute("book");%>
<% String src = request.getSession().getAttribute("source").toString();%>
<% String dest = request.getSession().getAttribute("destination").toString();%>
<% String nopas = request.getSession().getAttribute("nopas").toString();%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Details</title>
<style>
 *{
  box-sizing: border-box;
}

body {
  margin: 0;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 30px;
  font-style: oblique;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #6495ED;
}

/* Style the top navigation bar */
.topnav {
  overflow: hidden;
  background-color: #6495ED;
  
}

/* Style the topnav links lettering */
.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  font-size: 30px;
  font-style: oblique;
  
}

/* Change color on hover */
.topnav a:hover {
  background-color: #111;
  color:#6495ED;
}

input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  readonly  : true;
}

input[type=submit] {
  width: 60%;
  background-color: #6495ED;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
}



/* Create two equal columns that floats next to each other */
.column {
  float: left;
  width: 50%;
  padding: 30px;
 border-right-style: solid;
   background-color:#f2f2f2
}
.footer {
  background-color: #f1f1f1;
  padding: 10px;
  text-align: center;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - makes the 2 columns stack on top of each other instead of next to each other */
@media screen and (max-width:600px) {
  .column {
    width: 100%;
     
  }
  
}
</style>
</head>
<body>

<div class="topnav">
   <li><a href="homePagejsp">FlyAway</a></li>

  <li style="float:right"><a class="active" href="logoutjsp">Logout</a></li>
</div>


<form action = "confimrationservlet" method = "POST" >
<div class="row">
  <div class="column">
  
  
   
   <h3>Flight no - <%= bookflight.getFlightId() %></h3>
     <h3>Source -<%= src %></h3>  <h3>Destination- <%= dest %></h3>  
    <h3>Departure time -<%=bookflight.getDepartureDate() %></h3><h3>Arrival time-<%= bookflight.getArrivalDate()  %></h3>
   
  </div>
  
  <div class="column">
    
    <h3>Price per Passenger  -  <%= bookflight.getPrice()  %> Rs</h3>
    <h3>No of passengers    - <%= nopas %></h3>
        <h3>___________________________________</h3> 
          <h3>Total         -  <%= bookflight.getPrice()  * Integer.parseInt(nopas) %> Rs</h3>
         
  </div>
  <div class="footer">
  <input type="submit" value="Pay">
</div>
  
  
</div>
</form>

</body>
</html>


