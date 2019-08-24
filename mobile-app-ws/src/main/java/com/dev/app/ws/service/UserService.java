package com.dev.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dev.app.ws.dto.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDto);
	
	UserDTO getUser(String email);
}
