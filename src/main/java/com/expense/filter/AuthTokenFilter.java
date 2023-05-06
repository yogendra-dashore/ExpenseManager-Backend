package com.expense.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.expense.entity.UserEntity;
import com.expense.repository.UserRepository;
import com.google.gson.Gson;

//@Component
public class AuthTokenFilter implements Filter {
	
	
	@Autowired
	UserRepository userRepository;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)(request);
		HttpServletResponse resp = (HttpServletResponse)(response);
		
		String url = req.getRequestURI().toString();
		
		System.out.println(url);
		
		String token = req.getHeader("token");
//		
//		if() {
//			
//			
//		}
//		
		
		if(token == null || token.trim().length() != 16)
		{
         String userJsonString = new Gson().toJson(token);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(userJsonString);
			resp.setStatus(HttpStatus.FORBIDDEN.value());
			out.flush();
			
		}
		else 
		{
			UserEntity token1 = userRepository.findBytoken(token).orElse(null);
			if(token1 == null) {
				
				String userJsonString = new Gson().toJson("Invalid Access");
				
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(userJsonString);
				resp.setStatus(HttpStatus.FORBIDDEN.value());
				out.flush();
			}
			
			
			
			chain.doFilter(request, response);
		}
		
	}
}
