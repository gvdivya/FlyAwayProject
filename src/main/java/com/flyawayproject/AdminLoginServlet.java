package com.flyawayproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginServlet extends HttpServlet {
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
		boolean adminFound= false;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
					System.out.println("Connection established!");
					
					try(PreparedStatement ps = connection.prepareStatement("Select * from admin where email=? and password=?")) {
						// Setting values for the placeholders
						ps.setString(1, email);
						ps.setString(2, password);
						
						
						// Execute the query
						ResultSet rs = ps.executeQuery();
						
						 adminFound=rs.next();
						
						
					} 		
				} 
			} catch(ClassNotFoundException | SQLException e) {
				System.out.println("DB error"  + e.getMessage());
				
				
			}
			
			response.setContentType("text/html");
			
			if(adminFound) {
		
					System.out.println("Login Successfull");
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("adminFound", adminFound);
					
					RequestDispatcher rd= request.getRequestDispatcher("dashboardjsp");
					rd.forward(request, response);
					response.getWriter().write("<html><body>"
							
							
							+"<h1>       Login Successfull       </h1>"
							
							+ "</body></html>"
					);
					
					
				} else 
					{
					System.out.println("Login failed");
                     
					
					
					RequestDispatcher rd= request.getRequestDispatcher("adminloginjsp");
					rd.include(request, response);
					response.getWriter().write("<html><body>"
							
							
							+"<h1>       Wrong email/password        </h1>"
							
							+ "</body></html>"
					);
					
					
                         
				}
			}
		} 
	


