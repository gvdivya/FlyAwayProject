package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminBookingServlet extends HttpServlet {
	final String DB_URL = "jdbc:mysql://localhost:3306/Flyaway";
	final String USER = "root";
	final String PASSWORD = null;
	;

	// For testing purposes
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();
		
		ResultSet rs;
		
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
				System.out.println("Connection established!");
					
                 try (PreparedStatement ps = connection
						.prepareStatement("Select bid,fno,nopas,bdate,email from Booking")) {
					
					

					// Execute the query
					rs = ps.executeQuery();

					response.setContentType("text/html");

					ResultSetMetaData rsmd = rs.getMetaData();
					
					int columnsNumber = rsmd.getColumnCount();
					
					List<AdminBookingDTO> result = new ArrayList<>();
			
						
					while (rs.next()) 
					{
						AdminBookingDTO newResult = new AdminBookingDTO();
						newResult.setBookingId(Integer.parseInt(rs.getString(1)));
						newResult.setBFlightId(Integer.parseInt(rs.getString(2)));
						newResult.setNoPax(Integer.parseInt(rs.getString(3)));
						newResult.setBookingDate(rs.getString(4));
						newResult.setEmail(rs.getString(5));
							// Print one row
							for (int i = 1; i <= columnsNumber; i++) 
							{
								System.out.print(rs.getString(i) + " "); // Print one element of a row
							
							}

							System.out.println();// Move to the next line to print the next row.

						result.add(newResult);
					}
					session.setAttribute("adminbookingList", result);
					RequestDispatcher rd = request.getRequestDispatcher("adminbookingjsp");
					rd.forward(request, response);
					
					/*for(FlightsResultsDTO test: result) {
						System.out.println("new row");
						System.out.println("flightID: " + test.getFlightId());
						System.out.println("price: " + test.getPrice());
						System.out.println("depDAte: " + test.getDepartureDate());
						System.out.println("arrivalDate: " + test.getArrivalDate());
					}*/
				}
			}
		} catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("DB error" + e.getMessage());

		}
	}
	}
	
	

