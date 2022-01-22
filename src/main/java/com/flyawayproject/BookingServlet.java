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

public class BookingServlet extends HttpServlet{

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
		String date =(String)session.getAttribute("date");
		String source =(String)session.getAttribute("source");
		String destination=(String)session.getAttribute("destination");
		String nopas =(String)session.getAttribute("nopas");
		String rid =(String)session.getAttribute("rid");
		
		ResultSet rs;
		String flightId = request.getParameter("flightid");
		session.setAttribute("fno", flightId);
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
				System.out.println("Connection established!");
					
                 try (PreparedStatement ps = connection
						.prepareStatement("Select fno,price,starttime,arrivaltime from flight where fno=? ")) {
					// Setting values for the placeholders
					ps.setString(1, flightId);
					
					// Execute the query
					rs = ps.executeQuery();

					response.setContentType("text/html");

					ResultSetMetaData rsmd = rs.getMetaData();
			
						
					while (rs.next()) 
					{
						FlightsResultsDTO newResult = new FlightsResultsDTO();
						newResult.setFlightId(Integer.parseInt(rs.getString(1)));
						newResult.setPrice(Float.parseFloat(rs.getString(2)));
						newResult.setDepartureDate(rs.getString(3));
						newResult.setArrivalDate(rs.getString(4));

						session.setAttribute("book", newResult);
						
					}
					
					RequestDispatcher rd = request.getRequestDispatcher("bookingpaymentjsp");
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
