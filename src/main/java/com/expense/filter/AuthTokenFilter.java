package com.expense.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

//@Component
public class AuthTokenFilter implements Filter {
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)(request);
		String token = req.getHeader("token");
		
		if(token == null || token.trim().length() != 16)
		{
			
			
		}
		else 
		{
			chain.doFilter(request, response);
		}
		
	}
}
