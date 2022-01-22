package com.flyawayproject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Userauthfilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {	
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		if(email == null || email.isEmpty()) {		
			response.getWriter().write(
					"<html><body>"
							+"<h2>Please login as user</h2>"
							+ "</body></html>"
					);
		}  else {
			chain.doFilter(request, response);
		}
	}
	
	public void destroy( ) {
		/* Called before the Filter instance is removed from service by the web container*/
	}
}