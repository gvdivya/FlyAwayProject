package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;



public class LoginServlet extends HttpServlet {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		boolean userFound= false;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
					System.out.println("Connection established!");
					
					try(PreparedStatement ps = connection.prepareStatement("Select * from users where email=? and password=?")) {
						// Setting values for the placeholders
						ps.setString(1, email);
						ps.setString(2, password);
						
						
						// Execute the query
						ResultSet rs = ps.executeQuery();
						
						 userFound=rs.next();
						
						
					} 		
				} 
			} catch(ClassNotFoundException | SQLException e) {
				System.out.println("DB error"  + e.getMessage());
				
			}
			
			response.setContentType("text/html");
			
			if(userFound) {
		
					System.out.println("Login Successfull");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					RequestDispatcher rd= request.getRequestDispatcher("flightlistservlet");
					rd.forward(request, response);
					
					/*response.getWriter().write(
							"<html><body>"
									+ "<ul><li>" 
									+ email
									+ "</li></ul>"
									+"<h2>Login successful!</h2>"
									+ "</body></html>"
							);	*/
				} else 
					{
					System.out.println("Login failed");
					
						response.getWriter().write("<html><body>"
							
							
							+"<h1>       Wrong email/password        </h1>"
							
							+ "</body></html>"
					);
					RequestDispatcher rd= request.getRequestDispatcher("loginjsp");
					rd.include(request, response);
					/*response.getWriter().write(
						"<html><body>"
								+"<h2>Login Failed!</h2>"
								+ "</body></html>"	);*/
				}
			}
		} 
	
