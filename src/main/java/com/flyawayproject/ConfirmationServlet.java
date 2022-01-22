package com.flyawayproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmationServlet extends HttpServlet{



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
		String fno =(String)session.getAttribute("fno");
		String email =(String)session.getAttribute("email");
		
		ResultSet rs, bs;

		String uid = UUID.randomUUID().toString();
		int rowsAfftected = 0;
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
				System.out.println("Connection established!");
				
				try(PreparedStatement ps = connection.prepareStatement("INSERT INTO booking(bdate, nopas, fno, email, uid) "
						+ "VALUES(?, ?, ?, ?, ?)")) {
					// Setting values for the placeholders
					ps.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
					ps.setString(2, nopas);
					ps.setString(3, fno);
					ps.setString(4, email);
					ps.setString(5, uid);
					
					// Execute the query
					rowsAfftected = ps.executeUpdate();
				}
					
					if(rowsAfftected > 0) {
						System.out.println("A new record inserted!");
						String bdate =(String)session.getAttribute("email");
						
						try(PreparedStatement cs = connection.prepareStatement("Select bid from booking where uid=? ")){
							cs.setString(1, uid);
							
							// Execute the query
							bs = cs.executeQuery();

							response.setContentType("text/html");

							ResultSetMetaData csmd = cs.getMetaData();
					
								
							while (bs.next()) 
							{
								String bid = bs.getString(1);
								session.setAttribute("bid", bid);
							}
						}
						
						RequestDispatcher rd = request.getRequestDispatcher("confirmationjsp");
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
			
		} catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("DB error" + e.getMessage());

		}
	}
	

	
}
