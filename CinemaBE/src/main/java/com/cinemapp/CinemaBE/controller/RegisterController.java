package com.cinemapp.CinemaBE.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cinemapp.CinemaBE.domain.User;
import com.cinemapp.CinemaBE.dto.UserDTO;
import com.cinemapp.CinemaBE.mapper.UserMapper;
import com.cinemapp.CinemaBE.service.UserService;

@RestController
public class RegisterController {
		
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
		
		User newUser = userService.save(userMapper.userDTOToUser(userDTO));
		
		return new ResponseEntity<>(userMapper.userToUserDTO(newUser), HttpStatus.OK);
	}

}
