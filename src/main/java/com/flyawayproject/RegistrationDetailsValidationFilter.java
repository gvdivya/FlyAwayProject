package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegistrationDetailsValidationFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {	
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if(fname == null || fname.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please provide a First Name!</h2>"
							+ "</body></html>"
					);
		} else if(lname == null || lname.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please provide a Last Name!</h2>"
							+ "</body></html>"
					);
		} else if(email == null || email.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please provide a valid email!</h2>"
							+ "</body></html>"
					);
		} else if(password == null || password.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please provide a Password!</h2>"
							+ "</body></html>"
					);
		} else if(confirmPassword == null || confirmPassword.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please provide a Confirm Password!</h2>"
							+ "</body></html>"
					);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy( ) {
		/* Called before the Filter instance is removed from service by the web container*/
	}
}