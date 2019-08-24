package com.dev.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.app.ws.SpringApplicationContext;
import com.dev.app.ws.dto.UserDTO;
import com.dev.app.ws.model.UserLoginRequestModel;
import com.dev.app.ws.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			UserLoginRequestModel credentials = new ObjectMapper()
					.readValue(request.getInputStream(), UserLoginRequestModel.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), 
											credentials.getPassword(), new ArrayList<>()));
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = ((User)authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();

		UserService userService = (UserService) SpringApplicationContext.getBean("userService");
		UserDTO userDTO = userService.getUser(username);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("UserID", userDTO.getUserId());
	}
}
