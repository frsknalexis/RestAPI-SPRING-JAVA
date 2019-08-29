package com.dev.app.ws.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.app.ws.dto.UserDTO;
import com.dev.app.ws.enums.OperationNames;
import com.dev.app.ws.enums.OperationStatus;
import com.dev.app.ws.model.OperationStatusModel;
import com.dev.app.ws.model.UserDetailsRequestModel;
import com.dev.app.ws.model.UserResponse;
import com.dev.app.ws.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@GetMapping(value = "/users/{id}", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserResponse getUser(@PathVariable(value = "id") String id) {
		
		UserResponse userDetails = new UserResponse();
		UserDTO userDTO = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDTO, userDetails);
		return userDetails;
	}
	
	@PostMapping(value = "/users",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserResponse createUser(@RequestBody UserDetailsRequestModel request) throws Exception {
		
		UserResponse userDetails = new UserResponse();
		
		if(request.getFirstName().isEmpty()) throw new NullPointerException("The object is null");
		
		//UserDTO userDto = new UserDTO();
		//BeanUtils.copyProperties(request, userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDto = modelMapper.map(request, UserDTO.class);
		
		UserDTO createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, userDetails);
		
		return userDetails;
	}
	
	@PutMapping(value = "/users/{id}", 
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserResponse updateUser(@PathVariable(value = "id") String id, @Valid @RequestBody UserDetailsRequestModel request) {
		
		UserResponse userDetails = new UserResponse();
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(request, userDTO);
		
		UserDTO updatedUser = userService.updateUser(id, userDTO);
		BeanUtils.copyProperties(updatedUser, userDetails);
		
		return userDetails;
	}
	
	@DeleteMapping(value = "/users/{id}",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable(value = "id") String id)  {
		
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(OperationNames.DELETE.name());
		
		userService.deleteUser(id);
		
		returnValue.setOperationResult(OperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	@GetMapping(value = "/users",
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		
		List<UserResponse> returnValue = new ArrayList<>();
		
		List<UserDTO> usersDTO = userService.getUsers(page, limit);
		
		for(UserDTO userDTO : usersDTO) {
			UserResponse userModel = new UserResponse();
			BeanUtils.copyProperties(userDTO, userModel);
			returnValue.add(userModel);
		}
		
		return returnValue;
	}
}
