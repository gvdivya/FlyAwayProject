package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class HomePageServlet extends HttpServlet {
	final String DB_URL = "jdbc:mysql://localhost:3306/Flyaway";
	final String USER = "root";
	final String PASSWORD = null;
	private boolean flightsFound = false;

	// For testing purposes
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();

		String date = request.getParameter("date");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		int nopas = Integer.parseInt(request.getParameter("nopas"));
		
		session.setAttribute("date",date);
		session.setAttribute("source",source);
		session.setAttribute("destination",destination);
		session.setAttribute("nopas",request.getParameter("nopas"));
		


		ResultSet rs;
		Integer rid = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
				System.out.println("Connection established!");


				try (PreparedStatement fs = connection
						.prepareStatement("Select rid from route where source=? and destination =? ")) {

					fs.setString(1, source);
					fs.setString(2, destination);

					// Execute the query
					ResultSet res = fs.executeQuery();

					while (res.next()) {
						System.out.println(res.getString(1) + " ");
						rid = res.getInt(1);
						
						session.setAttribute("rid",rid.toString());
						
					} 
                 
				}

				try (PreparedStatement ps = connection
						.prepareStatement("Select count(*) from flight where rid=? and date =? and seats>=?")) {
					// Setting values for the placeholders
					ps.setInt(1, rid);
					ps.setString(2, date);
					ps.setInt(3, nopas);
					

					// Execute the query
					rs = ps.executeQuery();
					rs.next();
				    int rowCount = rs.getInt(1);
				    
				   /* if (rowCount>0)
				    {
				    	response.getWriter().write(
								"<html><body>"
										+ "<ul><li>" 
										+ rowCount
										
										+"<h2>flights found</h2>"
										
										+ "</body></html>"
								);	
				    }
				    
				    else {
				    	response.getWriter().write(
								"<html><body>"
										
										
										+"<h2>flights not found</h2>"
										
										+ "</body></html>"
								);	
				    }*/
				   

					response.setContentType("text/html");

					ResultSetMetaData rsmd = rs.getMetaData();
					
					int columnsNumber = rsmd.getColumnCount();
					
					if (rowCount>0)
					
					{
						System.out.println("flights found"+ rowCount );
						
						RequestDispatcher rd = request.getRequestDispatcher("registrationjsp");
						rd.forward(request, response);
						
						    while (rs.next()) {
							// Print one row
							for (int i = 1; i <= columnsNumber; i++) {

								System.out.print(rs.getString(i) + " "); // Print one element of a row
							
							}

							System.out.println();// Move to the next line to print the next row.

						}
						
		
						
						
					}

					else {
						System.out.println("flights not found");
						response.getWriter().write(
								"<html><body>"
										
										
										+"<h1>       Flights not found         </h1>"
										
										+ "</body></html>"
								);	
						RequestDispatcher rd = request.getRequestDispatcher("homePagejsp");
						rd.include(request, response);

					}

					

				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB error" + e.getMessage());

		}
	}
}
