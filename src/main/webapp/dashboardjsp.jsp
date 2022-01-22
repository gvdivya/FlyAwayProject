<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard </title>
<style>
ul {
  list-style-type: none;
  color: white;
  margin: 10;
  padding: 10;
  overflow: hidden;
  background-color: #6495ED;
  font-size: 20px;
  font-style: oblique;
}

</style>
</head>

<body>

	<ul>
	<h2>FlyAway(Admin)</h2>
	<h2 style="float:right"><a href="adminlogoutjsp">Logout</a></h2>
	</ul>

</body>

<style>

.center {
  text-align: left;
  border: 10px Black;
}


div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

ul.a {
  list-style-type: circle;
  background-color: #f2f2f2;
  color: #111;
  font-style: normal;
  line-height: 3;
  
  
}
</style>


<body>

<div class="center">
  
  <h1>Data</h1>
   <ul class="a">
     <li><a href="adminroutesservlet">Routes</a></li>
       <li><a href="adminflightlistservlet">Flights</a></li>
         <li><a href="adminbookingservlet">Booking</a></li>
  
</ul>

  
</div>

</body>
</html>



