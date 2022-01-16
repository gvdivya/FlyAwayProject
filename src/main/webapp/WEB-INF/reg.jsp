<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
  <form action="registration" method="POST">
    <label for="fname">First name</label>
    <input type="text" id="fname" name="fname" placeholder="Your name..">

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lname" placeholder="Your last name..">

    <label for="email">Email</label>
    <input type="email" id="email" name="email" placeholder="Your email-id..">
  	
    <label for="password">Password</label>
	<input type="password" id="password" name="password" placeholder="Enter Password..">
		
	<label for="confirmPassword">Confirm Password</label>
	<input type="password" id="confirmPassword" name="confirmPassword"placeholder="Confirm Password..">
		
    <input type="submit" value="Submit">
    
    <h3>(Already User )<a href="loginjsp">Login</a></h3>
   

  </form>
</div>
</body>
</html>