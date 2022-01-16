package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RegistrationServlet extends HttpServlet {
	final String DB_URL = "jdbc:mysql://localhost:3306/Flyaway";
	final String USER = "root";
	final String PASSWORD = null;
	
	// For testing purposes
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		request.setAttribute("date", request.getAttribute("date"));
		
		String date = request.getAttribute("date").toString();
		
		String fullName = (firstName == null ? "" : firstName) + " " + (lastName == null ? "" : lastName);
		
		if(password.equals(confirmPassword)) {
			Cookie fnameCookie = new Cookie("first_name", firstName);
			Cookie lnameCookie = new Cookie("last_name", lastName);
			
			fnameCookie.setMaxAge(12*60*60);
			lnameCookie.setMaxAge(12*60*60);
			
			response.addCookie(fnameCookie);
			response.addCookie(lnameCookie);
			
			response.setContentType("text/html");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
					System.out.println("Connection established!");
					
					try(PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES(?, ?, ?)")) {
						// Setting values for the placeholders
						ps.setString(1, email);
						ps.setString(2, password);
						ps.setString(3, fullName);
						
						// Execute the query
						int rowsAfftected = ps.executeUpdate();
						
						if(rowsAfftected > 0) {
							System.out.println("A new record inserted!");
							
							RequestDispatcher rd = request.getRequestDispatcher("loginjsp");
							rd.forward(request, response);
							/*response.getWriter().write(
									"<html><body>"
											+ "<ul><li>" 
											+ fullName
											+ "</li><li>"
											+ email
											+ "</li></ul>"
											+"<h2>Registration successful!</h2>"
											+"<h3>New user record inserted!</h3>"
											+ "</body></html>"
									);	*/
						} else if(rowsAfftected == 0) {
							System.out.println("No new record inserted!");
							throw new SQLException();		
						}
					} 		
				} 
			} catch(ClassNotFoundException | SQLException e) {
				System.out.println("DB error"  + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("registrationjsp");
				rd.include(request, response);
				response.getWriter().write("Registration failed!email id already present");	
			}
		} else {
			System.out.println("Passwords do not match");
			RequestDispatcher rd = request.getRequestDispatcher("registrationjsp");
			rd.include(request, response);
			response.getWriter().write("Passwords do not match!");
					
							
							
		}	
	}
}
