package com.dev.app.ws.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.app.ws.dto.UserDTO;
import com.dev.app.ws.entity.UserEntity;
import com.dev.app.ws.repository.UserRepository;
import com.dev.app.ws.service.UserService;
import com.dev.app.ws.util.Utils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	private Utils utils;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		
		if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("Record already exists");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDTO getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
			
		UserDTO returnValue = new UserDTO();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null)  throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}
}
