package com.dev.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dev.app.ws.dto.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDto);
	
	UserDTO getUser(String email);
	
	UserDTO getUserByUserId(String userId);
	
	UserDTO updateUser(String userId, UserDTO userDTO);
	
	void deleteUser(String userId);
	
	List<UserDTO> getUsers(int page, int limit);
}
