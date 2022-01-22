<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flyawayproject.FlightsResultsDTO"%>
<!DOCTYPE html>
<% List fList = (List)session.getAttribute("flightList");%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Details</title>
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

input[type=submit] {
  width: 100%;
  background-color: #6495ED;
  color: white;
  padding: 12px 15px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
}
#Flightlist {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#Flightlist td, #Flightlist th {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center
}

#Flightlist tr:nth-child(even){background-color: #f2f2f2;}

#Flightlist tr:hover {background-color: #ddd;}

#Flightlist th {
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



    <script type="text/javascript">
        function setValuesAndSubmit(flight_id, flight_price){
        	
		    document.getElementById('flightid').value = flight_id;
		    document.getElementById('flightprice').value = flight_price;
		
		//Uncomment below if you want to submit the form
		//document.forms[0].submit();
		document.getElementById('frm').submit();
	}
    </script>
</head>

<body>

	<div class="topnav">
   <li><a href="homePagejsp">FlyAway</a></li>

  <li style="float:right"><a class="active" href="logoutjsp">Logout</a></li>
</div>



<form action = "bookingservlet" id = "frm" method = "POST" >
<input type="hidden" id="flightid" name = "flightid"/>
<input type="hidden" id="flightprice" name = "flightprice"/>
<input type="hidden" id="flightdepdate" name = "flightdepdate"/>
<input type="hidden" id="flightarriavaldate" name = "flightarriavaldate"/>
<table id="Flightlist">
<tr>
    <th>FlightId</th>
    <th>Price</th>
    <th>Departure time</th>
   	<th> Arrival time   </th> 
   	<th> </th>
  </tr>
    <%
    for(Object test: fList){%>
        <tr>
            <td><%= ((FlightsResultsDTO)test).getFlightId() %> </td>
            <td><%= ((FlightsResultsDTO)test).getPrice() %></td>  
            <td><%= ((FlightsResultsDTO)test).getDepartureDate() %></td>
            <td><%= ((FlightsResultsDTO)test).getArrivalDate() %></td> 
                <%
    session.setAttribute("book", test);
        %>
            <td><button type="button"  onclick="setValuesAndSubmit(<%= ((FlightsResultsDTO)test).getFlightId() %>,
            <%= ((FlightsResultsDTO)test).getPrice() %>);" id="test">BOOK</button></td> 
        </tr>
      <%}%>
</table>
</form>
</body>
</html>