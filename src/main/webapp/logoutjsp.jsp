<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout </title>
<style>

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
  padding: 5px 5px;
  font-size: 30px;
  font-style: oblique;
  
}

/* Change color on hover */
.topnav a:hover {
  background-color: #111;
  color:#6495ED;
}




</style>
</head>

<style>

.center {
  text-align: center;
  border: 10px Black;
}
input[type=submit] {
  width: 30%;
  background-color: #6495ED;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 18px;
}

div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>


<body>

<div class="topnav">
   <li><a href="homePagejsp">FlyAway</a></li>

  
</div>

<div class="center">
<form action="homePagejsp" method="POST">
  
  <h1>You are successfully logged out!</h1>
   
 <%request.getSession().invalidate(); %>
  <input type="submit" value="Retry">
</div>

</body>
</html>



