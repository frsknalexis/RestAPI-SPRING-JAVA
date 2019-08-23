package com.dev.app.ws.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.ws.dto.UserDTO;
import com.dev.app.ws.model.UserDetailsRequestModel;
import com.dev.app.ws.model.UserResponse;
import com.dev.app.ws.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@GetMapping("/users")
	public String getUser() {
		return "get user was called";
	}
	
	@PostMapping("/users")
	public UserResponse createUser(@RequestBody UserDetailsRequestModel request) {
		
		UserResponse userDetails = new UserResponse();
		
		UserDTO userDto = new UserDTO();
		BeanUtils.copyProperties(request, userDto);
		
		UserDTO createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userDetails);
		
		return userDetails;
	}
	
	@PutMapping("/users")
	public String updateUser() {
		return "update user was called";
	}
	
	@DeleteMapping("/users")
	public String deleteUser()  {
		return "delete user was called";
	}
}
