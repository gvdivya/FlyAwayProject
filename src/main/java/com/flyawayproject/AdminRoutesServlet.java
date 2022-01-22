package com.flyawayproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminRoutesServlet extends HttpServlet {
	final String DB_URL = "jdbc:mysql://localhost:3306/Flyaway";
	final String USER = "root";
	final String PASSWORD = null;
	;

	// For testing purposes
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResultSet rs;
		
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
				System.out.println("Connection established!");
					
                 try (PreparedStatement ps = connection
						.prepareStatement("Select rid , source,destination from route")) {
					// Setting values for the placeholders
					
					

					// Execute the query
					rs = ps.executeQuery();

					response.setContentType("text/html");

					ResultSetMetaData rsmd = rs.getMetaData();
					
					int columnsNumber = rsmd.getColumnCount();
					
					List<FlightRouteResultsDTO> result = new ArrayList<>();
			
						
					while (rs.next()) 
					{
						FlightRouteResultsDTO newResult = new FlightRouteResultsDTO();
						newResult.setRouteId(Integer.parseInt(rs.getString(1)));
						newResult.setSource(rs.getString(2));
						newResult.setDestination(rs.getString(3));
							// Print one row
							for (int i = 1; i <= columnsNumber; i++) 
							{
								System.out.print(rs.getString(i) + " "); // Print one element of a row
							
							}

							System.out.println();// Move to the next line to print the next row.

						result.add(newResult);
					}
					HttpSession session =request.getSession();
					session.setAttribute("routeList", result);
					RequestDispatcher rd = request.getRequestDispatcher("routelistjsp");
					rd.forward(request, response);
					
				}
			}
		} catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("DB error" + e.getMessage());

		}
	}
	}
	