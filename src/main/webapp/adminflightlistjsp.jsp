<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flyawayproject.AdminFlightResultsDTO"%>
<!DOCTYPE html>
<%
List rList = (List)session.getAttribute("adminflightList");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Flight List</title>
<style>
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


#Routelist {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#Routelist td, #Routelist th {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center
}

#Routelist tr:nth-child(even){background-color: #f2f2f2;}

#Routelist tr:hover {background-color: #ddd;}

#Routelist th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
  text-align: center
}
</style>
</head>




<body>

	<div class="topnav">
   <li><a href="dashboardjsp">FlyAway(Admin)</a></li>

  <li style="float:right"><a class="active" href="adminlogoutjsp">Logout</a></li>
</div>

<table id="Routelist">
<tr>
	<th>Flight No</th>
    <th>RouteId</th>
    <th>Price</th>
    <th>Seats</th>
    <th>Departure Time</th>
    <th>Arrival time</th>
    
</tr>
    <%
    for(Object test: rList){
    %>
        <tr>
        	<td><%=((AdminFlightResultsDTO)test).getFlightId()%> </td>
            <td><%=((AdminFlightResultsDTO)test).getRouteId()%> </td>
            <td><%=((AdminFlightResultsDTO)test).getPrice()%></td>  
            <td><%=((AdminFlightResultsDTO)test).getSeats()%></td>
  			<td><%=((AdminFlightResultsDTO)test).getDeparturetime()%></td>
  			<td><%=((AdminFlightResultsDTO)test).getArrivaltime()%></td>          
    
        </tr>
      <%}%>
</table>
</form>
</body>
</html>